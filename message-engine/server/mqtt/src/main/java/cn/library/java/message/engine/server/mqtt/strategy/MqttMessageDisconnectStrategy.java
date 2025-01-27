package cn.library.java.message.engine.server.mqtt.strategy;

import cn.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttReasonCodes;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessageDisconnectStrategy implements MqttMessageStrategy<MqttMessage> {

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttMessage mqttMessage) {
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "mqtt disconnect message has been processed.");
        NettyMqtts.closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, MqttReasonCodes.Disconnect.NORMAL_DISCONNECT);
    }

}