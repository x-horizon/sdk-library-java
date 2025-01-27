package cn.library.java.message.engine.server.mqtt.tool;

import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.message.engine.server.mqtt.constant.MqttServerConstant;
import cn.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.library.java.message.engine.server.mqtt.model.enums.MqttVersionType;
import cn.library.java.tool.lang.object.Nil;
import cn.library.java.tool.lang.text.Strings;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 22:55
 */
@Slf4j
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NettyMqtts {

    public static MqttQoS getMaxSupportQualityOfService(MqttQoS requestQualityOfService) {
        return MqttQoS.valueOf(Math.min(requestQualityOfService.value(), MqttServerConstant.MAX_SUPPORTED_QUALITY_OF_SERVICE.value()));
    }

    public static MqttConnectReturnCode getMqttConnectReturnCode(MqttVersionType mqttVersionType, MqttConnectReturnCode returnCode) {
        if (!MqttVersion.MQTT_5.equals(mqttVersionType.getNettyMqttVersion()) && !MqttConnectReturnCode.CONNECTION_ACCEPTED.equals(returnCode)) {
            switch (returnCode) {
                case CONNECTION_REFUSED_BAD_USERNAME_OR_PASSWORD,
                     CONNECTION_REFUSED_NOT_AUTHORIZED_5:
                    return MqttConnectReturnCode.CONNECTION_REFUSED_NOT_AUTHORIZED;
                case CONNECTION_REFUSED_CLIENT_IDENTIFIER_NOT_VALID:
                    return MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED;
                case CONNECTION_REFUSED_SERVER_UNAVAILABLE_5,
                     CONNECTION_REFUSED_CONNECTION_RATE_EXCEEDED:
                    return MqttConnectReturnCode.CONNECTION_REFUSED_SERVER_UNAVAILABLE;
                default:
                    log.warn("{}unknown return code: {}", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, returnCode.name());
            }
        }
        return MqttConnectReturnCode.valueOf(returnCode.byteValue());
    }

    public static int getMqttSubscribeReturnCode(MqttVersionType mqttVersionType, MqttReasonCodes.SubAck returnCode) {
        if (!MqttVersion.MQTT_5.equals(mqttVersionType.getNettyMqttVersion())
                && !(MqttReasonCodes.SubAck.GRANTED_QOS_0.equals(returnCode)
                || MqttReasonCodes.SubAck.GRANTED_QOS_1.equals(returnCode)
                || MqttReasonCodes.SubAck.GRANTED_QOS_2.equals(returnCode))
        ) {
            return switch (returnCode) {
                case UNSPECIFIED_ERROR, TOPIC_FILTER_INVALID, IMPLEMENTATION_SPECIFIC_ERROR, NOT_AUTHORIZED, PACKET_IDENTIFIER_IN_USE, QUOTA_EXCEEDED, SHARED_SUBSCRIPTIONS_NOT_SUPPORTED, SUBSCRIPTION_IDENTIFIERS_NOT_SUPPORTED, WILDCARD_SUBSCRIPTIONS_NOT_SUPPORTED -> MqttQoS.FAILURE.value();
                default -> returnCode.byteValue();
            };
        }
        return returnCode.byteValue();
    }

    public static MqttConnAckMessage createMqttConnectAckMessage(MqttVersionType mqttVersionType, MqttConnectReturnCode mqttConnectReturnCode, MqttConnectMessage mqttConnectMessage) {
        MqttMessageBuilders.ConnAckBuilder connAckBuilder = MqttMessageBuilders.connAck();
        connAckBuilder.sessionPresent(!mqttConnectMessage.variableHeader().isCleanSession());
        connAckBuilder.returnCode(getMqttConnectReturnCode(mqttVersionType, mqttConnectReturnCode));
        return connAckBuilder.build();
    }

    public static MqttMessage createMqttDisconnectMessage(MqttVersionType mqttVersionType, byte returnCode) {
        MqttMessageBuilders.DisconnectBuilder disconnectBuilder = MqttMessageBuilders.disconnect();
        mqttVersionType.getStrategy().setClientDisconnectReasonCode(disconnectBuilder, returnCode);
        return disconnectBuilder.build();
    }

    public static MqttSubAckMessage createMqttSubscribeAckMessage(int messageId, List<Integer> reasonCodes) {
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        MqttSubAckPayload mqttSubAckPayload = new MqttSubAckPayload(reasonCodes);
        return new MqttSubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader, mqttSubAckPayload);
    }

    public static MqttUnsubAckMessage createMqttUnsubscribeAckMessage(MqttVersionType mqttVersionType, int messageId, List<Short> resultCodes) {
        MqttMessageBuilders.UnsubAckBuilder unsubAckBuilder = MqttMessageBuilders.unsubAck();
        unsubAckBuilder.packetId(messageId);
        mqttVersionType.getStrategy().setClientUnsubscribeReasonCode(unsubAckBuilder, resultCodes);
        return unsubAckBuilder.build();
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttReasonCodes.Disconnect returnCode) {
        closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, returnCode.byteValue());
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttConnectReturnCode returnCode) {
        closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, getMqttConnectReturnCode(mqttClientSessionContext.getMqttVersionType(), returnCode).byteValue());
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, byte returnCode) {
        closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, createMqttDisconnectMessage(mqttClientSessionContext.getMqttVersionType(), returnCode));
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttMessage mqttMessage) {
        if (Nil.isNotEmpty(mqttClientSessionContext.getRpcAwaitingAckMap())) {
            logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "cleanup rpc awaiting ack map because need to close the channel.");
            mqttClientSessionContext.getRpcAwaitingAckMap().clear();
        }
        if (Nil.isNull(channelHandlerContext.channel())) {
            logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "the channel handler context has no channel, closing channel...");
            channelHandlerContext.close();
        } else if (channelHandlerContext.channel().isOpen()) {
            mqttClientSessionContext.getMqttVersionType().getStrategy().closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, mqttMessage);
        }
        logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "channel is closed.");
    }

    public static void logTrace(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.trace(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    public static void logDebug(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.debug(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    public static void logInfo(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.info(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    public static void logWarn(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.warn(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    public static void logError(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.error(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    private static String getBaseLog(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId) {
        String baseLog = ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM;
        if (Nil.isNotNull(clientAddress)) {
            baseLog = baseLog + Strings.format("[ip:{}] - [port:{}]", clientAddress.getAddress(), clientAddress.getPort());
        }
        if (Nil.isNotNull(sessionId)) {
            baseLog = baseLog + Strings.format(" - [sessionId:{}]", sessionId);
        }
        if (Nil.isNotNull(channelHandlerContext)) {
            baseLog = baseLog + Strings.format(" - [channelId:{}]", channelHandlerContext.channel().id());
        }
        return baseLog;
    }

}