package com.qianxin.service;


import com.qianxin.call.KafKaCallback;
import com.qianxin.enums.ResultEnum;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class SendService {
    @Autowired
    KafKaCallback callback;

    @Autowired
    private KafkaTemplate<String, String> template;

    public ResultEnum send(String key,String entity) {
        ListenableFuture<SendResult<String, String>> future = template.send(new ProducerRecord<String, String>(key,entity));
        future.addCallback(callback);
        return ResultEnum.SUCCESS;
    }
}
