package org.horizon.sdk.library.java.message.engine.server.mqtt.model.property;

import org.horizon.sdk.library.java.tool.lang.time.Times;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wjm
 * @since 2025-01-05 23:20
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.mqtt.server.common")
public class MqttServerCommonProperty {

    private Boolean needToKeepAlive = true;

    private Boolean needToEnableVirtualThread = true;

    private Boolean needToEnableProxyProtocolSupport = false;

    private Integer nettyAcceptorThreadCount = 1;

    private Integer nettyWorkerThreadCount = 12;

    private Integer messageCallbackThreadCount = 20;

    private Integer maxSocketConnectWaitQueueSize = 128;

    private Integer maxByteInMessage = 65536;

    private String maxWaitingTimeToMqttV5WriteAndFlushWhenCloseChannelContext = "1s";

    private transient long internalMaxWaitingMillisecondTimeToMqttV5WriteAndFlushWhenCloseChannelContext;

    @PostConstruct
    public void init() {
        this.internalMaxWaitingMillisecondTimeToMqttV5WriteAndFlushWhenCloseChannelContext = Times.wrapper(getMaxWaitingTimeToMqttV5WriteAndFlushWhenCloseChannelContext()).toMillisecond().toMillis();
    }

}