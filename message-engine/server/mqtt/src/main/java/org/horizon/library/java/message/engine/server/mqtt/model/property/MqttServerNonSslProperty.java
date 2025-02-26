package org.horizon.library.java.message.engine.server.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wjm
 * @since 2025-01-04 23:30
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.mqtt.server.non-ssl")
public class MqttServerNonSslProperty extends MqttServerBaseProperty {

    private Integer port = 1883;

}