package com.example.demo.service;

import com.example.demo.model.Constants;
import com.example.demo.model.UserManagement;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @Autowired
    private UserManagementService userManagementService;

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);


    @KafkaListener(topics = Constants.topic, groupId = Constants.groupId)
    public void consume(String message) {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        UserManagement userManagement = new Gson().fromJson(message, UserManagement.class);
        if(userManagementService.findById(userManagement.getUsername()).isPresent()){
            logger.info(String.format("#### Username Already Exist! ####"));
        }else if(userManagementService.findByEmail(userManagement.getEmail()) != null){
            logger.info(String.format("#### Email Already Exist! ####"));
        }else{
            userManagementService.save(userManagement);
        }
    }
}
