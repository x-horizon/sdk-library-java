package org.horizon.library.java.message.engine.client.rabbitmq.model.property;

import org.horizon.library.java.message.engine.client.contract.model.property.MessageClientProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * properties for message engine rabbitmq
 *
 * @author wjm
 * @since 2024-05-25 16:35
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.message-engine.rabbitmq")
public class RabbitMqClientProperty extends MessageClientProperty {

}