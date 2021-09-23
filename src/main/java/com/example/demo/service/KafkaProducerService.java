package com.example.demo.service;

import com.example.demo.model.Constants;
import com.example.demo.model.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sun.nio.cs.US_ASCII;


@SuppressWarnings("ALL")
@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = Constants.topic;

    @Autowired
    private KafkaTemplate<Object, UserManagement> kafkaTemplate;

    public void sendMessage(UserManagement userManagement) {
        logger.info(String.format("#### -> Producing message -> %s", userManagement));
        this.kafkaTemplate.send(TOPIC, userManagement);
    }

//    public void sendMessageEmployee(String employee) {
//        logger.info(String.format("#### -> Producing message -> %s", employee));
//        this.kafkaTemplate.send(TOPIC, employee);
//    }
}
