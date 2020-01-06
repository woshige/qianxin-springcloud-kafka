package com.qianxin.service;

import com.qianxin.bean.UserInfoBean;
import com.qianxin.bo.ProducerBO;
import com.qianxin.common.Result;
import com.qianxin.feign.ConsumerFeign;
import com.qianxin.mapper.ConsumerMapper;
import com.qianxin.utils.JsonUtils;
import com.qianxin.vo.ConsumerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
    @Autowired
    ConsumerMapper mapper;
    @Autowired
    ConsumerFeign feign;

    @KafkaListener(topics = {"QI_AN_XIN_PRACTICE"})
    public void consumerMessage(String message) {
        ProducerBO producerBO = JsonUtils.jsonToObject(message, ProducerBO.class);
        UserInfoBean userInfoBean = feign.findById(producerBO.getUser_id());
        ConsumerVO vo = new ConsumerVO();
        vo.setCreate_time(producerBO.getCreate_time());
        vo.setDip(producerBO.getDip());
        vo.setId(producerBO.getUser_id());
        vo.setUser_id(producerBO.getUser_id());
        vo.setSip(producerBO.getSip());
        vo.setThreat_level(producerBO.getThreat_level());
        vo.setUser_name(userInfoBean.getUserName());
        vo.setUser_tel(userInfoBean.getUser_tel());
        mapper.insert(vo);
    }
}
