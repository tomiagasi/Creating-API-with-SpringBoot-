package com.example.demo.service;

import com.example.demo.model.Biodata;
import com.example.demo.model.Constants;
import com.example.demo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@SuppressWarnings("ALL")
@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = Constants.topic;

    @Autowired
    private KafkaTemplate<Object, Biodata> kafkaTemplate;

    public void sendMessage(Biodata biodata) {
        logger.info(String.format("#### -> Producing message -> %s", biodata));
        this.kafkaTemplate.send(TOPIC, biodata);
    }

//    public void sendMessageEmployee(String employee) {
//        logger.info(String.format("#### -> Producing message -> %s", employee));
//        this.kafkaTemplate.send(TOPIC, employee);
//    }
}
