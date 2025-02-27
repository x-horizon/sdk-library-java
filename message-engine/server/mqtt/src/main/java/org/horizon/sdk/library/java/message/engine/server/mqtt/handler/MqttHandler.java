package org.horizon.sdk.library.java.message.engine.server.mqtt.handler;

import org.horizon.sdk.library.java.message.engine.server.mqtt.constant.NettyMqttConstant;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.enums.MqttMessageType;
import org.horizon.sdk.library.java.message.engine.server.mqtt.tool.NettyMqtts;
import org.horizon.sdk.library.java.tool.id.uuid.support.UUIDs;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttReasonCodes;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 20:00
 */
@Slf4j
public class MqttHandler extends ChannelInboundHandlerAdapter implements GenericFutureListener<Future<? super Void>> {

    @Getter private final UUID sessionId;

    private InetSocketAddress clientAddress;

    private final MqttServerContext mqttServerContext;

    private final MqttClientSessionContext mqttClientSessionContext;

    public MqttHandler(MqttServerContext mqttServerContext) {
        this.sessionId = UUIDs.get();
        this.mqttServerContext = mqttServerContext;
        this.mqttClientSessionContext = new MqttClientSessionContext(sessionId, mqttServerContext);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelRegistered(channelHandlerContext);
        mqttServerContext.addChannelConnection();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelUnregistered(channelHandlerContext);
        mqttServerContext.decrementChannelConnection();
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object message) {
        NettyMqtts.logTrace(channelHandlerContext, mqttClientSessionContext.getAddress(), sessionId, "receive message: {}", message);
        if (Nil.isNull(clientAddress)) {
            clientAddress = getClientAddress(channelHandlerContext);
            mqttClientSessionContext.setAddress(clientAddress);
        }
        try {
            if (message instanceof MqttMessage mqttMessage) {
                if (mqttMessage.decoderResult().isFailure()) {
                    NettyMqtts.logError(channelHandlerContext, clientAddress, sessionId, "decode mqtt message failed: {}", mqttMessage.decoderResult().cause().getMessage());
                    NettyMqtts.closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, MqttReasonCodes.Disconnect.MALFORMED_PACKET);
                    return;
                }
                if (Nil.isNull(mqttMessage.fixedHeader())) {
                    NettyMqtts.logError(channelHandlerContext, clientAddress, sessionId, "receive mqtt message without fixed header: {}", mqttMessage);
                    NettyMqtts.closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, MqttReasonCodes.Disconnect.PROTOCOL_ERROR);
                    return;
                }
                mqttClientSessionContext.setChannelHandlerContext(channelHandlerContext);
                processMqttMessage(channelHandlerContext, mqttMessage);
            } else {
                NettyMqtts.logTrace(channelHandlerContext, clientAddress, sessionId, "receive non mqtt message: {}", message.getClass().getSimpleName());
                NettyMqtts.closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, (MqttMessage) null);
            }
        } finally {
            ReferenceCountUtil.safeRelease(message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        NettyMqtts.logError(channelHandlerContext, clientAddress, sessionId, "unexpected exception: ", cause);
        NettyMqtts.closeChannelHandlerContext(channelHandlerContext, mqttServerContext, mqttClientSessionContext, MqttReasonCodes.Disconnect.SERVER_SHUTTING_DOWN);
        if (cause instanceof OutOfMemoryError) {
            NettyMqtts.logError(channelHandlerContext, clientAddress, sessionId, "received out of memory error, going to shutdown server.");
            System.exit(1);
        }
    }

    @Override
    public void operationComplete(Future<? super Void> future) {
        mqttClientSessionContext.disconnect();
        NettyMqtts.logTrace(null, clientAddress, sessionId, "operate complete, channel is closed.");
    }

    void processMqttMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) {
        MqttMessageType.from(mqttMessage.fixedHeader().messageType()).getStrategy().process(channelHandlerContext, mqttServerContext, mqttClientSessionContext, mqttMessage);
    }

    private InetSocketAddress getClientAddress(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress sourceAddress = channelHandlerContext.channel().attr(NettyMqttConstant.SOURCE_ADDRESS).get();
        if (Nil.isNull(sourceAddress)) {
            sourceAddress = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
            NettyMqtts.logTrace(channelHandlerContext, null, sessionId, "using source address as client address: {}", sourceAddress);
        } else {
            NettyMqtts.logTrace(channelHandlerContext, null, sessionId, "using remote address as client address: {}", sourceAddress);
        }
        return sourceAddress;
    }

}