package org.horizon.sdk.library.java.message.engine.client.mqtt.v5.model.property;

import lombok.Getter;
import lombok.Setter;
import org.horizon.sdk.library.java.message.engine.client.contract.model.property.MessageClientProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * properties for message engine mqtt-v5
 *
 * @author wjm
 * @since 2024-05-25 16:35
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.mqtt-v5")
public class MqttV5ClientProperty extends MessageClientProperty {

}