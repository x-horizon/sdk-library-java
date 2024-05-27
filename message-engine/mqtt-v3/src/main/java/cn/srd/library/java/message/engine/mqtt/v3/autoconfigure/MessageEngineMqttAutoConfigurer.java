// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.contract.properties.MessageEngineMqttProperties;
import cn.srd.library.java.message.engine.contract.MessageEngineType;
import cn.srd.library.java.message.engine.contract.MessageFlows;
import cn.srd.library.java.message.engine.contract.MessageReceive;
import cn.srd.library.java.message.engine.mqtt.v3.MessageEngineMqttAction;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
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
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

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
    public MessageEngineMqttAction messageEngineMqttAction() {
        return new MessageEngineMqttAction();
    }

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

    private <T> void registerFlow(MqttPahoClientFactory mqttClientFactory) {
        Annotations.getAnnotatedMethods(MessageReceive.class)
                .stream()
                .filter(method -> Comparators.equals(MessageEngineType.MQTT_V3, method.getAnnotation(MessageReceive.class).type()))
                .forEach(method -> {
                    String flowId = MessageFlows.getId(method);
                    if (Nil.isNull(flowContext.getRegistrationById(flowId))) {
                        MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer3", mqttClientFactory, "siSampleTopic");
                        messageDrivenChannelAdapter.setCompletionTimeout(5000);
                        messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
                        messageDrivenChannelAdapter.setQos(1);
                        this.flowContext
                                .registration(IntegrationFlow.from(messageDrivenChannelAdapter).handle(message -> {
                                    MessageModel<T> messageModel = Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class);
                                    messageModel.requireSuccess();
                                    Reflects.invoke(Springs.getBean(method.getDeclaringClass()), method, messageModel.getData());
                                }).get())
                                .id(flowId)
                                .useFlowIdAsPrefix()
                                .register();
                    }
                });
    }

}