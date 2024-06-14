// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.strategy;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.kafka.KafkaConfig;
import cn.srd.library.java.message.engine.kafka.model.dto.KafkaConfigDTO;
import cn.srd.library.java.message.engine.kafka.model.enums.KafkaConsumerAdapterAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.KafkaConsumerAdapterListenerMode;
import cn.srd.library.java.message.engine.kafka.model.property.KafkaProperty;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
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
public class KafkaConfigStrategy<K, V> extends MessageConfigStrategy<KafkaProperty, KafkaConfigDTO, KafkaConfigDTO.BrokerDTO, KafkaConfigDTO.ClientDTO, KafkaConfigDTO.ProducerDTO, KafkaConfigDTO.ConsumerDTO> {

    @Autowired KafkaProperties kafkaProperties;

    @Autowired KafkaProperty kafkaProperty;

    @Override
    protected Class<KafkaConfigDTO> getConfigType() {
        return KafkaConfigDTO.class;
    }

    @Override
    protected Class<KafkaProperty> getPropertyType() {
        return KafkaProperty.class;
    }

    @Override
    protected KafkaConfigDTO.BrokerDTO getBrokerDTO() {
        return KafkaConfigDTO.BrokerDTO.builder().serverUrls(this.kafkaProperty.getServerUrls()).build();
    }

    @Override
    protected KafkaConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        KafkaConfig.ClientConfig clientConfigAnnotation = (KafkaConfig.ClientConfig) clientConfig;
        String flowId = MessageFlows.getFlowId(MessageEngineType.KAFKA, executeMethod);
        ClientIdGenerateType idGenerateType = clientConfigAnnotation.idGenerateType();
        return KafkaConfigDTO.ClientDTO.builder()
                .clientId(MessageFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .build();
    }

    @Override
    protected KafkaConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        return KafkaConfigDTO.ProducerDTO.builder()
                .clientDTO(getClientDTO(producerAnnotation.config().kafka().clientConfig(), executeMethod))
                .topic(producerAnnotation.topic())
                .build();
    }

    @Override
    protected KafkaConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        KafkaConfig.ConsumerConfig consumerConfig = consumerAnnotation.config().kafka().consumerConfig();
        return KafkaConfigDTO.ConsumerDTO.builder()
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
    protected IntegrationFlow getProducerFlow(KafkaConfigDTO.ProducerDTO producerDTO) {
        DefaultKafkaProducerFactory<K, V> producerFactory = Springs.getBean(DefaultKafkaProducerFactory.class.getName(), DefaultKafkaProducerFactory.class);
        KafkaProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec<K, V> messageHandler = Kafka.outboundChannelAdapter(producerFactory).topic(producerDTO.getTopic());
        return MessageFlows.getObjectToStringIntegrationFlow(messageHandler);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected IntegrationFlow getConsumerFlow(KafkaConfigDTO.ConsumerDTO consumerDTO) {
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
                .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                .get();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected void completeNativeConfigDTO(KafkaConfigDTO configDTO) {
        List<KafkaConfigDTO.ConsumerDTO> consumerDTOs = (List<KafkaConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
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
    protected void registerClientFactory(KafkaConfigDTO.BrokerDTO brokerDTO) {
        this.kafkaProperties.setBootstrapServers(brokerDTO.getServerUrls());
        DefaultKafkaProducerFactory<K, V> producerFactory = new DefaultKafkaProducerFactory<>(this.kafkaProperties.buildProducerProperties(null));
        Springs.registerBean(DefaultKafkaProducerFactory.class.getName(), producerFactory);
    }

    @Override
    protected void registerProducerFactory(KafkaConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(KafkaConfigDTO.ConsumerDTO consumerDTO) {
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
    protected MessageVerificationConfigDTO getVerificationConfigDTO(KafkaConfigDTO configDTO) {
        MessageVerificationConfigDTO verificationConfigDTO = new MessageVerificationConfigDTO();

        List<KafkaConfigDTO.ConsumerDTO> consumerDTOs = (List<KafkaConfigDTO.ConsumerDTO>) configDTO.getConsumerDTOs();
        verificationConfigDTO.setConsumerFailedReasons(consumerDTOs
                .stream()
                .map(consumerDTO -> {
                    Map<String, String> consumerFailedReasons = Collections.newHashMap();
                    if (Nil.isBlank(consumerDTO.getGroupId())) {
                        consumerFailedReasons.put("invalid group id", "the group id should not be blank, please check!");
                    }
                    return (MessageVerificationConfigDTO.ConsumerDTO) MessageVerificationConfigDTO.ConsumerDTO.builder()
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