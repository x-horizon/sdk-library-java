package org.horizon.sdk.library.java.message.engine.server.mqtt.strategy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttReasonCodes;
import org.horizon.sdk.library.java.message.engine.server.mqtt.callback.MessageCallback;
import org.horizon.sdk.library.java.message.engine.server.mqtt.constant.ClientConnectConstant;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.ClientConnectAuthHandler;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthRequestDTO;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthResponseDTO;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.enums.MqttVersionType;
import org.horizon.sdk.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessageConnectStrategy implements MqttMessageStrategy<MqttConnectMessage> {

    @Autowired private ClientConnectAuthHandler clientConnectAuthHandler;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttConnectMessage mqttConnectMessage) {
        String clientId = mqttConnectMessage.payload().clientIdentifier();
        String userName = mqttConnectMessage.payload().userName();
        byte[] passwordBytes = mqttConnectMessage.payload().passwordInBytes();
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "processing client mqtt connect message: {}", clientId);
        mqttClientSessionContext.setMqttVersionType(MqttVersionType.from(mqttConnectMessage.variableHeader().version()));
        if (Comparators.equals(ClientConnectConstant.PROVISION, userName, clientId)) {
            mqttClientSessionContext.setProvisionOnlyIs(true);
            channelHandlerContext.writeAndFlush(NettyMqtts.createMqttConnectAckMessage(mqttClientSessionContext.getMqttVersionType(), MqttConnectReturnCode.CONNECTION_ACCEPTED, mqttConnectMessage));
        } else {
            ClientConnectAuthRequestDTO authRequestDTO = ClientConnectAuthRequestDTO.builder()
                    .clientId(clientId)
                    .username(userName)
                    .password(Nil.isNull(passwordBytes) ? null : new String(passwordBytes, StandardCharsets.UTF_8))
                    .build();
            MessageCallback<ClientConnectAuthResponseDTO> callback = new MessageCallback<>() {
                @Override
                public void onSuccess(ClientConnectAuthResponseDTO responseDTO) {
                    mqttClientSessionContext.connect();
                    channelHandlerContext.writeAndFlush(NettyMqtts.createMqttConnectAckMessage(mqttClientSessionContext.getMqttVersionType(), MqttConnectReturnCode.CONNECTION_ACCEPTED, mqttConnectMessage));
                }

                @Override
                public void onFailure(Throwable throwable) {
                    NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "client [{}] auth failed: {}", clientId, throwable);
                    channelHandlerContext.writeAndFlush(NettyMqtts.createMqttConnectAckMessage(mqttClientSessionContext.getMqttVersionType(), MqttConnectReturnCode.CONNECTION_REFUSED_SERVER_UNAVAILABLE_5, mqttConnectMessage));
                    NettyMqtts.closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, MqttReasonCodes.Disconnect.SERVER_BUSY);
                }
            };
            callback.process(mqttServerContext.getMessageCallbackExecutor(), () -> clientConnectAuthHandler.process(authRequestDTO));
        }
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), mqttClientSessionContext.getSessionId(), "client [{}] has connected.", clientId);
    }

}