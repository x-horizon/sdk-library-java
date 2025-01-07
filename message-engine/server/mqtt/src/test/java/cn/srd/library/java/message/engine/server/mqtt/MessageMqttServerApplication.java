package cn.srd.library.java.message.engine.server.mqtt;

import cn.srd.library.java.message.engine.server.mqtt.autoconfigure.EnableMessageMqttServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wjm
 * @since 2024-12-29 17:29
 */
@EnableMessageMqttServer
@SpringBootApplication
public class MessageMqttServerApplication {

    public static void main(String... args) {
        SpringApplication.run(MessageMqttServerApplication.class, args);
    }

}