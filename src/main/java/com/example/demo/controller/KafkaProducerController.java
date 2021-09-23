package com.example.demo.controller;

import com.example.demo.model.Biodata;
import com.example.demo.model.Employee;
import com.example.demo.service.KafkaConsumerService;
import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {

    private final KafkaProducerService producer;

    @Autowired
    KafkaProducerController(KafkaProducerService producer) {
        this.producer = producer;
    }

//    @PostMapping(value = "/publish")
//    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
//        this.producer.sendMessage(message);
//    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopicEmployee(@RequestBody Biodata biodata) {
        this.producer.sendMessage(biodata);
    }
}
