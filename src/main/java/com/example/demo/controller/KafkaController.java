package com.example.demo.controller;

import com.example.demo.model.Constants;
import com.example.demo.model.responsebody.ErrorCode;
import com.example.demo.model.UserManagement;
import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.RoleManagementService;
import com.example.demo.service.UserManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private RoleManagementService roleManagementService;

    private final KafkaProducerService producer;

    private ErrorCode errorCode = new ErrorCode();

    public KafkaController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/user-management-signup")
    public ResponseEntity<?> sendMessageToKafkaTopicEmployee(@RequestBody UserManagement userManagement) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String userData = objectMapper.writeValueAsString(userManagement);
        if(userManagementService.findById(userManagement.getUsername()).isPresent()){
            errorCode.setCode(Constants.USERNAME_EXIST[0]);
            errorCode.setMessage(Constants.USERNAME_EXIST[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }

        if(roleManagementService.findById(userManagement.getRoleId()).isPresent() == false){
            errorCode.setCode(Constants.ROLE_NOT_AVAILABLE[0]);
            errorCode.setMessage(Constants.ROLE_NOT_AVAILABLE[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }

        try{
            this.producer.sendMessage(userData);
            errorCode.setCode(Constants.SUCCESS[0]);
            errorCode.setMessage(Constants.SUCCESS[1]);
            return ResponseEntity.ok().body(errorCode);
        }catch (Exception e){
            errorCode.setCode(Constants.METHOD_ERROR[0]);
            errorCode.setMessage(Constants.METHOD_ERROR[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }
    }

    @PostMapping(value = "/create-topic")
    public ResponseEntity<?> createKafkaTopic(@RequestParam String topicName){
        try{
            producer.createTopic(topicName);
            errorCode.setCode(Constants.SUCCESS[0]);
            errorCode.setMessage(Constants.SUCCESS[1]);
            return ResponseEntity.ok().body(errorCode);
        }catch (Exception e){
            errorCode.setCode(Constants.METHOD_ERROR[0]);
            errorCode.setMessage(Constants.METHOD_ERROR[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }
    }
}

