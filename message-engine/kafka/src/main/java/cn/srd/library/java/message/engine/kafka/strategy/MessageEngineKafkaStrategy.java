// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.strategy;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineStrategy;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.integration.kafka.dsl.KafkaProducerMessageHandlerSpec;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaderMapper;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineKafkaStrategy<K, V> implements MessageEngineStrategy {

    @Autowired private ProducerFactory<K, V> producerFactory;

    @Autowired private KafkaHeaderMapper headerMapper;

    @Autowired private IntegrationFlowContext flowContext;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    @Override
    public MessageEngineKafkaStrategy<K, V> registerProducerFlowIfNeed(String flowId, MessageProducer messageProducerAnnotation) {
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            KafkaProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec<K, V> messageHandler = Kafka.outboundChannelAdapter(producerFactory)
                    .messageKey(m -> m.getHeaders().get(IntegrationMessageHeaderAccessor.SEQUENCE_NUMBER))
                    .headerMapper(headerMapper)
                    .partitionId(m -> 10)
                    .topicExpression(STR."headers[kafka_topic] ?: '\{messageProducerAnnotation.topic()}'")
                    .configureKafkaTemplate(t -> t.id(STR."kafkaTemplate:\{messageProducerAnnotation.topic()}"));
            this.flowContext
                    .registration(flow -> flow.transform(messageModel -> Converts.withJackson().toString(messageModel)).handle(messageHandler))
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
            // TODO wjm 此处涉及到发送到哪个分区的问题，要深入研究，需通过不同的分区来区分消息的顺序性，消息引擎只是逻辑队列，分区才是物理的先进先出队列
            // KafkaProducerMessageHandler<K, V> producerMessageHandler = null;
            // MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MessageFlows.getUniqueClientId(flowId, messageProducerAnnotation.clientId()), this.mqttClientFactory);
            // messageHandler.setDefaultTopic(messageProducerAnnotation.topic());
            // messageHandler.setDefaultQos(messageProducerAnnotation.qos().getStatus());
            // messageHandler.setAsync(messageProducerAnnotation.sendAsync());
            // messageHandler.setCompletionTimeout(messageProducerAnnotation.completionTimeout());
            // messageHandler.setDisconnectCompletionTimeout(messageProducerAnnotation.disconnectCompletionTimeout());
        }
        return this;
    }

}