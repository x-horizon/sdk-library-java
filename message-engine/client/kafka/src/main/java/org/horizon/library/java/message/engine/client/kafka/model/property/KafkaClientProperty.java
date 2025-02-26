package org.horizon.library.java.message.engine.client.kafka.model.property;

import org.horizon.library.java.message.engine.client.contract.model.property.MessageClientProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * property for message engine mqtt
 *
 * @author wjm
 * @since 2024-05-25 16:35
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.kafka")
public class KafkaClientProperty extends MessageClientProperty {

}