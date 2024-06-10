// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.kafka.model.dto.MessageKafkaConfigDTO;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAdapterAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAdapterListenerMode;
import cn.srd.library.java.message.engine.kafka.model.properties.MessageKafkaProperties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.dsl.KafkaMessageDrivenChannelAdapterSpec;
import org.springframework.integration.kafka.dsl.KafkaProducerMessageHandlerSpec;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wjm
 * @since 2024-06-04 14:16
 */
@Slf4j
public class MessageKafkaConfigStrategy<K, V> extends MessageConfigStrategy<MessageKafkaConfigDTO, MessageKafkaConfigDTO.BrokerDTO, MessageKafkaConfigDTO.ClientDTO, MessageKafkaConfigDTO.ProducerDTO, MessageKafkaConfigDTO.ConsumerDTO> {

    @Override
    protected Class<MessageKafkaConfigDTO> getConfigType() {
        return MessageKafkaConfigDTO.class;
    }

    @Override
    protected MessageKafkaConfigDTO.BrokerDTO getBrokerDTO() {
        MessageKafkaProperties kafkaProperties = Springs.getBean(MessageKafkaProperties.class);
        Assert.of().setMessage("{}could not find the kafka server url, please provide the kafka server url in the config yaml, see [{}].", ModuleView.MESSAGE_ENGINE_SYSTEM, MessageKafkaProperties.class.getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(kafkaProperties.getServerUrls());
        return MessageKafkaConfigDTO.BrokerDTO.builder().serverUrls(kafkaProperties.getServerUrls()).build();
    }

    @Override
    protected void registerClientFactory(MessageKafkaConfigDTO.BrokerDTO brokerDTO) {
        KafkaProperties kafkaProperties = Springs.getBean(KafkaProperties.class);
        kafkaProperties.setBootstrapServers(brokerDTO.getServerUrls());
        DefaultKafkaProducerFactory<K, V> producerFactory = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(null));
        Springs.registerBean(DefaultKafkaProducerFactory.class.getName(), producerFactory);
    }

    @Override
    protected MessageKafkaConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        MessageKafkaConfig.ClientConfig clientConfigAnnotation = (MessageKafkaConfig.ClientConfig) clientConfig;
        String flowId = MessageFlows.getFlowId(MessageEngineType.KAFKA, executeMethod);
        ClientIdGenerateType idGenerateType = clientConfigAnnotation.idGenerateType();
        return MessageKafkaConfigDTO.ClientDTO.builder()
                .clientId(MessageFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .build();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected void registerProducerFlow(MessageKafkaConfigDTO.ProducerDTO producerDTO) {
        IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
        if (Nil.isNull(flowContext.getRegistrationById(producerDTO.getClientDTO().getFlowId()))) {
            DefaultKafkaProducerFactory<K, V> producerFactory = Springs.getBean(DefaultKafkaProducerFactory.class.getName(), DefaultKafkaProducerFactory.class);
            KafkaProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec<K, V> messageHandler = Kafka.outboundChannelAdapter(producerFactory).topic(producerDTO.getTopic());
            flowContext.registration(MessageFlows.getObjectToStringIntegrationFlow(messageHandler))
                    .id(producerDTO.getClientDTO().getFlowId())
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    protected void registerConsumerFlow(MessageKafkaConfigDTO.ConsumerDTO consumerDTO) {
        IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
        if (Nil.isNull(flowContext.getRegistrationById(consumerDTO.getClientDTO().getFlowId()))) {
            ConsumerFactory<K, V> consumerFactory = Springs.getBean(MessageFlows.getFlowId(MessageEngineType.KAFKA, consumerDTO.getClientDTO().getExecuteMethod()), ConsumerFactory.class);
            KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec<K, V> messageDrivenChannelAdapter = Kafka.messageDrivenChannelAdapter(
                            consumerFactory,
                            consumerDTO.getOriginalListenerMode(),
                            Converts.toArray(consumerDTO.getTopics(), String.class)
                    )
                    .configureListenerContainer(kafkaMessageListenerContainerSpec -> kafkaMessageListenerContainerSpec
                            .ackMode(consumerDTO.getOriginalAckMode())
                            .id(consumerDTO.getClientDTO().getClientId())
                    );

            Object consumerInstance = Springs.getBean(consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass());
            IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                    .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                    .get();
            Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass().getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(consumerInstance);
            flowContext.registration(flow)
                    .id(consumerDTO.getClientDTO().getFlowId())
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

    @Override
    protected MessageKafkaConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        return MessageKafkaConfigDTO.ProducerDTO.builder()
                .clientDTO(getClientDTO(producerAnnotation.config().kafka().clientConfig(), executeMethod))
                .engineType(producerAnnotation.config().engineType())
                .topic(producerAnnotation.topic())
                .build();
    }

    @Override
    protected MessageKafkaConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        MessageKafkaConfig.ConsumerConfig consumerConfig = consumerAnnotation.config().kafka().consumerConfig();
        return MessageKafkaConfigDTO.ConsumerDTO.builder()
                .clientDTO(getClientDTO(consumerAnnotation.config().kafka().clientConfig(), executeMethod))
                .forwardProducerDTO(forwardProducerDTO)
                .topics(Converts.toList(consumerAnnotation.topics()))
                .groupId(consumerConfig.groupId())
                .allowToAutoCreateTopic(consumerConfig.allowToAutoCreateTopic())
                .ackMode(consumerConfig.ackMode())
                .autoCommitOffsetInterval(consumerConfig.autoCommitOffsetInterval())
                .listenerMode(consumerConfig.listenerMode())
                .offsetResetMode(consumerConfig.offsetResetMode())
                .originalGroupId(consumerConfig.groupId())
                .originalAllowAutoCreateTopics(Converts.toString(consumerConfig.allowToAutoCreateTopic()))
                .originalAckMode(MessageKafkaConsumerAdapterAckMode.fromAckMode(consumerConfig.ackMode()).getKafkaAckMode())
                .originalEnableAutoCommit(Converts.toString(MessageKafkaConsumerAdapterAckMode.fromAckMode(consumerConfig.ackMode()).getStrategy().needToEnableAutoCommitOffset()))
                .originalAutoCommitIntervalMs(Converts.toString(Times.wrapper(consumerConfig.autoCommitOffsetInterval()).toMillisecond().toMillis()))
                .originalListenerMode(MessageKafkaConsumerAdapterListenerMode.fromListenerMode(consumerConfig.listenerMode()).getKafkaListenerMode())
                .originalAutoOffsetReset(consumerConfig.offsetResetMode().getCode())
                .build();
    }

    @Override
    protected void registerConsumerFactory(MessageKafkaConfigDTO.ConsumerDTO consumerDTO) {
        MessageKafkaProperties kafkaProperties = Springs.getBean(MessageKafkaProperties.class);
        ConsumerFactory<K, V> consumerFactory = new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServerUrls(),
                ConsumerConfig.GROUP_ID_CONFIG, consumerDTO.getOriginalGroupId(),
                ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, consumerDTO.getOriginalAllowAutoCreateTopics(),
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerDTO.getOriginalEnableAutoCommit(),
                ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerDTO.getOriginalAutoCommitIntervalMs(),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerDTO.getOriginalAutoOffsetReset(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
        ));
        Springs.registerBean(MessageFlows.getFlowId(MessageEngineType.KAFKA, consumerDTO.getClientDTO().getExecuteMethod()), consumerFactory);
    }

}