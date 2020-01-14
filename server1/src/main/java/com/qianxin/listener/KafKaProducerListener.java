package com.qianxin.listener;

import com.qianxin.service.SendService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.LeaderNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafKaProducerListener implements ProducerListener<String,String>{
    @Autowired
    SendService sendService;
    @Override
    public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {

    }

    @Override
    public void onError(String topic, Integer partition, String key, String value, Exception exception) {
               if(exception != null){
                   if(exception instanceof LeaderNotAvailableException){
                     //todo 重发消息
                       sendService.send(topic,value);
                   }else {
                       exception.printStackTrace();
                   }
               }else{
                   //todo log.error
               }
    }

    @Override
    public boolean isInterestedInSuccess() {
        return false;
    }
}
