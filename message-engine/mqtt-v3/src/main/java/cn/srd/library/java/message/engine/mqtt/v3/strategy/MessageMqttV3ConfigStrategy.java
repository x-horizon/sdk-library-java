// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v3.model.dto.MessageMqttV3ConfigDTO;
import cn.srd.library.java.message.engine.mqtt.v3.model.properties.MessageMqttV3Properties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageMqttV3ConfigStrategy implements MessageConfigStrategy<MessageMqttV3ConfigDTO> {

    @Override
    public MessageMqttV3ConfigDTO customize() {
        log.info("{}message engine mqtt-v3 customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        MessageMqttV3ConfigDTO.BrokerDTO brokerDTO = buildBrokerDTO();
        Map<Method, Map<MessageConfig, MessageMqttV3ConfigDTO.ProducerDTO>> producerRouters = buildProducerRouters();
        Map<Method, MessageMqttV3ConfigDTO.ConsumerDTO> consumerRouters = buildConsumerRouters();
        MessageMqttV3ConfigDTO mqttV3ConfigDTO = MessageMqttV3ConfigDTO.builder()
                .brokerDTO(brokerDTO)
                .producerRouters(producerRouters)
                .consumerRouters(consumerRouters)
                .producerDTOs(Collections.getMapValues(producerRouters).stream().map(Collections::getMapValues).flatMap(Collection::stream).toList())
                .consumerDTOs(Collections.getMapValues(consumerRouters))
                .build();
        Springs.registerBean(MessageMqttV3ConfigDTO.class.getName(), mqttV3ConfigDTO);

        log.info("""
                        {}message engine mqtt-v3 customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        MqttV3 Broker Info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        MqttV3 Producer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        MqttV3 Consumer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getBrokerDTO()),
                Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getProducerDTOs()),
                Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getConsumerDTOs())
        );
        log.info("{}message engine mqtt-v3 customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);

        return mqttV3ConfigDTO;
    }

    private MessageMqttV3ConfigDTO.BrokerDTO buildBrokerDTO() {
        MessageMqttV3Properties mqttV3Properties = Springs.getBean(MessageMqttV3Properties.class);
        MessageMqttV3ConfigDTO.BrokerDTO brokerDTO = MessageMqttV3ConfigDTO.BrokerDTO.builder()
                .serverUrls(mqttV3Properties.getServerUrls())
                .username(mqttV3Properties.getUsername())
                .password(mqttV3Properties.getPassword())
                .build();
        registerClientFactory(brokerDTO);
        return brokerDTO;
    }

    private MessageMqttV3ConfigDTO.ClientDTO buildClientDTO(MessageMqttV3Config.ClientConfig clientConfig, Method executeMethod) {
        String flowId = MessageFlows.getFlowId(MessageEngineType.MQTT_V3, executeMethod);
        ClientIdGenerateType idGenerateType = clientConfig.idGenerateType();
        return MessageMqttV3ConfigDTO.ClientDTO.builder()
                .clientId(MessageFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .qosType(clientConfig.qosType())
                .completionTimeout(clientConfig.completionTimeout())
                .disconnectCompletionTimeout(clientConfig.disconnectCompletionTimeout())
                .originalCompletionTimeout(Times.wrapper(clientConfig.completionTimeout()).toMillisecond().toMillis())
                .originalDisconnectCompletionTimeout(Times.wrapper(clientConfig.disconnectCompletionTimeout()).toMillisecond().toMillis())
                .build();
    }

    private Map<Method, Map<MessageConfig, MessageMqttV3ConfigDTO.ProducerDTO>> buildProducerRouters() {
        return Annotations.getAnnotatedMethods(MessageProducer.class)
                .stream()
                .filter(producerMethod -> Collections.contains(Converts.toList(producerMethod.getAnnotation(MessageProducer.class).configs(), MessageConfig::engineType), MessageEngineType.MQTT_V3))
                .map(producerMethod -> {
                    MessageProducer producerAnnotation = producerMethod.getAnnotation(MessageProducer.class);
                    return Collections.ofPair(producerMethod, Arrays.stream(producerAnnotation.configs())
                            .filter(configAnnotation -> Comparators.equals(MessageEngineType.MQTT_V3, configAnnotation.engineType()))
                            .map(configAnnotation -> {
                                MessageMqttV3Config.ProducerConfig producerConfig = configAnnotation.mqttV3().producerConfig();
                                MessageMqttV3ConfigDTO.ProducerDTO producerDTO = MessageMqttV3ConfigDTO.ProducerDTO.builder()
                                        .clientDTO(buildClientDTO(configAnnotation.mqttV3().clientConfig(), producerMethod))
                                        .topic(producerAnnotation.topic())
                                        .needToSendAsync(producerConfig.needToSendAsync())
                                        .build();
                                registerProducerFlow(producerDTO);
                                return Collections.ofPair(configAnnotation, producerDTO);
                            })
                            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
                    );
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private Map<Method, MessageMqttV3ConfigDTO.ConsumerDTO> buildConsumerRouters() {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageMqttV3ConfigDTO.ConsumerDTO consumerDTO = MessageMqttV3ConfigDTO.ConsumerDTO.builder()
                            .clientDTO(buildClientDTO(consumerAnnotation.config().mqttV3().clientConfig(), consumerMethod))
                            .topics(Converts.toList(consumerAnnotation.topics()))
                            .build();
                    registerConsumerFlow(consumerDTO);
                    return Collections.ofPair(consumerMethod, consumerDTO);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private void registerClientFactory(MessageMqttV3ConfigDTO.BrokerDTO brokerDTO) {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(Converts.toArray(brokerDTO.getServerUrls(), String.class));
        Optional.ofNullable(brokerDTO.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(brokerDTO.getPassword()).ifPresent(value -> mqttConnectOptions.setPassword(value.toCharArray()));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        Springs.registerBean(DefaultMqttPahoClientFactory.class.getName(), mqttClientFactory);
    }

    private void registerProducerFlow(MessageMqttV3ConfigDTO.ProducerDTO producerDTO) {
        IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
        if (Nil.isNull(flowContext.getRegistrationById(producerDTO.getClientDTO().getFlowId()))) {
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(producerDTO.getClientDTO().getClientId(), Springs.getBean(MqttPahoClientFactory.class));
            messageHandler.setDefaultTopic(producerDTO.getTopic());
            messageHandler.setDefaultQos(producerDTO.getClientDTO().getQosType().getCode());
            messageHandler.setAsync(producerDTO.isNeedToSendAsync());
            messageHandler.setCompletionTimeout(producerDTO.getClientDTO().getOriginalCompletionTimeout());
            messageHandler.setDisconnectCompletionTimeout(producerDTO.getClientDTO().getOriginalDisconnectCompletionTimeout());
            flowContext.registration(MessageFlows.getObjectToStringIntegrationFlow(messageHandler))
                    .id(producerDTO.getClientDTO().getFlowId())
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

    private void registerConsumerFlow(MessageMqttV3ConfigDTO.ConsumerDTO consumerDTO) {
        IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
        if (Nil.isNull(flowContext.getRegistrationById(consumerDTO.getClientDTO().getFlowId()))) {
            DefaultMqttPahoClientFactory mqttClientFactory = Springs.getBean(DefaultMqttPahoClientFactory.class);
            MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(consumerDTO.getClientDTO().getClientId(), mqttClientFactory, Converts.toArray(consumerDTO.getTopics(), String.class));
            messageDrivenChannelAdapter.setQos(consumerDTO.getClientDTO().getQosType().getCode());
            messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
            messageDrivenChannelAdapter.setCompletionTimeout(consumerDTO.getClientDTO().getOriginalCompletionTimeout());
            messageDrivenChannelAdapter.setDisconnectCompletionTimeout(consumerDTO.getClientDTO().getOriginalDisconnectCompletionTimeout());

            Object consumerInstance = Springs.getBean(consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass());
            Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass().getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(consumerInstance);
            IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                    .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                    .get();
            flowContext.registration(flow)
                    .id(consumerDTO.getClientDTO().getFlowId())
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

}