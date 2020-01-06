package com.qianxin.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
    @KafkaListener(topics = {"QI_AN_XIN_PRACTICE"})
    public void consumerMessage(String message){
        
    }
}
