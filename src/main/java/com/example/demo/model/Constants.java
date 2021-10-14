package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    public static String PROJECT_URI = "http://localhost:8037/";

    public static final int MAX_REQUESTS_PER_SECOND = 10;

    /*
    Kafka config
     */
    public static final String topic = "testing-tomi";
    public static final String groupId = "testing";
    public static String KAFKA_SERVER;
    public static final int PARTITIONS = 2;
    public static final short REPLICATION = 2;

    /*
    ERROR CODE & MESSAGE
     */
    public static final String[] SUCCESS = {"00", "Success"};
    public static final String[] USERNAME_EXIST = {"83", "Username already exist!"};
    public static final String[] ROLE_NOT_AVAILABLE = {"84", "Role not available!"};
    public static final String[] METHOD_ERROR = {"02", "Method Error!"};
    public static final String[] INVALID_USERNAME_OR_PASSWORD = {"81", "Invalid Username or Password!"};
    public static final String[] INVALID_PASSWORD = {"82", "Invalid Password!"};
    public static final String[] PASSWORD_NOT_MATCH = {"83", "Password Not Match!"};
    public static final String[] EMAIL_EXIST = {"90", "Email already exist!"};
    public static final String[] INVALID_EMAIL = {"91", "Invalid Email!"};
    public static final String[] REQID_NOT_AVAILABLE = {"53", "Request ID not available!"};

    /*
    MASTER_STATUS
     */
    public static final int APPROVE = 54;
    public static final int WAITING_APPROVAL = 63;
    public static final int REJECT = 67;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    public void setKafkaServer(String kafkaServer) {
        KAFKA_SERVER = kafkaServer;
    }
}

