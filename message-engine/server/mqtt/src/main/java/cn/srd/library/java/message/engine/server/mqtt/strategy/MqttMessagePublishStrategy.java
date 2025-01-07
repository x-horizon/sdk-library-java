package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.callback.MessageCallback;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.srd.library.java.message.engine.server.mqtt.handler.ClientPublishHandler;
import cn.srd.library.java.message.engine.server.mqtt.tool.NettyMqtts;
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
        MessageCallback.EMPTY.process(mqttServerContext.getMessageCallbackExecutor(), () -> clientPublishHandler.process(mqttPublishMessage));
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "mqtt publish message has been processed.}");
    }

}