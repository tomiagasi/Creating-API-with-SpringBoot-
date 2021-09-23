package com.example.demo.service;

import com.example.demo.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @KafkaListener(topics = Constants.topic, groupId = Constants.groupId)
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
