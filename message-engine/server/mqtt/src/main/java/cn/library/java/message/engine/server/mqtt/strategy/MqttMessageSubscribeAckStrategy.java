package cn.library.java.message.engine.server.mqtt.strategy;

import cn.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttSubAckMessage;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessageSubscribeAckStrategy implements MqttMessageStrategy<MqttSubAckMessage> {

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttSubAckMessage mqttMessage) {

    }

}