// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageKafkaConfig;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.kafka.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
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
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.support.RetryTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    @PostConstruct
    public void initialize() {
        log.info("{}message engine kafka customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        EnableMessageEngineKafka kafkaCustomizer = Annotations.getAnnotation(EnableMessageEngineKafka.class);
        this.clientIdGenerateType = kafkaCustomizer.clientIdGenerateType();

        List<Method> consumerMethods = getConsumerMethods();
        registerConsumer(consumerMethods);

        log.info(""" 
                        {}message engine kafka customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        Client Id Generate Config:
                           generateType                            = [{}]
                        Controller Broker Server Info:
                           serverUrls                              = [{}]
                        Consumer Info:
                        [
                           {}
                        ]
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                kafkaCustomizer.clientIdGenerateType().name(),
                Strings.join(Springs.getBean(MessageEngineKafkaProperties.class).getServerUrls(), SymbolConstant.COMMA + SymbolConstant.SPACE),
                consumerMethods.stream().map(consumerMethod -> {
                            MessageKafkaConfig kafkaConfig = consumerMethod.getAnnotation(MessageConsumer.class).config().kafka();
                            return STR."groupId = [\{kafkaConfig.consumerConfig().groupId()}], " +
                                    STR."flowId = [\{MessageFlows.getUniqueFlowId(MessageEngineType.KAFKA, consumerMethod)}]";
                        })
                        .collect(Collectors.joining("\n   "))
        );

        log.info("{}message engine kafka customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

    private List<Method> getConsumerMethods() {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.KAFKA, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .toList();
    }

    private void registerConsumer(List<Method> consumerMethods) {
        consumerMethods.forEach(consumerMethod -> {
            ConsumerFactory<K, V> consumerFactory = registerConsumerFactory(consumerMethod);
            registerConsumerFlow(consumerMethod, consumerFactory);
        });
    }

    private ConsumerFactory<K, V> registerConsumerFactory(Method consumerMethod) {
        MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
        MessageEngineKafkaProperties libraryJavaKafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, libraryJavaKafkaProperties.getServerUrls());
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerAnnotation.config().kafka().consumerConfig().groupId());
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ConsumerFactory<K, V> consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaProperties);
        Springs.registerBean(MessageFlows.getUniqueFlowId(MessageEngineType.KAFKA, consumerMethod), consumerFactory);
        return consumerFactory;
    }

    private void registerConsumerFlow(Method consumerMethod, ConsumerFactory<K, V> consumerFactory) {
        MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
        String flowId = MessageFlows.getUniqueFlowId(MessageEngineType.KAFKA, consumerMethod);
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            Object consumerInstance = Springs.getBean(consumerMethod.getDeclaringClass());
            IntegrationFlow flow = IntegrationFlow.from(Kafka.messageDrivenChannelAdapter(consumerFactory, KafkaMessageDrivenChannelAdapter.ListenerMode.record, consumerAnnotation.topic())
                            .configureListenerContainer(kafkaMessageListenerContainerSpec -> kafkaMessageListenerContainerSpec.ackMode(ContainerProperties.AckMode.RECORD).id(flowId))
                            // .recoveryCallback(new ErrorMessageSendingRecoverer(errorChannel(), new RawRecordHeaderErrorMessageStrategy()))
                            .retryTemplate(new RetryTemplate())
                            .filterInRetry(true)
                    )
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