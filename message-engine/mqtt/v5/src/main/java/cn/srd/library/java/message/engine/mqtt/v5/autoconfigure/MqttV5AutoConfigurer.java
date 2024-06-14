// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.autoconfigure;

import cn.srd.library.java.message.engine.mqtt.v5.model.property.MqttV5Property;
import cn.srd.library.java.message.engine.mqtt.v5.strategy.MqttV5ConfigStrategy;
import cn.srd.library.java.message.engine.mqtt.v5.strategy.MqttV5FlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine MQTT-V5
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MqttV5Property.class)
public class MqttV5AutoConfigurer {

    @Bean
    public MqttV5ConfigStrategy mqttV5ConfigStrategy() {
        return new MqttV5ConfigStrategy();
    }

    @Bean
    public MqttV5FlowStrategy mqttV5FlowStrategy() {
        return new MqttV5FlowStrategy();
    }

}