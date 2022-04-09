package com.example.demo.service;

import com.example.demo.model.Constants;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.util.Arrays.asList;


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
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER);

        AdminClient admin = AdminClient.create(config);

        Map<String, String> configs = new HashMap<>();
        int partitions = Constants.PARTITIONS;
        short replication = Constants.REPLICATION;

        admin.createTopics(asList(new NewTopic(topicName, partitions, replication).configs(configs)));
    }
}
