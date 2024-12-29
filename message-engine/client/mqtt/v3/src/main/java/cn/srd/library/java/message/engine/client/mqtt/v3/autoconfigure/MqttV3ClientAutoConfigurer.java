package cn.srd.library.java.message.engine.client.mqtt.v3.autoconfigure;

import cn.srd.library.java.message.engine.client.mqtt.v3.model.property.MqttV3ClientProperty;
import cn.srd.library.java.message.engine.client.mqtt.v3.strategy.MqttV3ClientConfigStrategy;
import cn.srd.library.java.message.engine.client.mqtt.v3.strategy.MqttV3ClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Client MQTT-V3
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MqttV3ClientProperty.class)
public class MqttV3ClientAutoConfigurer {

    @Bean
    public MqttV3ClientConfigStrategy mqttV3ConfigStrategy() {
        return new MqttV3ClientConfigStrategy();
    }

    @Bean
    public MqttV3ClientFlowStrategy mqttV3FlowStrategy() {
        return new MqttV3ClientFlowStrategy();
    }

}