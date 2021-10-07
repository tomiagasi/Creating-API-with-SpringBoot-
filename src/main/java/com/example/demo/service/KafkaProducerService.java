package com.example.demo.service;

import com.example.demo.model.Constants;
import org.apache.kafka.common.internals.Topic;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@SuppressWarnings("ALL")
@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = Constants.topic;

    @Autowired
    private KafkaTemplate<Object, String> kafkaTemplate;

    public void sendMessage(String userManagement) {
        logger.info(String.format("#### -> Producing message -> %s", userManagement));
        this.kafkaTemplate.send(TOPIC, userManagement);
    }

    public void createTopic(String topicName){
        TopicBuilder.name(topicName);
    }
}
