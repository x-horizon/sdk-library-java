package org.horizon.sdk.library.java.message.engine.server.mqtt.strategy;

import org.horizon.sdk.library.java.message.engine.server.mqtt.callback.MessageCallback;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.ClientUnsubscribeHandler;
import org.horizon.sdk.library.java.message.engine.server.mqtt.matcher.MqttTopicMatcher;
import org.horizon.sdk.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttReasonCodes;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessageUnsubscribeStrategy implements MqttMessageStrategy<MqttUnsubscribeMessage> {

    @Autowired private ClientUnsubscribeHandler clientUnsubscribeHandler;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttUnsubscribeMessage mqttUnsubscribeMessage) {
        int messageId = mqttUnsubscribeMessage.variableHeader().messageId();
        if (mqttClientSessionContext.isDisconnected()) {
            NettyMqtts.logInfo(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "current client session is disconnect, close this session.");
            channelHandlerContext.writeAndFlush(NettyMqtts.createMqttUnsubscribeAckMessage(mqttClientSessionContext.getMqttVersionType(), messageId, Collections.ofSingletonList((short) MqttReasonCodes.UnsubAck.NOT_AUTHORIZED.byteValue())));
            return;
        }
        List<Short> unsubscribeResultCodes = Collections.newArrayList(mqttUnsubscribeMessage.payload().topics().size());
        mqttUnsubscribeMessage.payload().topics().forEach(unsubscribeTopic -> {
            MqttTopicMatcher mqttTopicMatcher = new MqttTopicMatcher(unsubscribeTopic);
            if (mqttClientSessionContext.getTopicMappingSupportedQualityOfServiceMap().containsKey(mqttTopicMatcher)) {
                mqttClientSessionContext.getTopicMappingSupportedQualityOfServiceMap().remove(mqttTopicMatcher);
                MessageCallback<Short> callback = new MessageCallback<>() {
                    @Override
                    public void onSuccess(Short unsubscribeResultCode) {
                        unsubscribeResultCodes.add(unsubscribeResultCode);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        NettyMqtts.logWarn(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "failed to unsubscribe topic: {}, reason: {}", unsubscribeTopic, throwable);
                        unsubscribeResultCodes.add((short) MqttReasonCodes.UnsubAck.IMPLEMENTATION_SPECIFIC_ERROR.byteValue());
                    }
                };
                callback.process(mqttServerContext.getMessageCallbackExecutor(), () -> clientUnsubscribeHandler.process(unsubscribeTopic));
            } else {
                NettyMqtts.logDebug(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "failed to unsubscribe topic - [{}] because not found.", unsubscribeTopic);
                unsubscribeResultCodes.add((short) MqttReasonCodes.UnsubAck.NO_SUBSCRIPTION_EXISTED.byteValue());
            }
        });
        channelHandlerContext.writeAndFlush(NettyMqtts.createMqttUnsubscribeAckMessage(mqttClientSessionContext.getMqttVersionType(), messageId, unsubscribeResultCodes));
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "mqtt unsubscribe message has been processed.");
    }

}