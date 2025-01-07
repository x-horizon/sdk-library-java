package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.context.ClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;

/**
 * @author wjm
 * @since 2025-01-05 22:41
 */
public interface MqttVersionStrategy {

    default void setChannelDisconnectReasonCode(MqttMessageBuilders.DisconnectBuilder disconnectBuilder, byte returnCode) {
    }

    default void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, ClientSessionContext clientSessionContext, MqttMessage mqttMessage) {
        channelHandlerContext.close();
    }

}