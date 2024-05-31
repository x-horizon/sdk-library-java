// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageEngineMqttV3Config;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.properties.MessageEngineMqttV3Properties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * the global message engine mqtt-v3 customizer
 *
 * @author wjm
 * @since 2024-05-30 11:13
 */
@Slf4j
@Getter
public class MessageEngineMqttV3Customizer {

    @Autowired private IntegrationFlowContext flowContext;

    private ClientIdGenerateType clientIdGenerateType;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    @PostConstruct
    public void initialize() {
        log.info("{}message engine mqtt-v3 customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        EnableMessageEngineMqttV3 mqttV3Customizer = Annotations.getAnnotation(EnableMessageEngineMqttV3.class);
        this.clientIdGenerateType = mqttV3Customizer.clientIdGenerateType();

        List<Method> consumerMethods = getConsumerMethods();
        MqttPahoClientFactory clientFactory = registerClientFactory();
        registerConsumer(consumerMethods, clientFactory);

        log.info(""" 
                        {}message engine mqtt-v3 customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        Client Id Generate Config:
                           generateType                            = [{}]
                        Server Info:
                           serverUrls                              = [{}]
                        Consumer Info:
                        [
                           {}
                        ]
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                mqttV3Customizer.clientIdGenerateType().name(),
                Strings.join(Springs.getBean(MessageEngineMqttV3Properties.class).getServerUrls(), SymbolConstant.COMMA + SymbolConstant.SPACE),
                consumerMethods.stream().map(consumerMethod -> {
                            MessageEngineMqttV3Config mqttV3Config = consumerMethod.getAnnotation(MessageConsumer.class).engineConfig().mqttV3();
                            return STR."flowId = [\{MessageFlows.getUniqueFlowId(MessageEngineType.MQTT_V3, consumerMethod)}], " +
                                    STR."qos = [\{mqttV3Config.qos()}], " +
                                    STR."completionTimeout = [\{mqttV3Config.completionTimeout()}], " +
                                    STR."disconnectCompletionTimeout = [\{mqttV3Config.disconnectCompletionTimeout()}]";
                        })
                        .collect(Collectors.joining("\n   "))
        );

        log.info("{}message engine mqtt-v3 customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

    private List<Method> getConsumerMethods() {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, consumerMethod.getAnnotation(MessageConsumer.class).engineConfig().type()))
                .toList();
    }

    private MqttPahoClientFactory registerClientFactory() {
        MessageEngineMqttV3Properties mqttProperties = Springs.getBean(MessageEngineMqttV3Properties.class);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        Optional.ofNullable(mqttProperties.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(mqttProperties.getPassword()).ifPresent(password -> mqttConnectOptions.setPassword(password.toCharArray()));
        mqttConnectOptions.setServerURIs(Converts.toArray(mqttProperties.getServerUrls(), String.class));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        Springs.registerBean("mqttClientFactory", mqttClientFactory);
        return mqttClientFactory;
    }

    private void registerConsumer(List<Method> consumerMethods, MqttPahoClientFactory clientFactory) {
        consumerMethods.forEach(consumerMethod -> registerConsumerFlow(consumerMethod, clientFactory));
    }

    private void registerConsumerFlow(Method consumerMethod, MqttPahoClientFactory mqttClientFactory) {
        String flowId = MessageFlows.getUniqueFlowId(MessageEngineType.MQTT_V3, consumerMethod);
        MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            MessageEngineMqttV3Config messageEngineMqttV3Config = consumerAnnotation.engineConfig().mqttV3();
            String clientId = MessageFlows.getUniqueClientId(this.clientIdGenerateType, flowId);
            MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory, consumerAnnotation.topic());
            messageDrivenChannelAdapter.setQos(messageEngineMqttV3Config.qos().getStatus());
            messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
            messageDrivenChannelAdapter.setCompletionTimeout(messageEngineMqttV3Config.completionTimeout());
            messageDrivenChannelAdapter.setDisconnectCompletionTimeout(messageEngineMqttV3Config.disconnectCompletionTimeout());
            Object consumerInstance = Springs.getBean(consumerMethod.getDeclaringClass());
            Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, consumerMethod.getDeclaringClass().getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(consumerInstance);
            IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                    .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerMethod))
                    .get();
            this.flowContext
                    .registration(flow)
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

}