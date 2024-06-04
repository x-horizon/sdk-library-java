// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.autoconfigure;

import cn.srd.library.java.message.engine.mqtt.v5.model.properties.MessageMqttV5Properties;
import cn.srd.library.java.message.engine.mqtt.v5.strategy.MessageMqttV5ConfigStrategy;
import cn.srd.library.java.message.engine.mqtt.v5.strategy.MessageMqttV5FlowStrategy;
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
@EnableConfigurationProperties(MessageMqttV5Properties.class)
public class MessageEngineMqttV5AutoConfigurer {

    @Bean
    public MessageMqttV5ConfigStrategy messageMqttV5ConfigStrategy() {
        return new MessageMqttV5ConfigStrategy();
    }

    @Bean
    public MessageMqttV5FlowStrategy messageMqttV5FlowStrategy() {
        return new MessageMqttV5FlowStrategy();
    }

}