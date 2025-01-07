package cn.srd.library.java.message.engine.server.mqtt.context;

import cn.srd.library.java.message.engine.server.mqtt.model.enums.MqttVersionType;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 22:10
 */
@Getter
public class MqttClientSessionContext extends MqttClientAwareSessionContext {

    private final MqttServerContext mqttServerContext;

    @Setter
    private ChannelHandlerContext channelHandlerContext;

    @Setter
    private MqttVersionType mqttVersionType;

    @Setter
    private InetSocketAddress address;

    @Setter
    private boolean provisionOnlyIs = false;

    public MqttClientSessionContext(UUID sessionId, MqttServerContext mqttServerContext) {
        super(sessionId);
        this.mqttServerContext = mqttServerContext;
    }

}