// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.message.engine.contract.MessageEngineAction;
import cn.srd.library.java.message.engine.contract.MessageReceive;
import cn.srd.library.java.message.engine.contract.MessageSend;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;

import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineMqttAction implements MessageEngineAction {

    @Autowired private MqttPahoClientFactory mqttClientFactory;

    @Autowired private IntegrationFlowContext flowContext;

    @Override
    public MessageEngineMqttAction registerSendFlowIfNeed(String flowId, MessageSend messageSendAnnotation) {
        if (Nil.isNull(flowContext.getRegistrationById(flowId))) {
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher3", this.mqttClientFactory);
            messageHandler.setAsync(true);
            messageHandler.setDefaultTopic("siSampleTopic");
            messageHandler.setDefaultQos(1);
            messageHandler.setCompletionTimeout(5000);
            this.flowContext
                    .registration(flow -> flow.transform(message -> Converts.withJackson().toString(message)).handle(messageHandler))
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
        }
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    public <T> MessageEngineMqttAction registerReceiveFlowIfNeed(String flowId, MessageReceive messageReceiveAnnotation, Consumer<T> function) {
        if (Nil.isNull(flowContext.getRegistrationById(flowId))) {
            MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer3", mqttClientFactory, "siSampleTopic");
            messageDrivenChannelAdapter.setCompletionTimeout(5000);
            messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
            messageDrivenChannelAdapter.setQos(1);
            this.flowContext
                    .registration(IntegrationFlow.from(messageDrivenChannelAdapter).handle(message -> {
                        MessageModel<T> messageModel = Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class);
                        messageModel.requireSuccess();
                        function.accept(messageModel.getData());
                    }).get())
                    .id("mqttInFlow")
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

    @Override
    public <T> T receive(String flowId) {
        return null;
    }

    public MessageHandler handler() {
        return message -> {
            MessageModel<String> messageModel = Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class);
            System.out.println(message.getPayload());
        };
    }

}