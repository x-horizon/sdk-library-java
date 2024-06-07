// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v3.model.dto.MessageMqttV3ConfigDTO;
import cn.srd.library.java.message.engine.mqtt.v3.model.properties.MessageMqttV3Properties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageMqttV3ConfigStrategy implements MessageConfigStrategy {

    @Override
    public void customize() {
        log.info("{}message engine mqtt-v3 customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        MessageMqttV3ConfigDTO.BrokerDTO brokerDTO = registerBroker();
        Map<Method, MessageMqttV3ConfigDTO.ProducerDTO> producerRouter = registerProducerRouter();
        Map<Method, MessageMqttV3ConfigDTO.ConsumerDTO> consumerRouter = registerConsumerRouter();
        MessageMqttV3ConfigDTO mqttV3ConfigDTO = MessageMqttV3ConfigDTO.builder()
                .brokerDTO(brokerDTO)
                .producerRouter(producerRouter)
                .consumerRouter(consumerRouter)
                .producerDTOs(Collections.getMapValues(producerRouter))
                .consumerDTOs(Collections.getMapValues(consumerRouter))
                .build();
        Springs.registerBean(MessageMqttV3ConfigDTO.class.getName(), mqttV3ConfigDTO);

        log.info("""
                        {}message engine mqtt-v3 customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        MqttV3 Broker Info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        MqttV3 Producer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        MqttV3 Consumer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getBrokerDTO()),
                Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getProducerDTOs()),
                Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getConsumerDTOs())
        );
        log.info("{}message engine mqtt-v3 customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

    private MessageMqttV3ConfigDTO.BrokerDTO registerBroker() {
        MessageMqttV3Properties mqttV3Properties = Springs.getBean(MessageMqttV3Properties.class);
        Assert.of().setMessage("{}could not find the mqtt-v3 server url, please provide the mqtt-v3 server url in the config yaml, see [{}].", ModuleView.MESSAGE_ENGINE_SYSTEM, MessageMqttV3Properties.class.getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(mqttV3Properties.getServerUrls());
        MessageMqttV3ConfigDTO.BrokerDTO brokerDTO = MessageMqttV3ConfigDTO.BrokerDTO.builder()
                .serverUrls(mqttV3Properties.getServerUrls())
                .username(mqttV3Properties.getUsername())
                .password(mqttV3Properties.getPassword())
                .build();
        registerClientFactory(brokerDTO);
        return brokerDTO;
    }

    private MessageMqttV3ConfigDTO.ClientDTO registerClient(MessageMqttV3Config.ClientConfig clientConfig, Method executeMethod) {
        String flowId = MessageFlows.getFlowId(MessageEngineType.MQTT_V3, executeMethod);
        ClientIdGenerateType idGenerateType = clientConfig.idGenerateType();
        return MessageMqttV3ConfigDTO.ClientDTO.builder()
                .clientId(MessageFlows.getDistributedUniqueClientId(idGenerateType, flowId))
                .flowId(flowId)
                .idGenerateType(idGenerateType)
                .executeMethod(executeMethod)
                .qosType(clientConfig.qosType())
                .completionTimeout(clientConfig.completionTimeout())
                .disconnectCompletionTimeout(clientConfig.disconnectCompletionTimeout())
                .originalCompletionTimeout(Times.wrapper(clientConfig.completionTimeout()).toMillisecond().toMillis())
                .originalDisconnectCompletionTimeout(Times.wrapper(clientConfig.disconnectCompletionTimeout()).toMillisecond().toMillis())
                .build();
    }

    private void registerClientFactory(MessageMqttV3ConfigDTO.BrokerDTO brokerDTO) {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(Converts.toArray(brokerDTO.getServerUrls(), String.class));
        Optional.ofNullable(brokerDTO.getUsername()).ifPresent(mqttConnectOptions::setUserName);
        Optional.ofNullable(brokerDTO.getPassword()).ifPresent(value -> mqttConnectOptions.setPassword(value.toCharArray()));
        DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
        mqttClientFactory.setConnectionOptions(mqttConnectOptions);
        Springs.registerBean(DefaultMqttPahoClientFactory.class.getName(), mqttClientFactory);
    }

    private Map<Method, MessageMqttV3ConfigDTO.ProducerDTO> registerProducerRouter() {
        return registerProducerRouter(Annotations.getAnnotatedMethods(MessageProducer.class));
    }

    private Map<Method, MessageMqttV3ConfigDTO.ProducerDTO> registerProducerRouter(Collection<Method> producerMethods) {
        return producerMethods.stream()
                .filter(producerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> Collections.ofPair(producerMethod, registerProducer(producerMethod)))
                .peek(producerRouter -> registerProducerFlow(producerRouter.getValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @Override
    public void registerProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO) {
        Map<Method, MessageMqttV3ConfigDTO.ProducerDTO> producerRouter = Springs.getBean(MessageMqttV3ConfigDTO.class).getProducerRouter();
        producerRouter.put(executeMethod, (MessageMqttV3ConfigDTO.ProducerDTO) producerDTO);
        registerProducerFlow((MessageMqttV3ConfigDTO.ProducerDTO) producerDTO);
    }

    @Override
    public void registerForwardProducerRouter() {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .forEach(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageConfigStrategy configStrategy = consumerAnnotation.forwardTo().config().engineType().getConfigStrategy();
                    configStrategy.registerProducerRouter(consumerMethod, Springs.getBean(MessageMqttV3ConfigDTO.class).getConsumerRouter().get(consumerMethod).getForwardProducerDTO());
                });
    }

    private MessageMqttV3ConfigDTO.ProducerDTO registerProducer(Method producerMethod) {
        return registerProducer(producerMethod, producerMethod.getAnnotation(MessageProducer.class));
    }

    @Override
    public MessageMqttV3ConfigDTO.ProducerDTO registerProducer(Method executeMethod, MessageProducer producerAnnotation) {
        MessageMqttV3Config.ProducerConfig producerConfig = producerAnnotation.config().mqttV3().producerConfig();
        return MessageMqttV3ConfigDTO.ProducerDTO.builder()
                .clientDTO(registerClient(producerAnnotation.config().mqttV3().clientConfig(), executeMethod))
                .topic(producerAnnotation.topic())
                .needToSendAsync(producerConfig.needToSendAsync())
                .build();
    }

    private void registerProducerFlow(MessageMqttV3ConfigDTO.ProducerDTO producerDTO) {
        IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
        if (Nil.isNull(flowContext.getRegistrationById(producerDTO.getClientDTO().getFlowId()))) {
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(producerDTO.getClientDTO().getClientId(), Springs.getBean(MqttPahoClientFactory.class));
            messageHandler.setDefaultTopic(producerDTO.getTopic());
            messageHandler.setDefaultQos(producerDTO.getClientDTO().getQosType().getCode());
            messageHandler.setAsync(producerDTO.isNeedToSendAsync());
            messageHandler.setCompletionTimeout(producerDTO.getClientDTO().getOriginalCompletionTimeout());
            messageHandler.setDisconnectCompletionTimeout(producerDTO.getClientDTO().getOriginalDisconnectCompletionTimeout());
            flowContext.registration(MessageFlows.getObjectToStringIntegrationFlow(messageHandler))
                    .id(producerDTO.getClientDTO().getFlowId())
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

    private Map<Method, MessageMqttV3ConfigDTO.ConsumerDTO> registerConsumerRouter() {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageConfigStrategy configStrategy = consumerAnnotation.forwardTo().config().engineType().getConfigStrategy();
                    MessageConfigDTO.ProducerDTO forwardProducerDTO = configStrategy.registerProducer(consumerMethod, consumerAnnotation.forwardTo());

                    MessageMqttV3ConfigDTO.ConsumerDTO consumerDTO = MessageMqttV3ConfigDTO.ConsumerDTO.builder()
                            .clientDTO(registerClient(consumerAnnotation.config().mqttV3().clientConfig(), consumerMethod))
                            .forwardProducerDTO(forwardProducerDTO)
                            .topics(Converts.toList(consumerAnnotation.topics()))
                            .build();
                    registerConsumerFlow(consumerDTO);
                    return Collections.ofPair(consumerMethod, consumerDTO);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private void registerConsumerFlow(MessageMqttV3ConfigDTO.ConsumerDTO consumerDTO) {
        IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
        if (Nil.isNull(flowContext.getRegistrationById(consumerDTO.getClientDTO().getFlowId()))) {
            DefaultMqttPahoClientFactory mqttClientFactory = Springs.getBean(DefaultMqttPahoClientFactory.class);
            MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(consumerDTO.getClientDTO().getClientId(), mqttClientFactory, Converts.toArray(consumerDTO.getTopics(), String.class));
            messageDrivenChannelAdapter.setQos(consumerDTO.getClientDTO().getQosType().getCode());
            messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
            messageDrivenChannelAdapter.setCompletionTimeout(consumerDTO.getClientDTO().getOriginalCompletionTimeout());
            messageDrivenChannelAdapter.setDisconnectCompletionTimeout(consumerDTO.getClientDTO().getOriginalDisconnectCompletionTimeout());

            Object consumerInstance = Springs.getBean(consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass());
            Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass().getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(consumerInstance);
            IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                    .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, consumerDTO.getClientDTO().getExecuteMethod()))
                    .get();
            flowContext.registration(flow)
                    .id(consumerDTO.getClientDTO().getFlowId())
                    .useFlowIdAsPrefix()
                    .register();
        }
    }

}