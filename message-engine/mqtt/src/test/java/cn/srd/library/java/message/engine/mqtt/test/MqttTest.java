// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * mqtt test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MqttTest {

    @Autowired
    private MqttGateway mqttGateway;

    @SneakyThrows
    @Test
    void testSave() {
        while (true) {
            mqttGateway.sendToMqtt("Hello MQTT", "testTopic");
            TimeUnit.SECONDS.sleep(1);
        }
    }

}