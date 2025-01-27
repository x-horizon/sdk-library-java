package cn.library.java.message.engine.client.mqtt.v3.test;

import cn.library.java.message.engine.client.mqtt.v3.autoconfigure.EnableMessageMqttV3Client;
import cn.library.java.message.engine.client.mqtt.v3.foo.FooProducer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * mqtt test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@EnableMessageMqttV3Client
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class MqttV3ClientTest {

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