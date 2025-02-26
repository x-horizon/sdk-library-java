package org.horizon.library.java.message.engine.server.mqtt.autoconfigure;

import org.horizon.library.java.contract.constant.module.ModuleView;
import org.horizon.library.java.message.engine.contract.model.enums.MessageEngineType;
import org.horizon.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.library.java.message.engine.server.mqtt.model.property.MqttServerCommonProperty;
import org.horizon.library.java.message.engine.server.mqtt.model.property.MqttServerNonSslProperty;
import org.horizon.library.java.message.engine.server.mqtt.model.property.MqttServerSslProperty;
import org.horizon.library.java.tool.lang.booleans.Booleans;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wjm
 * @since 2025-01-05 17:39
 */
@Slf4j
public class MqttServerLifecycleManager {

    @Autowired private MqttServerCommonProperty commonMqttServerProperty;

    @Autowired private MqttServerNonSslProperty nonSslMqttServerProperty;

    private Channel nonSslServerBootstrap;

    private EventLoopGroup nonSslAcceptorEventLoopGroup;

    private EventLoopGroup nonSslWorkerEventLoopGroup;

    @Autowired private MqttServerSslProperty sslMqttServerProperty;

    private Channel sslServerBootstrap;

    private EventLoopGroup sslAcceptorEventLoopGroup;

    private EventLoopGroup sslWorkerEventLoopGroup;

    @Autowired private MqttServerContext mqttServerContext;

    @SneakyThrows
    @PostConstruct
    public void startServer() {
        log.debug("{}message engine {} server is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());

        nonSslAcceptorEventLoopGroup = new NioEventLoopGroup(commonMqttServerProperty.getNettyAcceptorThreadCount());
        nonSslWorkerEventLoopGroup = new NioEventLoopGroup(commonMqttServerProperty.getNettyWorkerThreadCount());
        nonSslServerBootstrap = new ServerBootstrap()
                .group(nonSslAcceptorEventLoopGroup, nonSslWorkerEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MqttServerChannelInitializer(mqttServerContext, false, commonMqttServerProperty.getNeedToEnableProxyProtocolSupport(), commonMqttServerProperty.getMaxByteInMessage()))
                .option(ChannelOption.SO_BACKLOG, commonMqttServerProperty.getMaxSocketConnectWaitQueueSize())
                .childOption(ChannelOption.SO_KEEPALIVE, commonMqttServerProperty.getNeedToKeepAlive())
                .bind(nonSslMqttServerProperty.getHost(), nonSslMqttServerProperty.getPort())
                .sync()
                .channel();

        if (Booleans.isTrue(sslMqttServerProperty.getNeedToEnable())) {
            sslAcceptorEventLoopGroup = new NioEventLoopGroup(commonMqttServerProperty.getNettyAcceptorThreadCount());
            sslWorkerEventLoopGroup = new NioEventLoopGroup(commonMqttServerProperty.getNettyWorkerThreadCount());
            sslServerBootstrap = new ServerBootstrap()
                    .group(sslAcceptorEventLoopGroup, sslWorkerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MqttServerChannelInitializer(mqttServerContext, true, commonMqttServerProperty.getNeedToEnableProxyProtocolSupport(), commonMqttServerProperty.getMaxByteInMessage()))
                    .option(ChannelOption.SO_BACKLOG, commonMqttServerProperty.getMaxSocketConnectWaitQueueSize())
                    .childOption(ChannelOption.SO_KEEPALIVE, commonMqttServerProperty.getNeedToKeepAlive())
                    .bind(sslMqttServerProperty.getHost(), sslMqttServerProperty.getPort())
                    .sync()
                    .channel();
        }

        log.debug("{}message engine {} server initialized.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        log.debug("{}message engine {} server shutdown now, stopping...", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
        try {
            nonSslServerBootstrap.close().sync();
            if (Booleans.isTrue(sslMqttServerProperty.getNeedToEnable())) {
                sslServerBootstrap.close().sync();
            }
        } finally {
            nonSslWorkerEventLoopGroup.shutdownGracefully();
            nonSslAcceptorEventLoopGroup.shutdownGracefully();
            if (Booleans.isTrue(sslMqttServerProperty.getNeedToEnable())) {
                sslWorkerEventLoopGroup.shutdownGracefully();
                sslAcceptorEventLoopGroup.shutdownGracefully();
            }
        }
        log.debug("{}message engine {} server stopped.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
    }

}