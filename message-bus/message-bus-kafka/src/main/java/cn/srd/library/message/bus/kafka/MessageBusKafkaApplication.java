package cn.srd.library.message.bus.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * boot
 */
@SpringBootApplication
public class MessageBusKafkaApplication {

    /**
     * main
     *
     * @param args main argument
     */
    public static void main(String[] args) {
        SpringApplication.run(MessageBusKafkaApplication.class, args);
    }

}
