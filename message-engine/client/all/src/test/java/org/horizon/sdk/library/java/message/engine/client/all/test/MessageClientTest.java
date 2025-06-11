package org.horizon.sdk.library.java.message.engine.client.all.test;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.message.engine.client.all.foo.FooProducer;
import org.horizon.sdk.library.java.message.engine.client.kafka.autoconfigure.EnableMessageKafkaClient;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.autoconfigure.EnableMessageMqttV3Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * message engine test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@EnableMessageKafkaClient
@EnableMessageMqttV3Client
// @EnableMessageMqttV5Client
// @EnableMessageRabbitMqClient
// @EnableMessageRedisStreamClient
// @EnableMessageRocketMqClient
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class MessageClientTest {

    @Autowired private FooProducer fooProducer;

    @SneakyThrows
    @Test
    void test() {
        while (true) {
            // fooProducer.kafkaSend1();
            // fooProducer.kafkaSend2();
            fooProducer.mqttV3Send1();
            fooProducer.mqttV3SendDynamic("library-java-foo-dynamic-topic");
            // fooProducer.mqttV3Send2();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}