// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.strategy;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.MqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v3.model.dto.MqttV3ConfigDTO;
import cn.srd.library.java.message.engine.mqtt.v3.model.property.MqttV3Property;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.support.Springs;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MqttV3ConfigStrategy extends MessageConfigStrategy<MqttV3Property, MqttV3ConfigDTO, MqttV3ConfigDTO.BrokerDTO, MqttV3ConfigDTO.ClientDTO, MqttV3ConfigDTO.ProducerDTO, MqttV3ConfigDTO.ConsumerDTO> {

    @Autowired MqttV3Property mqttV3Property;

    @Override
    protected Class<MqttV3ConfigDTO> getConfigType() {
        return MqttV3ConfigDTO.class;
    }

    @Override
    protected Class<MqttV3Property> getPropertyType() {
        return MqttV3Property.class;
    }

    @Override
    protected MqttV3ConfigDTO.BrokerDTO getBrokerDTO() {
        return MqttV3ConfigDTO.BrokerDTO.builder()
                .serverUrls(this.mqttV3Property.getServerUrls())
                .username(this.mqttV3Property.getUsername())
                .password(this.mqttV3Property.getPassword())
                .build();
    }

    @Override
    protected MqttV3ConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        MqttV3Config.ClientConfig clientConfigAnnotation = (MqttV3Config.ClientConfig) clientConfig;
        String flowId = MessageFlows.getFlowId(MessageEngineType.MQTT_V3, executeMethod);
        ClientIdGenerateType idGenerateType = clientConfigAnnotation.idGenerateType();
        return MqttV3ConfigDTO.ClientDTO.builder()
                .clientId(MessageFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .qosType(clientConfigAnnotation.qosType())
                .completionTimeout(clientConfigAnnotation.completionTimeout())
                .disconnectCompletionTimeout(clientConfigAnnotation.disconnectCompletionTimeout())
                .build();
    }

    @Override
    protected MqttV3ConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        MqttV3Config.ProducerConfig producerConfig = producerAnnotation.config().mqttV3().producerConfig();
        return MqttV3ConfigDTO.ProducerDTO.builder()
                .clientDTO(getClientDTO(producerAnnotation.config().mqttV3().clientConfig(), executeMethod))
                .topic(producerAnnotation.topic())
                .needToSendAsync(producerConfig.needToSendAsync())
                .build();
    }

    @Override
    protected MqttV3ConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        return MqttV3ConfigDTO.ConsumerDTO.builder()
                .clientDTO(getClientDTO(consumerAnnotation.config().mqttV3().clientConfig(), executeMethod))
                .forwardProducerDTO(forwardProducerDTO)
                .topics(Converts.toList(consumerAnnotation.topics()))
                .build();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MqttV3ConfigDTO.ProducerDTO producerDTO) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(producerDTO.getClientDTO().getClientId(), Springs.getBean(MqttPahoClientFactory.class));
        messageHandler.setDefaultTopic(producerDTO.getTopic());
        messageHandler.setDefaultQos(producerDTO.getClientDTO().getQosType().getCode());
        messageHandler.setAsync(producerDTO.isNeedToSendAsync());
        messageHandler.setCompletionTimeout(producerDTO.getClientDTO().getNativeCompletionTimeout());
        messageHandler.setDisconnectCompletionTimeout(producerDTO.getClientDTO().getNativeDisconnectCompletionTimeout());
        return MessageFlows.getObjectToStringIntegrationFlow(messageHandler);
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MqttV3ConfigDTO.ConsumerDTO consumerDTO) {
        DefaultMqttPahoClientFactory mqttClientFactory = Springs.getBean(DefaultMqttPahoClientFactory.class);
        MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(consumerDTO.getClientDTO().getClientId(), mqttClientFactory, Converts.toArray(consumerDTO.getTopics(), String.class));
        messageDrivenChannelAdapter.setQos(consumerDTO.getClientDTO().getQosType().getCode());
        messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        messageDrivenChannelAdapter.setCompletionTimeout(consumerDTO.getClientDTO().getNativeCompletionTimeout());
        messageDrivenChannelAdapter.setDisconnectCompletionTimeout(consumerDTO.getClientDTO().getNativeDisconnectCompletionTimeout());

        Object consumerInstance = Springs.getBean(consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass());
        return IntegrationFlow.from(messageDrivenChannelAdapter)
                .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                .get();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected void completeNativeConfigDTO(MqttV3ConfigDTO configDTO) {
        List<MqttV3ConfigDTO.ProducerDTO> producerDTOs = (List<MqttV3ConfigDTO.ProducerDTO>) configDTO.getProducerDTOs();
        producerDTOs.forEach(producerDTO -> producerDTO.getClientDTO()
                .setNativeCompletionTimeout(Times.wrapper(producerDTO.getClientDTO().getCompletionTimeout()).toMillisecond().toMillis())
                .setNativeDisconnectCompletionTimeout(Times.wrapper(producerDTO.getClientDTO().getDisconnectCompletionTimeout()).toMillisecond().toMillis())
        );

        List<MqttV3ConfigDTO.ConsumerDTO> consumerDTOs = (List<MqttV3ConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        consumerDTOs.forEach(consumerDTO -> consumerDTO.getClientDTO()
                .setNativeCompletionTimeout(Times.wrapper(consumerDTO.getClientDTO().getCompletionTimeout()).toMillisecond().toMillis())
                .setNativeDisconnectCompletionTimeout(Times.wrapper(consumerDTO.getClientDTO().getDisconnectCompletionTimeout()).toMillisecond().toMillis())
        );
    }

    @Override
    protected void registerClientFactory(MqttV3ConfigDTO.BrokerDTO brokerDTO) {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(Converts.toArray(brokerDTO.getServerUrls(), String.class));
        Optional.ofNullable(brokerDTO.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(brokerDTO.getPassword()).ifPresent(value -> mqttConnectOptions.setPassword(value.toCharArray()));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        Springs.registerBean(DefaultMqttPahoClientFactory.class.getName(), mqttClientFactory);
    }

    @Override
    protected void registerProducerFactory(MqttV3ConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(MqttV3ConfigDTO.ConsumerDTO consumerDTO) {

    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(MqttV3ConfigDTO configDTO) {
        MessageVerificationConfigDTO verificationConfigDTO = new MessageVerificationConfigDTO();

        List<MqttV3ConfigDTO.ProducerDTO> producerDTOs = (List<MqttV3ConfigDTO.ProducerDTO>) configDTO.getProducerDTOs();
        verificationConfigDTO.setProducerFailedReasons(producerDTOs
                .stream()
                .map(producerDTO -> (MessageVerificationConfigDTO.ProducerDTO) MessageVerificationConfigDTO.ProducerDTO.builder()
                        .methodPoint(producerDTO.getClientDTO().getFlowId())
                        .failedReason(verifyClientConfig(producerDTO.getClientDTO()))
                        .build()
                )
                .filter(failedReasonDTO -> Nil.isNotEmpty(failedReasonDTO.getFailedReason()))
                .toList()
        );

        List<MqttV3ConfigDTO.ConsumerDTO> consumerDTOs = (List<MqttV3ConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        verificationConfigDTO.setConsumerFailedReasons(consumerDTOs
                .stream()
                .map(consumerDTO -> (MessageVerificationConfigDTO.ConsumerDTO) MessageVerificationConfigDTO.ConsumerDTO.builder()
                        .methodPoint(consumerDTO.getClientDTO().getFlowId())
                        .failedReason(verifyClientConfig(consumerDTO.getClientDTO()))
                        .build()
                )
                .filter(failedReasonDTO -> Nil.isNotEmpty(failedReasonDTO.getFailedReason()))
                .toList()
        );

        return verificationConfigDTO;
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    private Map<String, String> verifyClientConfig(MqttV3ConfigDTO.ClientDTO clientDTO) {
        Map<String, String> failedReasons = Collections.newHashMap();
        if (Times.isInvalidTimeFormat(clientDTO.getCompletionTimeout())) {
            failedReasons.put("invalid completion timeout", STR."could not parse time from completion timeout value [\{clientDTO.getCompletionTimeout()}], please check!");
        }
        if (Times.isInvalidTimeFormat(clientDTO.getDisconnectCompletionTimeout())) {
            failedReasons.put("invalid disconnect completion timeout", STR."could not parse time from disconnect completion timeout value [\{clientDTO.getDisconnectCompletionTimeout()}], please check!");
        }
        return failedReasons;
    }

}