package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;

import java.util.List;

/**
 * @author wjm
 * @since 2025-01-05 22:41
 */
public interface MqttVersionStrategy {

    default void setClientDisconnectReasonCode(MqttMessageBuilders.DisconnectBuilder disconnectBuilder, byte returnCode) {
    }

    default void setClientUnsubscribeReasonCode(MqttMessageBuilders.UnsubAckBuilder unsubAckBuilder, List<Short> resultCodes) {
    }

    default void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttMessage mqttMessage) {
        channelHandlerContext.close();
    }

}