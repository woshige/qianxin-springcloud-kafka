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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    @KafkaListener(topics = {"QI_AN_XIN_TEST_04"},containerFactory = "batchFactory",id = "qianxin_consumer_01")
    public void consumerMessage(List<ConsumerRecord<String, String>> list) {
        System.out.println("-----------------the list size: " + list.size() + "--------------------");
        List<ConsumerVO> consumerVOS = new LinkedList<>();
        List<Long> query = new LinkedList<>();
        Result<List<UserInfoBean>> userInfoBeans = null;
        Integer count = 0;
        ArrayList<ProducerBO> temp = new ArrayList<>(list.size());
        for (ConsumerRecord<String, String> record : list) {
            String value = record.value();
            ProducerBO producerBO = JsonUtils.jsonToObject(value, ProducerBO.class);
            if (producerBO != null) {
                query.add(producerBO.getUser_id());
                temp.add(producerBO);
                count++;
            }
            if (count.equals(list.size())) {
                userInfoBeans = feign.findByUserIds(query);
                break;
            }
            producerBO = null;
        }
        if (userInfoBeans == null) {
            logger.info("查询到的userInfoBeans为空");
        }else{
            int size = temp.size();
            List<UserInfoBean> infoBeans = userInfoBeans.getData();
            for (int i = 0; i < size; i++){
                ProducerBO producerBO = temp.get(i);
                UserInfoBean userInfoBean = infoBeans.get(i);
                ConsumerVO consumerVO = new ConsumerVO();
                consumerVO.setProducerBO(producerBO);
                consumerVO.setUser_tel(userInfoBean.getUser_tel());
                consumerVO.setUser_name(userInfoBean.getUser_name());
                consumerVO.setUser_id(userInfoBean.getUser_id());
                consumerVOS.add(consumerVO);
                consumerInfoService.insert(consumerVOS);
            }

        }
    }
}
