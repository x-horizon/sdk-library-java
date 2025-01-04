package cn.srd.library.java.message.engine.server.mqtt.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
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
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Server MQTT
 *
 * @author wjm
 * @since 2024-12-29 17:23
 */
@Slf4j
@AutoConfiguration
public class MqttServerAutoConfigurer {

    private Channel serverBootstrap;

    private EventLoopGroup acceptorEventLoopGroup;

    private Integer acceptorThreadCount = 1;

    private Integer workerThreadCount = 10;

    private EventLoopGroup workerEventLoopGroup;

    private String host = "127.0.0.1";

    private Integer port = 1883;

    private boolean keepAlive = true;

    @SneakyThrows
    @PostConstruct
    public void startServer() {
        log.debug("{}message engine {} server is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
        acceptorEventLoopGroup = new NioEventLoopGroup(acceptorThreadCount);
        workerEventLoopGroup = new NioEventLoopGroup(workerThreadCount);
        serverBootstrap = new ServerBootstrap()
                .group(acceptorEventLoopGroup, workerEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MqttServerChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, keepAlive)
                .bind(host, port)
                .sync()
                .channel();
        log.debug("{}message engine {} server initialized.", ModuleView.MESSAGE_ENGINE_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        log.debug("{}message engine {} server shutdown now, stopping...", ModuleView.MESSAGE_ENGINE_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
        try {
            serverBootstrap.close().sync();
        } finally {
            workerEventLoopGroup.shutdownGracefully();
            acceptorEventLoopGroup.shutdownGracefully();
        }
        log.debug("{}message engine {} server stopped.", ModuleView.MESSAGE_ENGINE_SERVER_SYSTEM, MessageEngineType.MQTT.getDescription());
    }

}