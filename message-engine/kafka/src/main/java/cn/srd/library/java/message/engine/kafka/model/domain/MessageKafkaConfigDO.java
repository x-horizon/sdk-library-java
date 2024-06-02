// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model.domain;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.domain.MessageConfigDO;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.kafka.model.enums.*;
import cn.srd.library.java.message.engine.kafka.model.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.dsl.KafkaMessageDrivenChannelAdapterSpec;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.io.Serial;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-01 09:57
 */
@Slf4j
@Getter
public class MessageKafkaConfigDO<K, V> extends MessageConfigDO {

    @Serial private static final long serialVersionUID = 1721566725873588079L;

    @JsonProperty("kafkaBrokerInfo")
    private final BrokerDO brokerDO;

    @JsonProperty("kafkaProducerInfo")
    private final List<ProducerDO> producerDOs;

    @JsonProperty("kafkaConsumerInfos")
    private final List<ConsumerDO<K, V>> consumerDOs;

    @JsonIgnore
    private final Map<Method, ProducerDO> producerRouters;

    @JsonIgnore
    private final Map<Method, ConsumerDO<K, V>> consumerRouters;

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public MessageKafkaConfigDO() {
        log.info("{}message engine kafka customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        this.brokerDO = new BrokerDO();
        this.producerRouters = Annotations.getAnnotatedMethods(MessageProducer.class)
                .stream()
                .filter(producerMethod -> Comparators.equals(MessageEngineType.KAFKA, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> Collections.ofPair(producerMethod, new ProducerDO(producerMethod)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        this.consumerRouters = Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.KAFKA, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> Collections.ofPair(consumerMethod, new ConsumerDO<>(consumerMethod)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> (ConsumerDO<K, V>) entry.getValue()));
        this.producerDOs = Collections.getMapValues(producerRouters);
        this.consumerDOs = Collections.getMapValues(consumerRouters);

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
                Converts.withJackson().toStringFormatted(this.brokerDO),
                Converts.withJackson().toStringFormatted(this.producerDOs),
                Converts.withJackson().toStringFormatted(this.consumerDOs)
        );
        log.info("{}message engine kafka customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

    public ProducerDO getProducerConfigDO(Method producerMethod) {
        return producerRouters.get(producerMethod);
    }

    public ConsumerDO<K, V> getConsumerConfigDO(Method consumerMethod) {
        return consumerRouters.get(consumerMethod);
    }

    @Getter
    public static class BrokerDO extends MessageConfigDO.BrokerDO {

        @Serial private static final long serialVersionUID = 6478172587588977016L;

        private final List<String> serverUrls;

        BrokerDO() {
            MessageEngineKafkaProperties kafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
            this.serverUrls = kafkaProperties.getServerUrls();
        }

    }

    @Getter
    private static class ClientDO extends MessageConfigDO.ClientDO {

        @Serial private static final long serialVersionUID = 7833921985689603695L;

        protected String clientId;

        protected String flowId;

        protected ClientIdGenerateType idGenerateType;

        ClientDO() {

        }

    }

    @Getter
    public static class ProducerDO extends ClientDO {

        @Serial private static final long serialVersionUID = -7957770669868555274L;

        ProducerDO(Method producerMethod) {
            this.executeMethod = producerMethod;
            this.flowId = MessageFlows.getFlowId(MessageEngineType.KAFKA, producerMethod);

            MessageProducer producerAnnotation = producerMethod.getAnnotation(MessageProducer.class);
            MessageKafkaConfig.ClientConfig clientConfig = producerAnnotation.config().kafka().clientConfig();
            this.idGenerateType = clientConfig.idGenerateType();
            this.clientId = MessageFlows.getDistributedUniqueClientId(this.idGenerateType, this.flowId);
        }

    }

    @Getter
    public static class ConsumerDO<K, V> extends ClientDO {

        @Serial private static final long serialVersionUID = -3363832601343322864L;

        private final String groupId;

        private final List<String> topics;

        private final boolean allowToAutoCreateTopic;

        private final MessageKafkaConsumerAckMode ackMode;

        private final String autoCommitOffsetInterval;

        private final MessageKafkaConsumerListenerMode listenerMode;

        private final MessageKafkaConsumerOffsetResetMode offsetResetMode;

        @JsonProperty("kafka_auto.offset.reset")
        private final String originalAutoOffsetReset;

        @JsonProperty("kafka_group.id")
        private final String originalGroupId;

        @JsonProperty("kafka_allow.auto.create.topics")
        private final String originalAllowAutoCreateTopics;

        @JsonProperty("kafka_listenerMode")
        private final KafkaMessageDrivenChannelAdapter.ListenerMode originalListenerMode;

        @JsonProperty("kafka_ackMode")
        private final ContainerProperties.AckMode originalAckMode;

        @JsonProperty("kafka_enable.auto.commit")
        private final String originalEnableAutoCommit;

        @JsonProperty("kafka_auto.commit.interval.ms")
        private final String originalAutoCommitIntervalMs;

        ConsumerDO(Method consumerMethod) {
            this.flowId = MessageFlows.getFlowId(MessageEngineType.KAFKA, consumerMethod);
            this.executeMethod = consumerMethod;

            MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
            this.topics = Converts.toList(consumerAnnotation.topics());

            MessageKafkaConfig.ClientConfig clientConfig = consumerAnnotation.config().kafka().clientConfig();
            this.idGenerateType = clientConfig.idGenerateType();
            this.clientId = MessageFlows.getDistributedUniqueClientId(this.idGenerateType, this.flowId);

            MessageKafkaConfig.ConsumerConfig consumerConfig = consumerAnnotation.config().kafka().consumerConfig();
            this.groupId = consumerConfig.groupId();
            this.allowToAutoCreateTopic = consumerConfig.allowToAutoCreateTopic();
            this.ackMode = consumerConfig.ackMode();
            this.autoCommitOffsetInterval = consumerConfig.autoCommitOffsetInterval();
            this.listenerMode = consumerConfig.listenerMode();
            this.offsetResetMode = consumerConfig.offsetResetMode();
            this.originalGroupId = consumerConfig.groupId();
            this.originalAllowAutoCreateTopics = Converts.toString(consumerConfig.allowToAutoCreateTopic());
            this.originalAckMode = MessageKafkaConsumerAdapterAckMode.fromAckMode(consumerConfig.ackMode()).getKafkaAckMode();
            this.originalEnableAutoCommit = Converts.toString(MessageKafkaConsumerAdapterAckMode.fromAckMode(consumerConfig.ackMode()).getStrategy().needToEnableAutoCommitOffset());
            this.originalAutoCommitIntervalMs = Converts.toString(Times.wrapper(consumerConfig.autoCommitOffsetInterval()).toMillisecond().toMillis());
            this.originalListenerMode = MessageKafkaConsumerAdapterListenerMode.fromListenerMode(consumerConfig.listenerMode()).getKafkaListenerMode();
            this.originalAutoOffsetReset = consumerConfig.offsetResetMode().getCode();

            registerFactory();
            registerFlow();
        }

        private void registerFactory() {
            MessageEngineKafkaProperties kafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
            ConsumerFactory<K, V> consumerFactory = new DefaultKafkaConsumerFactory<>(Map.of(
                    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServerUrls(),
                    ConsumerConfig.GROUP_ID_CONFIG, this.originalGroupId,
                    ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, this.originalAllowAutoCreateTopics,
                    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, this.originalEnableAutoCommit,
                    ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, this.originalAutoCommitIntervalMs,
                    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.originalAutoOffsetReset,
                    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
            ));
            Springs.registerBean(MessageFlows.getFlowId(MessageEngineType.KAFKA, this.executeMethod), consumerFactory);
        }

        @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
        private void registerFlow() {
            IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
            if (Nil.isNull(flowContext.getRegistrationById(this.flowId))) {
                ConsumerFactory<K, V> consumerFactory = Springs.getBean(MessageFlows.getFlowId(MessageEngineType.KAFKA, this.executeMethod), ConsumerFactory.class);
                KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec<K, V> messageDrivenChannelAdapter = Kafka.messageDrivenChannelAdapter(
                                consumerFactory,
                                this.originalListenerMode,
                                Converts.toArray(this.topics, String.class)
                        )
                        .configureListenerContainer(kafkaMessageListenerContainerSpec -> kafkaMessageListenerContainerSpec
                                .ackMode(this.originalAckMode)
                                .id(this.clientId)
                        );
                // .recoveryCallback(new ErrorMessageSendingRecoverer(errorChannel(), new RawRecordHeaderErrorMessageStrategy()))
                // .retryTemplate(new RetryTemplate())
                // .filterInRetry(true);

                Object consumerInstance = Springs.getBean(executeMethod.getDeclaringClass());
                // TODO wjm 为什么是 MessageProducerSupport ？
                IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                        .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, executeMethod))
                        .get();
                Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, executeMethod.getDeclaringClass().getName())
                        .setThrowable(LibraryJavaInternalException.class)
                        .throwsIfNull(consumerInstance);
                flowContext
                        .registration(flow)
                        .id(this.flowId)
                        .useFlowIdAsPrefix()
                        .register();

                // TODO wjm 有两种：KafkaMessageListenerContainer、ConcurrentMessageListenerContainer，应有配置文件配置，在 https://docs.spring.io/spring-integration/reference/kafka.html#java-dsl-configuration 直接搜索 KafkaMessageListenerContainer 即可看到介绍
                // TODO wjm KafkaMessageDrivenChannelAdapter.ListenerMode.record
                // KafkaMessageDrivenChannelAdapter<K, V> messageDrivenChannelAdapter = new KafkaMessageDrivenChannelAdapter<>(Springs.getBean(KafkaMessageListenerContainer.class), KafkaMessageDrivenChannelAdapter.ListenerMode.record);
                // messageDrivenChannelAdapter.setQos(Arrays.stream(consumerAnnotation.qos()).mapToInt(MessageQosType::getStatus).toArray());
                // messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
                // messageDrivenChannelAdapter.setCompletionTimeout(consumerAnnotation.completionTimeout());
                // messageDrivenChannelAdapter.setDisconnectCompletionTimeout(consumerAnnotation.disconnectCompletionTimeout());
            }
        }

    }

}