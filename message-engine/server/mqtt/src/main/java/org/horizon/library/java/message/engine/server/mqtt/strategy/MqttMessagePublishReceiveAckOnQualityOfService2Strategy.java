package org.horizon.library.java.message.engine.server.mqtt.strategy;

import org.horizon.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.library.java.message.engine.server.mqtt.context.MqttServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessagePublishReceiveAckOnQualityOfService2Strategy implements MqttMessageStrategy<MqttPubAckMessage> {

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttPubAckMessage mqttMessage) {

    }

}