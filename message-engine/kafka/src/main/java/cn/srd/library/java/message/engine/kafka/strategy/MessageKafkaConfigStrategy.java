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
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Annotations;
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

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-04 14:16
 */
@Slf4j
public class MessageKafkaConfigStrategy<K, V> extends MessageConfigStrategy<MessageKafkaConfigDTO, MessageKafkaConfigDTO.BrokerDTO, MessageKafkaConfigDTO.ClientDTO, MessageKafkaConfigDTO.ProducerDTO, MessageKafkaConfigDTO.ConsumerDTO> {

    @Override
    public MessageKafkaConfigDTO initialize() {
        log.info("{}message engine kafka customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        MessageKafkaConfigDTO.BrokerDTO brokerDTO = registerBroker();
        Map<Method, MessageKafkaConfigDTO.ProducerDTO> producerRouter = registerProducerRouter();
        Map<Method, MessageKafkaConfigDTO.ConsumerDTO> consumerRouter = registerConsumerRouter();
        MessageKafkaConfigDTO kafkaConfigDTO = MessageKafkaConfigDTO.builder()
                .brokerDTO(brokerDTO)
                .producerRouter(producerRouter)
                .producerDTOs(Collections.getMapValues(producerRouter))
                .consumerRouter(consumerRouter)
                .consumerDTOs(Collections.getMapValues(consumerRouter))
                .build();

        log.info("""
                        {}message engine kafka customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        Kafka Broker Info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        Kafka Producer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        Kafka Consumer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                Converts.withJackson().toStringFormatted(kafkaConfigDTO.getBrokerDTO()),
                Converts.withJackson().toStringFormatted(kafkaConfigDTO.getProducerDTOs()),
                Converts.withJackson().toStringFormatted(kafkaConfigDTO.getConsumerDTOs())
        );
        log.info("{}message engine kafka customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);

        return kafkaConfigDTO;
    }

    private MessageKafkaConfigDTO.BrokerDTO registerBroker() {
        MessageKafkaProperties kafkaProperties = Springs.getBean(MessageKafkaProperties.class);
        Assert.of().setMessage("{}could not find the kafka server url, please provide the kafka server url in the config yaml, see [{}].", ModuleView.MESSAGE_ENGINE_SYSTEM, MessageKafkaProperties.class.getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(kafkaProperties.getServerUrls());
        MessageKafkaConfigDTO.BrokerDTO brokerDTO = MessageKafkaConfigDTO.BrokerDTO.builder().serverUrls(kafkaProperties.getServerUrls()).build();
        registerProducerFactory(brokerDTO);
        return brokerDTO;
    }

    private MessageKafkaConfigDTO.ClientDTO registerClient(MessageKafkaConfig.ClientConfig clientConfig, Method executeMethod) {
        String flowId = MessageFlows.getFlowId(MessageEngineType.KAFKA, executeMethod);
        ClientIdGenerateType idGenerateType = clientConfig.idGenerateType();
        return MessageKafkaConfigDTO.ClientDTO.builder()
                .clientId(MessageFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .build();
    }

    private Map<Method, MessageKafkaConfigDTO.ProducerDTO> registerProducerRouter() {
        return registerProducerRouter(Annotations.getAnnotatedMethods(MessageProducer.class));
    }

    private Map<Method, MessageKafkaConfigDTO.ProducerDTO> registerProducerRouter(Collection<Method> producerMethods) {
        return producerMethods.stream()
                .filter(producerMethod -> Comparators.equals(MessageEngineType.KAFKA, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> Collections.ofPair(producerMethod, registerProducer(producerMethod)))
                .peek(producerRouter -> registerProducerFlow(producerRouter.getValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    public void registerProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO) {
        Map<Method, MessageKafkaConfigDTO.ProducerDTO> producerRouter = (Map<Method, MessageKafkaConfigDTO.ProducerDTO>) Springs.getBean(MessageKafkaConfigDTO.class).getProducerRouter();
        producerRouter.put(executeMethod, (MessageKafkaConfigDTO.ProducerDTO) producerDTO);
        registerProducerFlow((MessageKafkaConfigDTO.ProducerDTO) producerDTO);
    }

    @Override
    public void onInitializeComplete() {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.KAFKA, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .forEach(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    consumerAnnotation.forwardTo()
                            .config()
                            .engineType()
                            .getConfigStrategy()
                            .registerProducerRouter(consumerMethod, Springs.getBean(MessageKafkaConfigDTO.class).getConsumerRouter().get(consumerMethod).getForwardProducerDTO());
                });
    }

    private MessageKafkaConfigDTO.ProducerDTO registerProducer(Method producerMethod) {
        return registerProducer(producerMethod, producerMethod.getAnnotation(MessageProducer.class));
    }

    @Override
    public MessageKafkaConfigDTO.ProducerDTO registerProducer(Method executeMethod, MessageProducer producerAnnotation) {
        return MessageKafkaConfigDTO.ProducerDTO.builder()
                .clientDTO(registerClient(producerAnnotation.config().kafka().clientConfig(), executeMethod))
                .engineType(producerAnnotation.config().engineType())
                .topic(producerAnnotation.topic())
                .build();
    }

    private void registerProducerFactory(MessageKafkaConfigDTO.BrokerDTO brokerDTO) {
        KafkaProperties kafkaProperties = Springs.getBean(KafkaProperties.class);
        kafkaProperties.setBootstrapServers(brokerDTO.getServerUrls());
        DefaultKafkaProducerFactory<K, V> producerFactory = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(null));
        Springs.registerBean(DefaultKafkaProducerFactory.class.getName(), producerFactory);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private void registerProducerFlow(MessageKafkaConfigDTO.ProducerDTO producerDTO) {
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

    private Map<Method, MessageKafkaConfigDTO.ConsumerDTO> registerConsumerRouter() {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.KAFKA, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageConfigDTO.ProducerDTO forwardProducerDTO = consumerAnnotation.forwardTo()
                            .config()
                            .engineType()
                            .getConfigStrategy()
                            .registerProducer(consumerMethod, consumerAnnotation.forwardTo());

                    MessageKafkaConfig.ConsumerConfig consumerConfig = consumerAnnotation.config().kafka().consumerConfig();
                    MessageKafkaConfigDTO.ConsumerDTO consumerDTO = MessageKafkaConfigDTO.ConsumerDTO.builder()
                            .clientDTO(registerClient(consumerAnnotation.config().kafka().clientConfig(), consumerMethod))
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
                    registerConsumerFactory(consumerDTO);
                    registerConsumerFlow(consumerDTO);
                    return Collections.ofPair(consumerMethod, consumerDTO);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private void registerConsumerFactory(MessageKafkaConfigDTO.ConsumerDTO consumerDTO) {
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

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private void registerConsumerFlow(MessageKafkaConfigDTO.ConsumerDTO consumerDTO) {
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

}