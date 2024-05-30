// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.kafka.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.message.engine.kafka.strategy.MessageEngineKafkaStrategy;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.retry.support.RetryTemplate;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine Kafka
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AllArgsConstructor
@AutoConfiguration
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableIntegration
@EnableConfigurationProperties(MessageEngineKafkaProperties.class)
@IntegrationComponentScan
public class MessageEngineKafkaAutoConfigurer<K, V> {

    private final IntegrationFlowContext flowContext;

    @Bean
    public MessageEngineKafkaStrategy<K, V> messageEngineKafkaStrategy() {
        return new MessageEngineKafkaStrategy<>();
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public MessageEngineKafkaCustomizer messageEngineMqttV3Customizer() {
        return new MessageEngineKafkaCustomizer();
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public ProducerFactory<K, V> kafkaProducerFactory(KafkaProperties kafkaProperties) {
        MessageEngineKafkaProperties libraryJavaKafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
        kafkaProperties.setBootstrapServers(libraryJavaKafkaProperties.getServerURLs());
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(null));
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public ConsumerFactory<K, V> kafkaConsumerFactory(KafkaProperties kafkaProperties) {
        MessageEngineKafkaProperties libraryJavaKafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
        kafkaProperties.setBootstrapServers(libraryJavaKafkaProperties.getServerURLs());
        ConsumerFactory<K, V> consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(null));
        registerConsumerFlow(consumerFactory);
        return consumerFactory;
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public DefaultKafkaHeaderMapper kafkaHeaderMapper() {
        return new DefaultKafkaHeaderMapper();
    }

    // public void addAnotherListenerForTopics(String... topics) {
    //     Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties(null);
    //     // change the group id, so we don't revoke the other partitions.
    //     consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerProperties.get(ConsumerConfig.GROUP_ID_CONFIG) + "x");
    //     IntegrationFlow flow = IntegrationFlow.from(Kafka.messageDrivenChannelAdapter(new DefaultKafkaConsumerFactory<String, String>(consumerProperties), topics))
    //             .channel("fromKafka")
    //             .get();
    //     this.flowContext
    //             .registration(flow)
    //             .register();
    // }

    private void registerConsumerFlow(ConsumerFactory<K, V> consumerFactory) {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(method -> Comparators.equals(MessageEngineType.KAFKA, method.getAnnotation(MessageConsumer.class).engineConfig().type()))
                .forEach(method -> {
                    String flowId = MessageFlows.getUniqueFlowId(MessageEngineType.KAFKA, method);
                    MessageConsumer messageConsumerAnnotation = method.getAnnotation(MessageConsumer.class);
                    if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
                        Object consumerInstance = Springs.getBean(method.getDeclaringClass());
                        IntegrationFlow flow = IntegrationFlow.from(Kafka.messageDrivenChannelAdapter(consumerFactory, KafkaMessageDrivenChannelAdapter.ListenerMode.record, messageConsumerAnnotation.topic())
                                        .configureListenerContainer(kafkaMessageListenerContainerSpec -> kafkaMessageListenerContainerSpec.ackMode(ContainerProperties.AckMode.RECORD).id(flowId))
                                        // .recoveryCallback(new ErrorMessageSendingRecoverer(errorChannel(), new RawRecordHeaderErrorMessageStrategy()))
                                        .retryTemplate(new RetryTemplate())
                                        .filterInRetry(true)
                                )
                                .handle(message -> Reflects.invoke(consumerInstance, method, Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class).requireSuccessAndGetData()))
                                .get();
                        Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, method.getDeclaringClass().getName())
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
                        // messageDrivenChannelAdapter.setQos(Arrays.stream(messageConsumerAnnotation.qos()).mapToInt(MessageQosType::getStatus).toArray());
                        // messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
                        // messageDrivenChannelAdapter.setCompletionTimeout(messageConsumerAnnotation.completionTimeout());
                        // messageDrivenChannelAdapter.setDisconnectCompletionTimeout(messageConsumerAnnotation.disconnectCompletionTimeout());
                    }
                });
    }

}