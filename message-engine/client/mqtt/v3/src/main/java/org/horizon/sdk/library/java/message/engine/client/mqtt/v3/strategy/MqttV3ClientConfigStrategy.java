package org.horizon.sdk.library.java.message.engine.client.mqtt.v3.strategy;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConsumer;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientIdGenerateType;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.support.MessageClientFlows;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.MqttV3Config;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.model.dto.MqttV3ClientConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.model.property.MqttV3ClientProperty;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.time.Times;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
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
public class MqttV3ClientConfigStrategy extends MessageClientConfigStrategy<MqttV3ClientProperty, MqttV3ClientConfigDTO, MqttV3ClientConfigDTO.BrokerDTO, MqttV3ClientConfigDTO.ClientDTO, MqttV3ClientConfigDTO.ProducerDTO, MqttV3ClientConfigDTO.ConsumerDTO> {

    @Autowired MqttV3ClientProperty mqttV3Property;

    @Override
    protected Class<MqttV3ClientConfigDTO> getConfigType() {
        return MqttV3ClientConfigDTO.class;
    }

    @Override
    protected Class<MqttV3ClientProperty> getPropertyType() {
        return MqttV3ClientProperty.class;
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        return MessageClientType.MQTT_V3;
    }

    @Override
    protected MqttV3ClientConfigDTO getMessageConfigDTO() {
        return Springs.getBean(MqttV3ClientConfigDTO.class);
    }

    @Override
    protected MqttV3ClientConfigDTO.BrokerDTO getBrokerDTO() {
        return MqttV3ClientConfigDTO.BrokerDTO.builder()
                .serverUrls(this.mqttV3Property.getServerUrls())
                .username(this.mqttV3Property.getUsername())
                .password(this.mqttV3Property.getPassword())
                .build();
    }

    @Override
    protected MqttV3ClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        MqttV3Config.ClientConfig clientConfigAnnotation = (MqttV3Config.ClientConfig) clientConfig;
        String flowId = MessageClientFlows.getStaticFlowId(MessageClientType.MQTT_V3, executeMethod);
        MessageClientIdGenerateType idGenerateType = clientConfigAnnotation.idGenerateType();
        return MqttV3ClientConfigDTO.ClientDTO.builder()
                .clientId(MessageClientFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .qualityOfServiceType(clientConfigAnnotation.qualityOfServiceType())
                .completionTimeout(clientConfigAnnotation.completionTimeout())
                .disconnectCompletionTimeout(clientConfigAnnotation.disconnectCompletionTimeout())
                .build();
    }

