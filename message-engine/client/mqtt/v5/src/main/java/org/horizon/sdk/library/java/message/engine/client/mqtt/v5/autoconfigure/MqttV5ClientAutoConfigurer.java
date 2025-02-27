package org.horizon.sdk.library.java.message.engine.client.mqtt.v5.autoconfigure;

import org.horizon.sdk.library.java.message.engine.client.mqtt.v5.model.property.MqttV5ClientProperty;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v5.strategy.MqttV5ClientConfigStrategy;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v5.strategy.MqttV5ClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Client MQTT-V5
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MqttV5ClientProperty.class)
public class MqttV5ClientAutoConfigurer {

    @Bean
    public MqttV5ClientConfigStrategy mqttV5ConfigStrategy() {
        return new MqttV5ClientConfigStrategy();
    }

    @Bean
    public MqttV5ClientFlowStrategy mqttV5FlowStrategy() {
        return new MqttV5ClientFlowStrategy();
    }

}