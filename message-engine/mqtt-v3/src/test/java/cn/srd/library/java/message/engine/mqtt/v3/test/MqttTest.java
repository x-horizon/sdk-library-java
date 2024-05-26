// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.test;

import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.EnableMessageEngineMqtt;
import cn.srd.library.java.message.engine.mqtt.v3.consumer.FooConsumer;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * mqtt test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@EnableMessageEngineMqtt
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class MqttTest {

    @Autowired private IntegrationFlow mqttOutFlow;

    @SneakyThrows
    @Test
    void test() {
        Springs.getBean(FooConsumer.class).send();
        while (true) {
            this.mqttOutFlow.getInputChannel().send(new GenericMessage<>(MessageModel.builder().status(200).message("ok").data("foo").build()));
            TimeUnit.SECONDS.sleep(1);
        }
    }

}