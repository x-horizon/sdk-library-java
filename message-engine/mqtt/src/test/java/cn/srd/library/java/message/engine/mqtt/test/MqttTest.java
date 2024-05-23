// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.test.context.MockIntegrationContext;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.integration.test.mock.MockIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * mqtt test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class MqttTest {

    // @ClassRule
    // public static final BrokerRunning brokerRunning = BrokerRunning.isRunning(1883);

    @Autowired private MockIntegrationContext mockIntegrationContext;

    @Autowired private IntegrationFlow mqttOutFlow;

    @Test
    void test() throws InterruptedException {
        ArgumentCaptor<Message<?>> captor = MockIntegration.messageArgumentCaptor();
        CountDownLatch receiveLatch = new CountDownLatch(1);
        MessageHandler mockMessageHandler = MockIntegration.mockMessageHandler(captor).handleNext(_ -> receiveLatch.countDown());
        this.mockIntegrationContext.substituteMessageHandlerFor("mqttInFlow.org.springframework.integration.config.ConsumerEndpointFactoryBean#1", mockMessageHandler);
        this.mqttOutFlow.getInputChannel().send(new GenericMessage<>("foo"));
        Assertions.assertThat(receiveLatch.await(10, TimeUnit.SECONDS)).isTrue();
        Mockito.verify(mockMessageHandler).handleMessage(ArgumentMatchers.any());
        Assertions.assertThat(captor.getValue().getPayload()).isEqualTo("foo sent to MQTT, received from MQTT");
    }

}