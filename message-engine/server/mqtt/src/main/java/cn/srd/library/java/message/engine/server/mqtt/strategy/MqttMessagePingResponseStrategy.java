package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
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