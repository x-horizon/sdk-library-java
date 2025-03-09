package org.horizon.sdk.library.java.message.engine.server.mqtt.autoconfigure;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import lombok.AllArgsConstructor;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.IpLimitFlowChannelInboundHandler;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.MqttHandler;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.ProxyIpLimitFlowChannelInboundHandler;

/**
 * @author wjm
 * @since 2024-12-29 17:41
 */
@AllArgsConstructor
public class MqttServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final MqttServerContext mqttServerContext;

    private final boolean needToEnableSsl;

    private final boolean needToEnableProxyProtocolSupport;

    private final int maxByteInMessage;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        if (this.needToEnableProxyProtocolSupport) {
            pipeline.addLast("proxyIpMessageDecoder", new HAProxyMessageDecoder());
            pipeline.addLast("proxyIpLimitFlowChannelInboundHandler", new ProxyIpLimitFlowChannelInboundHandler());
        } else {
            pipeline.addLast("ipLimitFlowChannelInboundHandler", new IpLimitFlowChannelInboundHandler());
        }

        if (this.needToEnableSsl) {

        }

        pipeline.addLast("mqttDecoder", new MqttDecoder(this.maxByteInMessage));
        pipeline.addLast("mqttEncoder", MqttEncoder.INSTANCE);

        MqttHandler mqttHandler = new MqttHandler(mqttServerContext);
        pipeline.addLast("mqttHandler", mqttHandler);
        socketChannel.closeFuture().addListener(mqttHandler);
    }

}