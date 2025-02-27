package org.horizon.sdk.library.java.message.engine.server.mqtt.strategy;

import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public interface MqttMessageStrategy<M extends MqttMessage> {

    void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, M mqttMessage);

}