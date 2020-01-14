package com.qianxin.service;


import com.qianxin.enums.ResultEnum;
import com.qianxin.listener.KafKaProducerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendService {

    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private KafKaProducerListener kafKaProducerListener;

    public ResultEnum send(String key,String entity) {
        template.setProducerListener(kafKaProducerListener);
        template.send(key, String.valueOf(System.currentTimeMillis()), entity);
        return ResultEnum.SUCCESS;
    }
}
