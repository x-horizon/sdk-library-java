// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.model.domain;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.domain.MessageConfigDO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageQosType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v3.model.properties.MessageEngineMqttV3Properties;
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
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.io.Serial;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-01 14:34
 */
@Slf4j
@Getter
public class MessageMqttV3ConfigDO extends MessageConfigDO {

    @Serial private static final long serialVersionUID = 4736521459419470931L;

    @JsonProperty("mqttV3BrokerInfo")
    private final BrokerDO brokerDO;

    @JsonProperty("mqttV3ProducerInfos")
    private final List<ProducerDO> producerDOs;

    @JsonProperty("mqttV3ConsumerInfos")
    private final List<ConsumerDO> consumerDOs;

    @JsonIgnore
    private final transient Map<Method, ProducerDO> producerRouters;

    @JsonIgnore
    private final transient Map<Method, ConsumerDO> consumerRouters;

    public MessageMqttV3ConfigDO() {
        log.info("{}message engine mqtt-v3 customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        this.brokerDO = new BrokerDO();
        this.producerRouters = Annotations.getAnnotatedMethods(MessageProducer.class)
                .stream()
                .filter(producerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> Collections.ofPair(producerMethod, new ProducerDO(producerMethod)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        this.consumerRouters = Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(MessageEngineType.MQTT_V3, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> Collections.ofPair(consumerMethod, new ConsumerDO(consumerMethod)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        this.producerDOs = Collections.getMapValues(producerRouters);
        this.consumerDOs = Collections.getMapValues(consumerRouters);

        log.info("""
                        {}message engine mqtt-v3 customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        Mqtt-V3 Broker Info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        Mqtt-V3 Producer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        Mqtt-V3 Consumer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                Converts.withJackson().toStringFormatted(this.brokerDO),
                Converts.withJackson().toStringFormatted(this.producerDOs),
                Converts.withJackson().toStringFormatted(this.consumerDOs)
        );
        log.info("{}message engine mqtt-v3 customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

    public ProducerDO getProducerConfigDO(Method producerMethod) {
        return producerRouters.get(producerMethod);
    }

    public ConsumerDO getConsumerConfigDO(Method consumerMethod) {
        return consumerRouters.get(consumerMethod);
    }

    @Getter
    public static class BrokerDO extends MessageConfigDO.BrokerDO {

        @Serial private static final long serialVersionUID = 7988629260563027098L;

        private final List<String> serverUrls;

        private final String username;

        private final String password;

        BrokerDO() {
            MessageEngineMqttV3Properties mqttV3Properties = Springs.getBean(MessageEngineMqttV3Properties.class);
            this.serverUrls = mqttV3Properties.getServerUrls();
            this.username = mqttV3Properties.getUsername();
            this.password = mqttV3Properties.getPassword();

            registerClientFactory();
        }

        private void registerClientFactory() {
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            Optional.ofNullable(this.username).ifPresent(mqttConnectOptions::setUserName);
            Optional.ofNullable(this.password).ifPresent(value -> mqttConnectOptions.setPassword(value.toCharArray()));
            mqttConnectOptions.setServerURIs(Converts.toArray(this.serverUrls, String.class));
            DefaultMqttPahoClientFactory mqttClientFactory = new DefaultMqttPahoClientFactory();
            mqttClientFactory.setConnectionOptions(mqttConnectOptions);
            Springs.registerBean(DefaultMqttPahoClientFactory.class.getName(), mqttClientFactory);
        }

    }

    @Getter
    private static class ClientDO extends MessageConfigDO.ClientDO {

        @Serial private static final long serialVersionUID = 1647940393744696107L;

        protected MessageQosType qosType;

        protected String completionTimeout;

        protected String disconnectCompletionTimeout;

        @JsonProperty("mqttV3_completionTimeout")
        protected long originalCompletionTimeout;

        @JsonProperty("mqttV3_disconnectCompletionTimeout")
        protected long originalDisconnectCompletionTimeout;

    }

    @Getter
    public static class ProducerDO extends ClientDO {

        @Serial private static final long serialVersionUID = 3866458534946916451L;

        private final String topic;

        private final boolean needToSendAsync;

        ProducerDO(Method producerMethod) {
            this.flowId = MessageFlows.getFlowId(MessageEngineType.MQTT_V3, producerMethod);
            this.executeMethod = producerMethod;

            MessageProducer producerAnnotation = producerMethod.getAnnotation(MessageProducer.class);
            this.topic = producerAnnotation.topic();

            MessageMqttV3Config.ClientConfig clientConfig = producerAnnotation.config().mqttV3().clientConfig();
            this.idGenerateType = clientConfig.idGenerateType();
            this.qosType = clientConfig.qosType();
            this.completionTimeout = clientConfig.completionTimeout();
            this.disconnectCompletionTimeout = clientConfig.disconnectCompletionTimeout();
            this.originalCompletionTimeout = Times.wrapper(clientConfig.completionTimeout()).toMillisecond().toMillis();
            this.originalDisconnectCompletionTimeout = Times.wrapper(clientConfig.disconnectCompletionTimeout()).toMillisecond().toMillis();
            this.clientId = MessageFlows.getDistributedUniqueClientId(this.idGenerateType, this.flowId);

            MessageMqttV3Config.ProducerConfig producerConfig = producerAnnotation.config().mqttV3().producerConfig();
            this.needToSendAsync = producerConfig.needToSendAsync();

            registerFlow();
        }

        private void registerFlow() {
            IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
            if (Nil.isNull(flowContext.getRegistrationById(this.flowId))) {
                MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(this.clientId, Springs.getBean(MqttPahoClientFactory.class));
                messageHandler.setDefaultTopic(this.topic);
                messageHandler.setDefaultQos(this.qosType.getCode());
                messageHandler.setAsync(this.needToSendAsync);
                messageHandler.setCompletionTimeout(this.originalCompletionTimeout);
                messageHandler.setDisconnectCompletionTimeout(this.originalDisconnectCompletionTimeout);
                flowContext
                        .registration(MessageFlows.getObjectToStringIntegrationFlow(messageHandler))
                        .id(this.flowId)
                        .useFlowIdAsPrefix()
                        .register();
            }
        }

    }

    @Getter
    public static class ConsumerDO extends ClientDO {

        @Serial private static final long serialVersionUID = -5886254179329714404L;

        private final List<String> topics;

        ConsumerDO(Method consumerMethod) {
            this.flowId = MessageFlows.getFlowId(MessageEngineType.MQTT_V3, consumerMethod);
            this.executeMethod = consumerMethod;

            MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
            this.topics = Converts.toList(consumerAnnotation.topics());

            MessageMqttV3Config.ClientConfig clientConfig = consumerAnnotation.config().mqttV3().clientConfig();
            this.idGenerateType = clientConfig.idGenerateType();
            this.qosType = clientConfig.qosType();
            this.completionTimeout = clientConfig.completionTimeout();
            this.disconnectCompletionTimeout = clientConfig.disconnectCompletionTimeout();
            this.originalCompletionTimeout = Times.wrapper(clientConfig.completionTimeout()).toMillisecond().toMillis();
            this.originalDisconnectCompletionTimeout = Times.wrapper(clientConfig.disconnectCompletionTimeout()).toMillisecond().toMillis();
            this.clientId = MessageFlows.getDistributedUniqueClientId(this.idGenerateType, this.flowId);

            registerFlow();
        }

        private void registerFlow() {
            IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
            if (Nil.isNull(flowContext.getRegistrationById(this.flowId))) {
                DefaultMqttPahoClientFactory mqttClientFactory = Springs.getBean(DefaultMqttPahoClientFactory.class);
                MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(this.clientId, mqttClientFactory, Converts.toArray(this.topics, String.class));
                messageDrivenChannelAdapter.setQos(this.qosType.getCode());
                messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
                messageDrivenChannelAdapter.setCompletionTimeout(this.originalCompletionTimeout);
                messageDrivenChannelAdapter.setDisconnectCompletionTimeout(this.originalDisconnectCompletionTimeout);

                Object consumerInstance = Springs.getBean(this.executeMethod.getDeclaringClass());
                Assert.of().setMessage("{}could not find the consumer instance in spring ioc, the class info is: [{}], please add it into spring ioc!", ModuleView.MESSAGE_ENGINE_SYSTEM, this.executeMethod.getDeclaringClass().getName())
                        .setThrowable(LibraryJavaInternalException.class)
                        .throwsIfNull(consumerInstance);
                IntegrationFlow flow = IntegrationFlow.from(messageDrivenChannelAdapter)
                        .handle(MessageFlows.getStringToObjectMessageHandler(consumerInstance, this.executeMethod))
                        .get();
                flowContext
                        .registration(flow)
                        .id(this.flowId)
                        .useFlowIdAsPrefix()
                        .register();
            }
        }

    }

}