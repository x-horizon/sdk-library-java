package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.context.ClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public interface MqttMessageStrategy<M extends MqttMessage> {

    default void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, ClientSessionContext clientSessionContext, M mqttMessage) {

    }

}