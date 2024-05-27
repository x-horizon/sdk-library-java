// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.support.strategy;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.contract.support.strategy.MessageEngineStrategy;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineMqttV3Strategy implements MessageEngineStrategy {

    @Autowired private MqttPahoClientFactory mqttClientFactory;

    @Autowired private IntegrationFlowContext flowContext;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    @Override
    public MessageEngineMqttV3Strategy registerProducerFlowIfNeed(String flowId, MessageProducer messageProducerAnnotation) {
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MessageFlows.getUniqueClientId(flowId, messageProducerAnnotation.clientId()), this.mqttClientFactory);
            messageHandler.setDefaultTopic(messageProducerAnnotation.topic());
            messageHandler.setDefaultQos(messageProducerAnnotation.qos().getStatus());
            messageHandler.setAsync(messageProducerAnnotation.sendAsync());
            messageHandler.setCompletionTimeout(messageProducerAnnotation.completionTimeout());
            messageHandler.setDisconnectCompletionTimeout(messageProducerAnnotation.disconnectCompletionTimeout());
            this.flowContext
                    .registration(flow -> flow.transform(message -> Converts.withJackson().toString(message)).handle(messageHandler))
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
        }
        return this;
    }

    @Override
    public <T> boolean send(String flowId, T message) {
        return this.flowContext
                .getRegistrationById(flowId)
                .getInputChannel()
                .send(new GenericMessage<>(MessageModel.builder().status(200).message("ok").data(message).build()));
    }

}