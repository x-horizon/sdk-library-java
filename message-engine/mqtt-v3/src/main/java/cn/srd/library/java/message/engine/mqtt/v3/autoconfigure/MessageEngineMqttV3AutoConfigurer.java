// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageEngineMqttV3Config;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.properties.MessageEngineMqttV3Properties;
import cn.srd.library.java.message.engine.mqtt.v3.strategy.MessageEngineMqttV3Strategy;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
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
import org.springframework.context.annotation.DependsOn;
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
@EnableConfigurationProperties(MessageEngineMqttV3Properties.class)
@IntegrationComponentScan
public class MessageEngineMqttV3AutoConfigurer {

    private final IntegrationFlowContext flowContext;

    @Bean
    public MessageEngineMqttV3Strategy messageEngineMqttStrategy() {
        return new MessageEngineMqttV3Strategy();
    }

    @Bean
    @ConditionalOnBean(MessageEngineMqttV3Switcher.class)
    public MessageEngineMqttV3Customizer messageEngineMqttV3Customizer() {
        return new MessageEngineMqttV3Customizer();
    }

    @Bean
    @ConditionalOnBean(MessageEngineMqttV3Switcher.class)
    @DependsOn("messageEngineMqttV3Customizer")
    public MqttPahoClientFactory mqttClientFactory() {
        MessageEngineMqttV3Properties mqttProperties = Springs.getBean(MessageEngineMqttV3Properties.class);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        Optional.ofNullable(mqttProperties.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(mqttProperties.getPassword()).ifPresent(password -> mqttConnectOptions.setPassword(password.toCharArray()));
        mqttConnectOptions.setServerURIs(Converts.toArray(mqttProperties.getServerURLs(), String.class));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        registerConsumerFlow(mqttClientFactory);
        return mqttClientFactory;
    }

    private void registerConsumerFlow(MqttPahoClientFactory mqttClientFactory) {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(method -> Comparators.equals(MessageEngineType.MQTT_V3, method.getAnnotation(MessageConsumer.class).engineConfig().type()))
                .forEach(method -> {
                    String flowId = MessageFlows.getUniqueFlowId(MessageEngineType.MQTT_V3, method);
                    MessageConsumer messageConsumerAnnotation = method.getAnnotation(MessageConsumer.class);
                    if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
                        MessageEngineMqttV3Config messageEngineMqttV3Config = messageConsumerAnnotation.engineConfig().mqttV3();
                        MessageEngineMqttV3Customizer mqttV3Customizer = Springs.getBean(MessageEngineMqttV3Customizer.class);
                        MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(MessageFlows.getUniqueClientId(mqttV3Customizer.getUniqueClientIdGenerateType(), flowId, messageEngineMqttV3Config.clientId()), mqttClientFactory, messageConsumerAnnotation.topic());
                        messageDrivenChannelAdapter.setQos(messageEngineMqttV3Config.qos().getStatus());
                        messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
                        messageDrivenChannelAdapter.setCompletionTimeout(messageEngineMqttV3Config.completionTimeout());
                        messageDrivenChannelAdapter.setDisconnectCompletionTimeout(messageEngineMqttV3Config.disconnectCompletionTimeout());
                        Object consumerInstance = Springs.getBean(method.getDeclaringClass());
                        Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, method.getDeclaringClass().getName())
                                .setThrowable(LibraryJavaInternalException.class)
                                .throwsIfNull(consumerInstance);
                        IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                                .handle(message -> Reflects.invoke(consumerInstance, method, Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class).requireSuccessAndGetData()))
                                .get();
                        this.flowContext
                                .registration(flow)
                                .id(flowId)
                                .useFlowIdAsPrefix()
                                .register();
                    }
                });
    }

}