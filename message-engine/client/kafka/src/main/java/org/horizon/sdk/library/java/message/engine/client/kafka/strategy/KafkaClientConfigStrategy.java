package org.horizon.sdk.library.java.message.engine.client.kafka.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConsumer;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientIdGenerateType;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.support.MessageClientFlows;
import org.horizon.sdk.library.java.message.engine.client.kafka.KafkaConfig;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.dto.KafkaClientConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.enums.KafkaConsumerAdapterAckMode;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.enums.KafkaConsumerAdapterListenerMode;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.property.KafkaClientProperty;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.time.Times;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.dsl.KafkaMessageDrivenChannelAdapterSpec;
import org.springframework.integration.kafka.dsl.KafkaProducerMessageHandlerSpec;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wjm
 * @since 2024-06-04 14:16
 */
@Slf4j
public class KafkaClientConfigStrategy<K, V> extends MessageClientConfigStrategy<KafkaClientProperty, KafkaClientConfigDTO, KafkaClientConfigDTO.BrokerDTO, KafkaClientConfigDTO.ClientDTO, KafkaClientConfigDTO.ProducerDTO, KafkaClientConfigDTO.ConsumerDTO> {

    @Autowired KafkaProperties kafkaProperties;

    @Autowired KafkaClientProperty kafkaProperty;

    @Override
    protected Class<KafkaClientConfigDTO> getConfigType() {
        return KafkaClientConfigDTO.class;
    }

    @Override
    protected Class<KafkaClientProperty> getPropertyType() {
        return KafkaClientProperty.class;
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        return MessageClientType.KAFKA;
    }

    @Override
    protected KafkaClientConfigDTO getMessageConfigDTO() {
        return Springs.getBean(KafkaClientConfigDTO.class);
    }

    @Override
    protected KafkaClientConfigDTO.BrokerDTO getBrokerDTO() {
        return KafkaClientConfigDTO.BrokerDTO.builder().serverUrls(this.kafkaProperty.getServerUrls()).build();
    }

    @Override
    protected KafkaClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        KafkaConfig.ClientConfig clientConfigAnnotation = (KafkaConfig.ClientConfig) clientConfig;
        String flowId = MessageClientFlows.getStaticFlowId(MessageClientType.KAFKA, executeMethod);
        MessageClientIdGenerateType idGenerateType = clientConfigAnnotation.idGenerateType();
        return KafkaClientConfigDTO.ClientDTO.builder()
                .clientId(MessageClientFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .build();
    }

