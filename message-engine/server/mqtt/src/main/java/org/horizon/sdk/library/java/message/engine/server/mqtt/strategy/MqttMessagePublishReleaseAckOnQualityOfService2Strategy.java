package org.horizon.sdk.library.java.message.engine.server.mqtt.strategy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessagePublishReleaseAckOnQualityOfService2Strategy implements MqttMessageStrategy<MqttPubAckMessage> {

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttPubAckMessage mqttMessage) {

    }

}