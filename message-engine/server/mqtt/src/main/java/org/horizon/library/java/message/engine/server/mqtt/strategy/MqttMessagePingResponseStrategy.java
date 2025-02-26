package org.horizon.library.java.message.engine.server.mqtt.strategy;

import org.horizon.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessagePingResponseStrategy implements MqttMessageStrategy<MqttMessage> {

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttMessage mqttMessage) {

    }

}