package com.qianxin.service;

import com.qianxin.bean.UserInfoBean;
import com.qianxin.bo.ProducerBO;
import com.qianxin.common.Result;
import com.qianxin.feign.ConsumerFeign;
import com.qianxin.mapper.ConsumerMapper;
import com.qianxin.utils.JsonUtils;
import com.qianxin.vo.ConsumerVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConsumerService {
    Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    @Autowired
    ConsumerMapper mapper;
    @Autowired
    ConsumerFeign feign;
    @Autowired
    ConsumerInfoService consumerInfoService;

    /**
     * start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
     * 2020-01-12 22:18:32:576,
     * 2020-01-12 22:18:33:511,
     * 97.6563,
     * 104.4452,
     * 100000,
     * 106951.8717,   fetch.nMsg.sec
     * 1578885512822,
     * -1578885511887,
     * -0.0000,
     * -0.0001
     *
     * @param list
     */
    @KafkaListener(topics = "QI_AN_XIN_TEST", containerFactory = "batchFactory", id = "qianxin_consumer_02")
    public void consumerMessages(List<ConsumerRecord<String, String>> list) {
        logger.info("接收到的消息的数量为：{}", list.size());
        HashMap<Long, ProducerBO> map = new HashMap<>();
        Set<Long> query = new HashSet<>();
        List<ConsumerVO> consumerVOS = new LinkedList<>();
        List<ProducerBO> producerBOS = new LinkedList<>();
        for (ConsumerRecord<String, String> consumerRecord : list) {
            String value = consumerRecord.value();
            ProducerBO producerBO = JsonUtils.jsonToObject(value, ProducerBO.class);
            if (producerBO == null) {
                logger.error("将kafka消息转换为的实体类为空");
            } else {
                query.add(producerBO.getUser_id());
            }
        }
        Result<Map<Long, UserInfoBean>> result = feign.findByUserIds(query);
        Map<Long, UserInfoBean> data = result.getData();
        for (ProducerBO producerBO : producerBOS) {
            ConsumerVO vo = new ConsumerVO();
            vo.setProducerBO(producerBO);
            vo.setUser_name(data.get(producerBO.getUser_id()).getUser_name());
            vo.setUser_tel(data.get(producerBO.getUser_id()).getUser_tel());
            consumerVOS.add(vo);
        }
        consumerInfoService.insert(consumerVOS);
    }
}
