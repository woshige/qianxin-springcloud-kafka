package com.qianxin.service;


import com.qianxin.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    public static String key = "QI_AN_XIN_PRACTICE";
    @Autowired
    private KafkaTemplate<String, Object> template;

    public ResultEnum send(String entity) {
        template.send(key, String.valueOf(System.currentTimeMillis()), entity);
        return ResultEnum.SUCCESS;
    }
}
