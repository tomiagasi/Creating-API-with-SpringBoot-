package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    public static final String topic = "testing-tomi";
    public static final String groupId = "testing";


    public static final int MAX_REQUESTS_PER_SECOND = 10;

    /*
    Kafka config
     */
    public static String KAFKA_SERVER;
    public static int PARTITIONS = 2;
    public static short REPLICATION = 2;

    /*
    ERROR CODE & MESSAGE
     */
    public static final String[] SUCCESS = {"00", "Success"};
    public static final String[] USERNAME_EXIST = {"83", "Username already exist!"};
    public static final String[] ROLE_NOT_AVAILABLE = {"84", "Role not available!"};
    public static final String[] METHOD_ERROR = {"02", "Method Error!"};
    public static final String[] INVALID_USERNAME_OR_PASSWORD = {"81", "Invalid Username or Password!"};

    @Value("${spring.kafka.producer.bootstrap-servers}")
    public void setKafkaServer(String kafkaServer) {
        KAFKA_SERVER = kafkaServer;
    }
}

