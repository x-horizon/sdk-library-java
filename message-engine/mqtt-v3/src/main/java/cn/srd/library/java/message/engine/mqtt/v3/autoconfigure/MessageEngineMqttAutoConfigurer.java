// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.contract.properties.MessageEngineMqttProperties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageHandler;

import java.util.Optional;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine MQTT
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AllArgsConstructor
@AutoConfiguration
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableIntegration
@EnableConfigurationProperties(MessageEngineMqttProperties.class)
@IntegrationComponentScan
public class MessageEngineMqttAutoConfigurer {

    private final IntegrationFlowContext flowContext;

    @Bean
    @ConditionalOnBean(MessageEngineMqttSwitcher.class)
    public MqttPahoClientFactory mqttClientFactory() {
        MessageEngineMqttProperties mqttProperties = Springs.getBean(MessageEngineMqttProperties.class);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        Optional.ofNullable(mqttProperties.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(mqttProperties.getPassword()).ifPresent(password -> mqttConnectOptions.setPassword(password.toCharArray()));
        mqttConnectOptions.setServerURIs(Converts.toArray(mqttProperties.getServerURLs(), String.class));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        registerFlow(mqttClientFactory);
        return mqttClientFactory;
    }

    private void registerFlow(MqttPahoClientFactory mqttClientFactory) {
        this.flowContext
                .registration(mqttOutFlow(mqttClientFactory))
                .id("mqttOutFlow")
                .useFlowIdAsPrefix()
                .register();

        this.flowContext
                .registration(mqttInFlow(mqttClientFactory))
                .id("mqttInFlow")
                .useFlowIdAsPrefix()
                .register();
    }

    // publisher

    private IntegrationFlow mqttOutFlow(MqttPahoClientFactory mqttClientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher3", mqttClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("siSampleTopic");
        messageHandler.setDefaultQos(1);
        messageHandler.setCompletionTimeout(5000);
        return flow -> flow
                .transform(p -> Converts.withJackson().toString(p))
                .handle(messageHandler);
    }

    // consumer

    public IntegrationFlow mqttInFlow(MqttPahoClientFactory mqttClientFactory) {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer3", mqttClientFactory, "siSampleTopic");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return IntegrationFlow.from(adapter)
                .handle(handler())
                .get();
    }

    public MessageHandler handler() {
        return message -> {
            MessageModel<String> messageModel = Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class);
            System.out.println(message.getPayload());
        };
    }

}