    @Override
    protected KafkaClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        return KafkaClientConfigDTO.ProducerDTO.builder()
                .clientDTO(getClientDTO(producerAnnotation.config().kafka().clientConfig(), executeMethod))
                .topic(producerAnnotation.topic())
                .dynamicIs(computeDynamicIs(producerAnnotation.topic()))
                .build();
    }

    @Override
    protected KafkaClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        KafkaConfig.ConsumerConfig consumerConfig = consumerAnnotation.config().kafka().consumerConfig();
        return KafkaClientConfigDTO.ConsumerDTO.builder()
                .clientDTO(getClientDTO(consumerAnnotation.config().kafka().clientConfig(), executeMethod))
                .forwardProducerDTO(forwardProducerDTO)
                .topics(Converts.toList(consumerAnnotation.topics()))
                .groupId(consumerConfig.groupId())
                .allowToAutoCreateTopic(consumerConfig.allowToAutoCreateTopic())
                .ackMode(consumerConfig.ackMode())
                .autoCommitOffsetInterval(consumerConfig.autoCommitOffsetInterval())
                .listenerMode(consumerConfig.listenerMode())
                .offsetResetMode(consumerConfig.offsetResetMode())
                .build();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected IntegrationFlow getProducerFlow(KafkaClientConfigDTO.ProducerDTO producerDTO) {
        DefaultKafkaProducerFactory<K, V> producerFactory = Springs.getBean(DefaultKafkaProducerFactory.class.getName(), DefaultKafkaProducerFactory.class);
        KafkaProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec<K, V> messageHandler = Kafka.outboundChannelAdapter(producerFactory).topic(producerDTO.getTopic());
        return MessageClientFlows.getObjectToStringIntegrationFlow(messageHandler);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected IntegrationFlow getConsumerFlow(KafkaClientConfigDTO.ConsumerDTO consumerDTO) {
        ConsumerFactory<K, V> consumerFactory = Springs.getBean(consumerDTO.getClientDTO().getFlowId(), ConsumerFactory.class);
        KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec<K, V> messageDrivenChannelAdapter = Kafka.messageDrivenChannelAdapter(
                        consumerFactory,
                        consumerDTO.getNativeListenerMode(),
                        Converts.toArray(consumerDTO.getTopics(), String.class)
                )
                .configureListenerContainer(kafkaMessageListenerContainerSpec -> kafkaMessageListenerContainerSpec
                        .ackMode(consumerDTO.getNativeAckMode())
                        .id(consumerDTO.getClientDTO().getClientId())
                );

        Object consumerInstance = Springs.getBean(consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass());
        return IntegrationFlow.from(messageDrivenChannelAdapter)
                .handle(MessageClientFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                .get();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected void completeNativeConfigDTO(KafkaClientConfigDTO configDTO) {
        List<KafkaClientConfigDTO.ConsumerDTO> consumerDTOs = (List<KafkaClientConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        consumerDTOs.forEach(consumerDTO -> consumerDTO
                .setNativeGroupId(consumerDTO.getGroupId())
                .setNativeAllowAutoCreateTopics(Converts.toString(consumerDTO.isAllowToAutoCreateTopic()))
                .setNativeAckMode(KafkaConsumerAdapterAckMode.fromAckMode(consumerDTO.getAckMode()).getKafkaAckMode())
                .setNativeEnableAutoCommit(Converts.toString(KafkaConsumerAdapterAckMode.fromAckMode(consumerDTO.getAckMode()).getStrategy().needToEnableAutoCommitOffset()))
                .setNativeAutoCommitIntervalMs(Converts.toString(Times.wrapper(consumerDTO.getAutoCommitOffsetInterval()).toMillisecond().toMillis()))
                .setNativeListenerMode(KafkaConsumerAdapterListenerMode.fromListenerMode(consumerDTO.getListenerMode()).getKafkaListenerMode())
                .setNativeAutoOffsetReset(consumerDTO.getOffsetResetMode().getCode())
        );
    }

    @Override
    protected void registerClientFactory(KafkaClientConfigDTO.BrokerDTO brokerDTO) {
        this.kafkaProperties.setBootstrapServers(brokerDTO.getServerUrls());
        DefaultKafkaProducerFactory<K, V> producerFactory = new DefaultKafkaProducerFactory<>(this.kafkaProperties.buildProducerProperties(null));
        Springs.registerBean(DefaultKafkaProducerFactory.class.getName(), producerFactory);
    }

    @Override
    protected void registerProducerFactory(KafkaClientConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(KafkaClientConfigDTO.ConsumerDTO consumerDTO) {
        Springs.registerBean(consumerDTO.getClientDTO().getFlowId(), new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaProperty.getServerUrls(),
                ConsumerConfig.GROUP_ID_CONFIG, consumerDTO.getNativeGroupId(),
                ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, consumerDTO.getNativeAllowAutoCreateTopics(),
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerDTO.getNativeEnableAutoCommit(),
                ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerDTO.getNativeAutoCommitIntervalMs(),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerDTO.getNativeAutoOffsetReset(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
        )));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(KafkaClientConfigDTO configDTO) {
        MessageClientVerificationConfigDTO verificationConfigDTO = new MessageClientVerificationConfigDTO();

        List<KafkaClientConfigDTO.ConsumerDTO> consumerDTOs = (List<KafkaClientConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        verificationConfigDTO.setConsumerFailedReasons(consumerDTOs
                .stream()
                .map(consumerDTO -> {
                    Map<String, String> consumerFailedReasons = Collections.newHashMap();
                    if (Nil.isBlank(consumerDTO.getGroupId())) {
                        consumerFailedReasons.put("invalid group id", "the group id should not be blank, please check!");
                    }
                    return (MessageClientVerificationConfigDTO.ConsumerDTO) MessageClientVerificationConfigDTO.ConsumerDTO.builder()
                            .methodPoint(consumerDTO.getClientDTO().getFlowId())
                            .failedReason(consumerFailedReasons)
                            .build();
                })
                .filter(consumerFailedReasonDTO -> Nil.isNotEmpty(consumerFailedReasonDTO.getFailedReason()))
                .toList()
        );

        return verificationConfigDTO;
    }

}