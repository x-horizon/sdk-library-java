// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3;

import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.tool.convert.all.Converts;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.messaging.MessageHandler;

@Slf4j
@EnableIntegration
@IntegrationComponentScan
@SpringBootApplication
public class MessageEngineMqttApplication {

    public static void main(String... args) {
        SpringApplication.run(MessageEngineMqttApplication.class, args);
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        // options.setServerURIs(new String[]{"tcp://localhost:10017"});
        // options.setUserName("admin");
        // options.setPassword("a1234567".toCharArray());
        options.setServerURIs(new String[]{"tcp://192.168.10.91:32567"});
        // options.setUserName("admin");
        // options.setPassword("i76Pmj9cs6h3Ze".toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }

    // publisher

    @Bean
    public IntegrationFlow mqttOutFlow() {
        return IntegrationFlow.from(CharacterStreamReadingMessageSource.stdin(), e -> e.poller(Pollers.fixedDelay(1000)))
                .transform(p -> Converts.withJackson().toString(p))
                // .tr
                .handle(mqttOutbound())
                .get();
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("siSampleTopic");
        return messageHandler;
    }

    // consumer

    @Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlow.from(mqttInbound())
                .handle(handler())
                .get();
    }

    public MessageHandler handler() {
        return message -> {
            MessageModel<String> messageModel = Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class);
            System.out.println(message.getPayload());
        };
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer", mqttClientFactory(), "siSampleTopic");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

}