// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.mqtt.v3.autoconfigure;

import cn.srd.library.java.message.engine.client.mqtt.v3.model.property.MqttV3Property;
import cn.srd.library.java.message.engine.client.mqtt.v3.strategy.MqttV3ConfigStrategy;
import cn.srd.library.java.message.engine.client.mqtt.v3.strategy.MqttV3FlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine MQTT-V3
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MqttV3Property.class)
public class MqttV3AutoConfigurer {

    @Bean
    public MqttV3ConfigStrategy mqttV3ConfigStrategy() {
        return new MqttV3ConfigStrategy();
    }

    @Bean
    public MqttV3FlowStrategy mqttV3FlowStrategy() {
        return new MqttV3FlowStrategy();
    }

}