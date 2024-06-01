// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.strategy;

import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;
import cn.srd.library.java.message.engine.kafka.model.domain.MessageKafkaConfigDO;
import cn.srd.library.java.tool.spring.contract.Springs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaderMapper;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageKafkaFlowStrategy<K, V> implements MessageFlowStrategy {

    @Autowired private ProducerFactory<K, V> producerFactory;

    @Autowired private KafkaHeaderMapper headerMapper;

    @Autowired private IntegrationFlowContext flowContext;

    @Override
    public String getFlowId(Method producerMethod) {
        return Springs.getBean(MessageKafkaConfigDO.class.getName(), MessageKafkaConfigDO.class).getProducerConfigDO(producerMethod).getFlowId();
    }

    // @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    // @Override
    // public MessageKafkaFlowStrategy<K, V> registerProducerFlowIfNeed(String flowId, MessageProducer producerAnnotation) {
    //     if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
    //         KafkaProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec<K, V> messageHandler = Kafka.outboundChannelAdapter(producerFactory)
    //                 .messageKey(m -> m.getHeaders().get(IntegrationMessageHeaderAccessor.SEQUENCE_NUMBER))
    //                 .headerMapper(headerMapper)
    //                 .partitionId(m -> 10)
    //                 .topicExpression(STR."headers[kafka_topic] ?: '\{producerAnnotation.topic()}'")
    //                 .configureKafkaTemplate(t -> t.id(STR."kafkaTemplate:\{producerAnnotation.topic()}"));
    //         this.flowContext
    //                 .registration(MessageFlows.getObjectToStringIntegrationFlow(messageHandler))
    //                 .id(flowId)
    //                 .useFlowIdAsPrefix()
    //                 .register();
    //         // TODO wjm 此处涉及到发送到哪个分区的问题，要深入研究，需通过不同的分区来区分消息的顺序性，消息引擎只是逻辑队列，分区才是物理的先进先出队列
    //         // KafkaProducerMessageHandler<K, V> producerMessageHandler = null;
    //         // MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MessageFlows.getUniqueClientId(flowId, producerAnnotation.clientId()), this.mqttClientFactory);
    //         // messageHandler.setDefaultTopic(producerAnnotation.topic());
    //         // messageHandler.setDefaultQos(producerAnnotation.qos().getStatus());
    //         // messageHandler.setAsync(producerAnnotation.sendAsync());
    //         // messageHandler.setCompletionTimeout(producerAnnotation.completionTimeout());
    //         // messageHandler.setDisconnectCompletionTimeout(producerAnnotation.disconnectCompletionTimeout());
    //     }
    //     return this;
    // }

}