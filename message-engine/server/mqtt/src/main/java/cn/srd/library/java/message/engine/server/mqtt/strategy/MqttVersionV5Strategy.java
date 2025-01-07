package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.srd.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import cn.srd.library.java.tool.lang.object.Nil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wjm
 * @since 2025-01-05 22:42
 */
@Slf4j
public class MqttVersionV5Strategy implements MqttVersionStrategy {

    @Override
    public void setClientDisconnectReasonCode(MqttMessageBuilders.DisconnectBuilder disconnectBuilder, byte returnCode) {
        disconnectBuilder.reasonCode(returnCode);
    }

    @Override
    public void setClientUnsubscribeReasonCode(MqttMessageBuilders.UnsubAckBuilder unsubAckBuilder, List<Short> resultCodes) {
        unsubAckBuilder.addReasonCodes(resultCodes.toArray(Short[]::new));
    }

    @Override
    public void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttMessage mqttMessage) {
        if (Nil.isNotNull(mqttMessage)) {
            mqttServerContext.getSingleThreadScheduler().schedule(
                    () -> {
                        if (!channelHandlerContext.writeAndFlush(mqttMessage).addListener(_ -> channelHandlerContext.close()).isDone()) {
                            NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "the max waiting time was exceeded in mqtt v5 write and flush, closing channel...");
                            channelHandlerContext.close();
                        }
                    },
                    mqttServerContext.getMqttServerCommonProperty().getInternalMaxWaitingMillisecondTimeToMqttV5WriteAndFlushWhenCloseChannelContext(),
                    TimeUnit.MILLISECONDS
            );
        }
    }

}