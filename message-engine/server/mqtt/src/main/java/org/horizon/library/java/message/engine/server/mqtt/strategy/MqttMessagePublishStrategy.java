package org.horizon.library.java.message.engine.server.mqtt.strategy;

import org.horizon.library.java.message.engine.server.mqtt.callback.MessageCallback;
import org.horizon.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.library.java.message.engine.server.mqtt.handler.ClientPublishHandler;
import org.horizon.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessagePublishStrategy implements MqttMessageStrategy<MqttPublishMessage> {

    @Autowired private ClientPublishHandler clientPublishHandler;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttPublishMessage mqttPublishMessage) {
        if (mqttClientSessionContext.isDisconnected()) {
            NettyMqtts.logInfo(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "current client session is disconnect, close this session.");
            return;
        }
        String topicName = mqttPublishMessage.variableHeader().topicName();
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "processing client mqtt publish message, topic name: {}", topicName);

        MessageCallback<Void> callback = new MessageCallback<>() {
            @Override
            public void onSuccess(Void responseDTO) {
                NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "mqtt publish message has been processed.");
            }

            @Override
            public void onFailure(Throwable throwable) {
                NettyMqtts.logError(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "failed to handle publish message, topic name: {}, reason: {}", topicName, throwable);
            }
        };
        callback.process(mqttServerContext.getMessageCallbackExecutor(), () -> clientPublishHandler.process(mqttPublishMessage));
    }

}