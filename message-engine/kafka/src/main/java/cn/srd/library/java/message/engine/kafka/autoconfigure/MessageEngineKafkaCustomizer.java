// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.kafka.model.MessageKafkaConsumerConfig;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAdapterAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAdapterListenerMode;
import cn.srd.library.java.message.engine.kafka.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.dsl.KafkaMessageDrivenChannelAdapterSpec;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the global message engine kafka customizer
 *
 * @author wjm
 * @since 2024-05-30 11:13
 */
@Slf4j
@Getter
public class MessageEngineKafkaCustomizer<K, V> {

    @Autowired private IntegrationFlowContext flowContext;

    private ClientIdGenerateType clientIdGenerateType;

    @PostConstruct
    public void initialize() {
        log.info("{}message engine kafka customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        EnableMessageEngineKafka kafkaCustomizer = Annotations.getAnnotation(EnableMessageEngineKafka.class);
        this.clientIdGenerateType = kafkaCustomizer.clientIdGenerateType();

        List<MessageKafkaConsumerConfig> consumerConfigs = registerConsumer();

        log.info(""" 
                        {}message engine kafka customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        Client Id Generate Config:
                           generateType                            = [{}]
                        Controller Broker Server Info:
                           serverUrls                              = [{}]
                        Consumer Info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                kafkaCustomizer.clientIdGenerateType().name(),
                Strings.join(Springs.getBean(MessageEngineKafkaProperties.class).getServerUrls(), SymbolConstant.COMMA + SymbolConstant.SPACE),
                Converts.withJackson().toStringFormatted(consumerConfigs)
        );

        log.info("{}message engine kafka customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

    private List<MessageKafkaConsumerConfig> registerConsumer() {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.KAFKA, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageKafkaConsumerConfig consumerConfig = buildConsumerConfig(consumerMethod);
                    ConsumerFactory<K, V> consumerFactory = registerConsumerFactory(consumerMethod, consumerConfig);
                    registerConsumerFlow(consumerMethod, consumerConfig, consumerFactory);
                    return consumerConfig;
                })
                .toList();
    }

    private MessageKafkaConsumerConfig buildConsumerConfig(Method consumerMethod) {
        MessageConsumer consumerConfig = consumerMethod.getAnnotation(MessageConsumer.class);
        MessageKafkaConfig kafkaConfig = consumerConfig.config().kafka();
        MessageKafkaConfig.ConsumerConfig kafkaConsumerConfig = kafkaConfig.consumerConfig();
        MessageKafkaConsumerAdapterListenerMode listenerMode = MessageKafkaConsumerAdapterListenerMode.fromListenerMode(kafkaConsumerConfig.listenerMode());
        MessageKafkaConsumerAdapterAckMode consumerAckMode = MessageKafkaConsumerAdapterAckMode.fromAckMode(kafkaConsumerConfig.ackMode());
        return MessageKafkaConsumerConfig.builder()
                .consumerPointer(MessageFlows.getFlowId(MessageEngineType.KAFKA, consumerMethod))
                .groupId(kafkaConsumerConfig.groupId())
                .topics(Converts.toList(consumerConfig.topic()))
                .allowToAutoCreateTopic(kafkaConsumerConfig.allowToAutoCreateTopic())
                .ackMode(kafkaConsumerConfig.ackMode())
                .autoCommitOffsetInterval(kafkaConsumerConfig.autoCommitOffsetInterval())
                .listenerMode(kafkaConsumerConfig.listenerMode())
                .offsetResetMode(kafkaConsumerConfig.offsetResetMode())
                .kafkaGroupId(kafkaConsumerConfig.groupId())
                .kafkaAllowAutoCreateTopics(Converts.toString(kafkaConsumerConfig.allowToAutoCreateTopic()))
                .kafkaAckMode(consumerAckMode.getKafkaAckMode())
                .kafkaEnableAutoCommit(Converts.toString(consumerAckMode.getStrategy().needToEnableAutoCommitOffset()))
                .kafkaAutoCommitIntervalMs(Converts.toString(Times.wrapper(kafkaConsumerConfig.autoCommitOffsetInterval()).toMillisecond().toMillis()))
                .kafkaListenerMode(listenerMode.getKafkaListenerMode())
                .kafkaAutoOffsetReset(kafkaConsumerConfig.offsetResetMode().getCode())
                .build();
    }

    private ConsumerFactory<K, V> registerConsumerFactory(Method consumerMethod, MessageKafkaConsumerConfig consumerConfig) {
        MessageEngineKafkaProperties libraryJavaKafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, libraryJavaKafkaProperties.getServerUrls());
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfig.getKafkaGroupId());
        kafkaProperties.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, consumerConfig.getKafkaAllowAutoCreateTopics());
        kafkaProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerConfig.getKafkaEnableAutoCommit());
        kafkaProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerConfig.getKafkaAutoCommitIntervalMs());
        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerConfig.getKafkaAutoOffsetReset());
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ConsumerFactory<K, V> consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaProperties);
        Springs.registerBean(MessageFlows.getFlowId(MessageEngineType.KAFKA, consumerMethod), consumerFactory);
        return consumerFactory;
    }

    private void registerConsumerFlow(Method consumerMethod, MessageKafkaConsumerConfig consumerConfig, ConsumerFactory<K, V> consumerFactory) {
        String flowId = MessageFlows.getFlowId(MessageEngineType.KAFKA, consumerMethod);
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec<K, V> messageDrivenChannelAdapter = Kafka.messageDrivenChannelAdapter(
                            consumerFactory,
                            consumerConfig.getKafkaListenerMode(),
                            Converts.toArray(consumerConfig.getTopics(), String.class)
                    )
                    .configureListenerContainer(kafkaMessageListenerContainerSpec -> kafkaMessageListenerContainerSpec
                            .ackMode(consumerConfig.getKafkaAckMode())
                            .id(MessageFlows.getDistributedUniqueClientId(this.clientIdGenerateType, flowId))
                    );
            // .recoveryCallback(new ErrorMessageSendingRecoverer(errorChannel(), new RawRecordHeaderErrorMessageStrategy()))
            // .retryTemplate(new RetryTemplate())
            // .filterInRetry(true);

            Object consumerInstance = Springs.getBean(consumerMethod.getDeclaringClass());
            // TODO wjm 为什么是 MessageProducerSupport ？
            IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                    .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerMethod))
                    .get();
            Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, consumerMethod.getDeclaringClass().getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(consumerInstance);
            this.flowContext
                    .registration(flow)
                    .id(flowId)
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