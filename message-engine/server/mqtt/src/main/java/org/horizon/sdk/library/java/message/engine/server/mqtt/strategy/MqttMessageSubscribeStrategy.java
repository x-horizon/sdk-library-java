package org.horizon.sdk.library.java.message.engine.server.mqtt.strategy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttReasonCodes;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import org.horizon.sdk.library.java.message.engine.server.mqtt.callback.MessageFocusOnFailureCallback;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.ClientSubscribeHandler;
import org.horizon.sdk.library.java.message.engine.server.mqtt.matcher.MqttTopicMatcher;
import org.horizon.sdk.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessageSubscribeStrategy implements MqttMessageStrategy<MqttSubscribeMessage> {

    @Autowired private ClientSubscribeHandler clientSubscribeHandler;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttSubscribeMessage mqttSubscribeMessage) {
        int messageId = mqttSubscribeMessage.variableHeader().messageId();
        if (mqttClientSessionContext.isDisconnected()) {
            NettyMqtts.logInfo(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "current client session is disconnect, close this session.");
            channelHandlerContext.writeAndFlush(NettyMqtts.createMqttSubscribeAckMessage(messageId, Collections.ofSingletonList(MqttReasonCodes.SubAck.NOT_AUTHORIZED.byteValue() & 0xFF)));
            return;
        }
        List<Integer> grantedQualityOfServices = Collections.newArrayList(mqttSubscribeMessage.payload().topicSubscriptions().size());
        mqttSubscribeMessage.payload().topicSubscriptions().forEach(topicSubscription -> {
            String topicName = topicSubscription.topicFilter();
            MqttQoS requestQualityOfService = topicSubscription.qualityOfService();
            NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "processing client mqtt subscribe message, message id: {}, subscribe topic name: {}, request quality of service: {}", messageId, topicName, requestQualityOfService);

            MqttQoS maxSupportQualityOfService = NettyMqtts.getMaxSupportQualityOfService(requestQualityOfService);
            mqttClientSessionContext.getTopicMappingSupportedQualityOfServiceMap().put(new MqttTopicMatcher(topicName), maxSupportQualityOfService);
            grantedQualityOfServices.add(maxSupportQualityOfService.value());

            MessageFocusOnFailureCallback<Void> callback = throwable -> {
                NettyMqtts.logWarn(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "failed to subscribe topic: {}, request quality of service: {}, reason: {}", topicName, requestQualityOfService, throwable);
                grantedQualityOfServices.add(NettyMqtts.getMqttSubscribeReturnCode(mqttClientSessionContext.getMqttVersionType(), MqttReasonCodes.SubAck.IMPLEMENTATION_SPECIFIC_ERROR));
            };
            callback.process(mqttServerContext.getMessageCallbackExecutor(), () -> clientSubscribeHandler.process(topicSubscription));
        });
        channelHandlerContext.writeAndFlush(NettyMqtts.createMqttSubscribeAckMessage(messageId, grantedQualityOfServices));
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "mqtt subscribe message has been processed.");
    }

}