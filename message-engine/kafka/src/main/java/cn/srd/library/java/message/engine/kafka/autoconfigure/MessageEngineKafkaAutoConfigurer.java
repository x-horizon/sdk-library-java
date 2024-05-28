// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.contract.support.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.strategy.MessageQosType;
import cn.srd.library.java.message.engine.kafka.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.message.engine.kafka.support.strategy.MessageEngineKafkaStrategy;
import cn.srd.library.java.message.engine.mqtt.v3.properties.MessageEngineMqttV3Properties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
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
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

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
public class MessageEngineKafkaAutoConfigurer {

    private final IntegrationFlowContext flowContext;

    @Bean
    public ProducerFactory<?, ?> kafkaProducerFactory(KafkaProperties properties) {
        Map<String, Object> producerProperties = properties.buildProducerProperties(null);
        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        return new DefaultKafkaProducerFactory<>(producerProperties);
    }

    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties) {
        Map<String, Object> consumerProperties = properties.buildConsumerProperties(null);
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }

    @Bean
    public MessageEngineKafkaStrategy messageEngineKafkaStrategy() {
        return new MessageEngineKafkaStrategy();
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public MqttPahoClientFactory mqttClientFactory() {
        MessageEngineMqttV3Properties mqttProperties = Springs.getBean(MessageEngineMqttV3Properties.class);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        Optional.ofNullable(mqttProperties.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(mqttProperties.getPassword()).ifPresent(password -> mqttConnectOptions.setPassword(password.toCharArray()));
        mqttConnectOptions.setServerURIs(Converts.toArray(mqttProperties.getServerURLs(), String.class));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        registerConsumerFlow(mqttClientFactory);
        return mqttClientFactory;
    }

    public void addAnotherListenerForTopics(String... topics) {
        Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties(null);
        // change the group id, so we don't revoke the other partitions.
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerProperties.get(ConsumerConfig.GROUP_ID_CONFIG) + "x");
        IntegrationFlow flow = IntegrationFlow.from(Kafka.messageDrivenChannelAdapter(new DefaultKafkaConsumerFactory<String, String>(consumerProperties), topics))
                .channel("fromKafka")
                .get();
        this.flowContext
                .registration(flow)
                .register();
    }

    private void registerConsumerFlow(MqttPahoClientFactory mqttClientFactory) {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(method -> Comparators.equals(MessageEngineType.KAFKA, method.getAnnotation(MessageConsumer.class).engine()))
                .forEach(method -> {
                    String flowId = MessageFlows.getUniqueFlowId(method);
                    MessageConsumer messageConsumerAnnotation = method.getAnnotation(MessageConsumer.class);
                    if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
                        KafkaMessageDrivenChannelAdapter messageDrivenChannelAdapter = new KafkaMessageDrivenChannelAdapter(MessageFlows.getUniqueClientId(flowId, messageConsumerAnnotation.clientId()), mqttClientFactory, messageConsumerAnnotation.topic());
                        messageDrivenChannelAdapter.setQos(Arrays.stream(messageConsumerAnnotation.qos()).mapToInt(MessageQosType::getStatus).toArray());
                        messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
                        messageDrivenChannelAdapter.setCompletionTimeout(messageConsumerAnnotation.completionTimeout());
                        messageDrivenChannelAdapter.setDisconnectCompletionTimeout(messageConsumerAnnotation.disconnectCompletionTimeout());
                        Object consumerInstance = Springs.getBean(method.getDeclaringClass());
                        Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, method.getDeclaringClass().getName())
                                .setThrowable(LibraryJavaInternalException.class)
                                .throwsIfNull(consumerInstance);
                        IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                                .handle(message -> Reflects.invoke(consumerInstance, method, Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class).requireSuccessAndGetData()))
                                .get();
                        this.flowContext
                                .registration(flow)
                                .id(flowId)
                                .useFlowIdAsPrefix()
                                .register();
                    }
                });
    }

}