    @Override
    protected MqttV3ClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        MqttV3Config.ProducerConfig producerConfig = producerAnnotation.config().mqttV3().producerConfig();
        return MqttV3ClientConfigDTO.ProducerDTO.builder()
                .clientDTO(getClientDTO(producerAnnotation.config().mqttV3().clientConfig(), executeMethod))
                .topic(producerAnnotation.topic())
                .dynamicIs(computeDynamicIs(producerAnnotation.topic()))
                .needToSendAsync(producerConfig.needToSendAsync())
                .build();
    }

    @Override
    protected MqttV3ClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        return MqttV3ClientConfigDTO.ConsumerDTO.builder()
                .clientDTO(getClientDTO(consumerAnnotation.config().mqttV3().clientConfig(), executeMethod))
                .forwardProducerDTO(forwardProducerDTO)
                .topics(Converts.toList(consumerAnnotation.topics()))
                .build();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MqttV3ClientConfigDTO.ProducerDTO producerDTO) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(producerDTO.getClientDTO().getClientId(), Springs.getBean(MqttPahoClientFactory.class));
        messageHandler.setDefaultTopic(producerDTO.getTopic());
        messageHandler.setDefaultQos(producerDTO.getClientDTO().getQualityOfServiceType().getCode());
        messageHandler.setAsync(producerDTO.isNeedToSendAsync());
        messageHandler.setCompletionTimeout(producerDTO.getClientDTO().getNativeCompletionTimeout());
        messageHandler.setDisconnectCompletionTimeout(producerDTO.getClientDTO().getNativeDisconnectCompletionTimeout());
        return MessageClientFlows.getObjectToStringIntegrationFlow(messageHandler);
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MqttV3ClientConfigDTO.ConsumerDTO consumerDTO) {
        DefaultMqttPahoClientFactory mqttClientFactory = Springs.getBean(DefaultMqttPahoClientFactory.class);
        MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(consumerDTO.getClientDTO().getClientId(), mqttClientFactory, Converts.toArray(consumerDTO.getTopics(), String.class));
        messageDrivenChannelAdapter.setQos(consumerDTO.getClientDTO().getQualityOfServiceType().getCode());
        messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        messageDrivenChannelAdapter.setCompletionTimeout(consumerDTO.getClientDTO().getNativeCompletionTimeout());
        messageDrivenChannelAdapter.setDisconnectCompletionTimeout(consumerDTO.getClientDTO().getNativeDisconnectCompletionTimeout());

        Object consumerInstance = Springs.getBean(consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass());
        return IntegrationFlow.from(messageDrivenChannelAdapter)
                .handle(MessageClientFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                .get();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected void completeNativeConfigDTO(MqttV3ClientConfigDTO configDTO) {
        List<MqttV3ClientConfigDTO.ProducerDTO> producerDTOs = (List<MqttV3ClientConfigDTO.ProducerDTO>) configDTO.getProducerDTOs();
        producerDTOs.forEach(producerDTO -> producerDTO.getClientDTO()
                .setNativeCompletionTimeout(Times.wrapper(producerDTO.getClientDTO().getCompletionTimeout()).toMillisecond().toMillis())
                .setNativeDisconnectCompletionTimeout(Times.wrapper(producerDTO.getClientDTO().getDisconnectCompletionTimeout()).toMillisecond().toMillis())
        );

        List<MqttV3ClientConfigDTO.ConsumerDTO> consumerDTOs = (List<MqttV3ClientConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        consumerDTOs.forEach(consumerDTO -> consumerDTO.getClientDTO()
                .setNativeCompletionTimeout(Times.wrapper(consumerDTO.getClientDTO().getCompletionTimeout()).toMillisecond().toMillis())
                .setNativeDisconnectCompletionTimeout(Times.wrapper(consumerDTO.getClientDTO().getDisconnectCompletionTimeout()).toMillisecond().toMillis())
        );
    }

    @Override
    protected void registerClientFactory(MqttV3ClientConfigDTO.BrokerDTO brokerDTO) {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(Converts.toArray(brokerDTO.getServerUrls(), String.class));
        Optional.ofNullable(brokerDTO.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(brokerDTO.getPassword()).ifPresent(value -> mqttConnectOptions.setPassword(value.toCharArray()));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        Springs.registerBean(DefaultMqttPahoClientFactory.class.getName(), mqttClientFactory);
    }

    @Override
    protected void registerProducerFactory(MqttV3ClientConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(MqttV3ClientConfigDTO.ConsumerDTO consumerDTO) {

    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(MqttV3ClientConfigDTO configDTO) {
        MessageClientVerificationConfigDTO verificationConfigDTO = new MessageClientVerificationConfigDTO();

        List<MqttV3ClientConfigDTO.ProducerDTO> producerDTOs = (List<MqttV3ClientConfigDTO.ProducerDTO>) configDTO.getProducerDTOs();
        verificationConfigDTO.setProducerFailedReasons(producerDTOs
                .stream()
                .map(producerDTO -> (MessageClientVerificationConfigDTO.ProducerDTO) MessageClientVerificationConfigDTO.ProducerDTO.builder()
                        .methodPoint(producerDTO.getClientDTO().getFlowId())
                        .failedReason(verifyClientConfig(producerDTO.getClientDTO()))
                        .build()
                )
                .filter(failedReasonDTO -> Nil.isNotEmpty(failedReasonDTO.getFailedReason()))
                .toList()
        );

        List<MqttV3ClientConfigDTO.ConsumerDTO> consumerDTOs = (List<MqttV3ClientConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        verificationConfigDTO.setConsumerFailedReasons(consumerDTOs
                .stream()
                .map(consumerDTO -> (MessageClientVerificationConfigDTO.ConsumerDTO) MessageClientVerificationConfigDTO.ConsumerDTO.builder()
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
    private Map<String, String> verifyClientConfig(MqttV3ClientConfigDTO.ClientDTO clientDTO) {
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