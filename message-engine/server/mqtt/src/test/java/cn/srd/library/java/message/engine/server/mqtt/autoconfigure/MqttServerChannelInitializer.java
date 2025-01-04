package cn.srd.library.java.message.engine.server.mqtt.autoconfigure;

import cn.srd.library.java.message.engine.server.mqtt.handler.MqttServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

/**
 * @author wjm
 * @since 2024-12-29 17:41
 */
public class MqttServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("mqttServerEncoder", MqttEncoder.INSTANCE);
        pipeline.addLast("mqttServerDecoder", new MqttDecoder());
        pipeline.addLast("mqttServerHandler", new MqttServerHandler());
    }

}