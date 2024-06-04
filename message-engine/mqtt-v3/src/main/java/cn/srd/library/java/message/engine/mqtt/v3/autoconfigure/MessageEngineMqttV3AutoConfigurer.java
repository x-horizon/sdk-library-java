// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.message.engine.mqtt.v3.model.properties.MessageMqttV3Properties;
import cn.srd.library.java.message.engine.mqtt.v3.strategy.MessageMqttV3ConfigStrategy;
import cn.srd.library.java.message.engine.mqtt.v3.strategy.MessageMqttV3FlowStrategy;
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
@EnableConfigurationProperties(MessageMqttV3Properties.class)
public class MessageEngineMqttV3AutoConfigurer {

    @Bean
    public MessageMqttV3ConfigStrategy messageMqttV3ConfigStrategy() {
        return new MessageMqttV3ConfigStrategy();
    }

    @Bean
    public MessageMqttV3FlowStrategy messageMqttV3FlowStrategy() {
        return new MessageMqttV3FlowStrategy();
    }

}