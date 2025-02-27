package org.horizon.sdk.library.java.message.engine.client.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageKafkaClientApplication {

    public static void main(String... args) {
        SpringApplication.run(MessageKafkaClientApplication.class, args);
    }

}