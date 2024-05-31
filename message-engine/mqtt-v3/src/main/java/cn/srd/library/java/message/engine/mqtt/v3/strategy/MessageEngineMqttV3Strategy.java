// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.strategy;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineStrategy;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.MessageEngineMqttV3Customizer;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Springs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineMqttV3Strategy implements MessageEngineStrategy {

    @Autowired private IntegrationFlowContext flowContext;

    @Override
    public MessageEngineMqttV3Strategy registerProducerFlowIfNeed(String flowId, MessageProducer producerAnnotation) {
        if (Nil.isNull(this.flowContext.getRegistrationById(flowId))) {
            MessageMqttV3Config mqttV3Config = producerAnnotation.config().mqttV3();
            String clientId = MessageFlows.getUniqueClientId(Springs.getBean(MessageEngineMqttV3Customizer.class).getClientIdGenerateType(), flowId);
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, Springs.getBean(MqttPahoClientFactory.class));
            messageHandler.setDefaultTopic(producerAnnotation.topic());
            messageHandler.setDefaultQos(mqttV3Config.qos().getStatus());
            messageHandler.setAsync(mqttV3Config.producerConfig().needToSendAsync());
            messageHandler.setCompletionTimeout(mqttV3Config.completionTimeout());
            messageHandler.setDisconnectCompletionTimeout(mqttV3Config.disconnectCompletionTimeout());
            this.flowContext
                    .registration(MessageFlows.getObjectToStringIntegrationFlow(messageHandler))
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
        }
        return this;
    }

}