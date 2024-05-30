// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.strategy;

import cn.srd.library.java.message.engine.contract.MessageEngineMqttV3Config;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.MessageEngineMqttV3Customizer;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineMqttV3Strategy implements MessageEngineStrategy {

    @Autowired private MqttPahoClientFactory mqttClientFactory;

    @Autowired private IntegrationFlowContext flowContext;

    @Autowired private MessageEngineMqttV3Customizer mqttV3Customizer;

    @Override
    public MessageEngineMqttV3Strategy registerProducerFlowIfNeed(String flowId, MessageProducer messageProducerAnnotation) {
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            MessageEngineMqttV3Config messageEngineMqttV3Config = messageProducerAnnotation.engineConfig().mqttV3();
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MessageFlows.getUniqueClientId(mqttV3Customizer.getUniqueClientIdGenerateType(), flowId, messageEngineMqttV3Config.clientId()), this.mqttClientFactory);
            messageHandler.setDefaultTopic(messageProducerAnnotation.topic());
            messageHandler.setDefaultQos(messageEngineMqttV3Config.qos().getStatus());
            messageHandler.setAsync(messageEngineMqttV3Config.producerConfig().needToSendAsync());
            messageHandler.setCompletionTimeout(messageEngineMqttV3Config.completionTimeout());
            messageHandler.setDisconnectCompletionTimeout(messageEngineMqttV3Config.disconnectCompletionTimeout());
            this.flowContext
                    .registration(flow -> flow.transform(messageModel -> Converts.withJackson().toString(messageModel)).handle(messageHandler))
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
        }
        return this;
    }

}