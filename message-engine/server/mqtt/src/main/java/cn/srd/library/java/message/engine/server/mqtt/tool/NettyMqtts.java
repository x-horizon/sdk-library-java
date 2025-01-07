package cn.srd.library.java.message.engine.server.mqtt.tool;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.message.engine.server.mqtt.context.ClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.srd.library.java.message.engine.server.mqtt.model.enums.MqttVersionType;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.mqtt.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 22:55
 */
@Slf4j
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NettyMqtts {

    public static ChannelId getChannelId(ChannelHandlerContext channelHandlerContext) {
        return channelHandlerContext.channel().id();
    }

    public static int getMqttPacketId(MqttMessage mqttMessage) {
        return ((MqttPublishMessage) mqttMessage).variableHeader().packetId();
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

    public static int getMqttSubscribeReturnCode(MqttVersion mqttVersion, MqttReasonCodes.SubAck returnCode) {
        if (!MqttVersion.MQTT_5.equals(mqttVersion)
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
        mqttVersionType.getStrategy().setChannelDisconnectReasonCode(disconnectBuilder, returnCode);
        return disconnectBuilder.build();
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, ClientSessionContext clientSessionContext, MqttReasonCodes.Disconnect returnCode) {
        closeChannelHandlerContext(channelHandlerContext, mqttServerContext, clientSessionContext, returnCode.byteValue());
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, ClientSessionContext clientSessionContext, MqttConnectReturnCode returnCode) {
        closeChannelHandlerContext(channelHandlerContext, mqttServerContext, clientSessionContext, getMqttConnectReturnCode(clientSessionContext.getMqttVersionType(), returnCode).byteValue());
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, ClientSessionContext clientSessionContext, byte returnCode) {
        closeChannelHandlerContext(channelHandlerContext, mqttServerContext, clientSessionContext, createMqttDisconnectMessage(clientSessionContext.getMqttVersionType(), returnCode));
    }

    public static void closeChannelHandlerContext(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, ClientSessionContext clientSessionContext, MqttMessage mqttMessage) {
        if (Nil.isNotEmpty(clientSessionContext.getRpcAwaitingAckMap())) {
            logTrace(channelHandlerContext, clientSessionContext.getAddress(), clientSessionContext.getSessionId(), "cleanup rpc awaiting ack map because need to close the channel.");
            clientSessionContext.getRpcAwaitingAckMap().clear();
        }
        if (Nil.isNull(channelHandlerContext.channel())) {
            logTrace(channelHandlerContext, clientSessionContext.getAddress(), clientSessionContext.getSessionId(), "the channel handler context has no channel, closing channel...");
            channelHandlerContext.close();
        } else if (channelHandlerContext.channel().isOpen()) {
            clientSessionContext.getMqttVersionType().getStrategy().closeChannelHandlerContext(channelHandlerContext, mqttServerContext, clientSessionContext, mqttMessage);
        }
        logTrace(channelHandlerContext, clientSessionContext.getAddress(), clientSessionContext.getSessionId(), "channel is closed.");
    }

    public static void logTrace(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.trace(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    public static void logError(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId, String logMessage, Object... logParams) {
        log.error(Strings.format(STR."\{getBaseLog(channelHandlerContext, clientAddress, sessionId)} - \{logMessage}", logParams));
    }

    private static String getBaseLog(ChannelHandlerContext channelHandlerContext, InetSocketAddress clientAddress, UUID sessionId) {
        if (Nil.isNull(clientAddress)) {
            return Strings.format("{}[sessionId:{}] - [channelId:{}]", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, sessionId, getChannelId(channelHandlerContext));
        }
        return Strings.format("{}[ip:{}] - [port:{}] - [sessionId:{}] - [channelId:{}]", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, clientAddress.getAddress(), clientAddress.getPort(), sessionId, getChannelId(channelHandlerContext));
    }

}