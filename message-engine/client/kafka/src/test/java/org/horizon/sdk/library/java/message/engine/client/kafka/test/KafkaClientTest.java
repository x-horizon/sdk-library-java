package org.horizon.sdk.library.java.message.engine.client.kafka.test;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.message.engine.client.kafka.autoconfigure.EnableMessageKafkaClient;
import org.horizon.sdk.library.java.message.engine.client.kafka.foo.FooProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * kafka test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@EnableMessageKafkaClient
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class KafkaClientTest {

    @Autowired private FooProducer fooProducer;

    @SneakyThrows
    @Test
    void test() {
        while (true) {
            fooProducer.send1();
            // fooProducer.send2();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}