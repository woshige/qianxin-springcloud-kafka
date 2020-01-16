package com.qianxin.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafKaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private Boolean autoCommit;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private Integer autoCommitInterval;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private Integer maxPollRecords;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,autoCommit);
        map.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommit);
        map.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,maxPollRecords);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        map.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        map.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,6000);
        map.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,12000);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return map;
    }
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        factory.setConcurrency(10);
        return factory;
    }
}
