package org.horizon.library.java.message.engine.server.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Library Java Mqtt Server
 *
 * @author wjm
 * @since 2025-01-04 23:24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.mqtt.server.ssl")
public class MqttServerSslProperty extends MqttServerBaseProperty {

    private Integer port = 8883;

    private Boolean needToEnable = false;

}