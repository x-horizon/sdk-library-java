package cn.srd.library.java.message.engine.client.mqtt.v3.model.property;

import cn.srd.library.java.message.engine.client.contract.model.property.MessageClientProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * property for message engine mqtt-v3
 *
 * @author wjm
 * @since 2024-05-25 16:35
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.mqtt-v3")
public class MqttV3ClientProperty extends MessageClientProperty {

    private String username;

    private String password;

}