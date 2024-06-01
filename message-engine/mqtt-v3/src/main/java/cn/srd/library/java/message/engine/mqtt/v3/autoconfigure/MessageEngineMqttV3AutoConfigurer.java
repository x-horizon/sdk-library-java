// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.message.engine.mqtt.v3.model.domain.MessageMqttV3ConfigDO;
import cn.srd.library.java.message.engine.mqtt.v3.model.properties.MessageEngineMqttV3Properties;
import cn.srd.library.java.message.engine.mqtt.v3.strategy.MessageMqttV3FlowStrategy;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine MQTT
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AllArgsConstructor
@AutoConfiguration
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableIntegration
@EnableConfigurationProperties(MessageEngineMqttV3Properties.class)
@IntegrationComponentScan
public class MessageEngineMqttV3AutoConfigurer {

    @Bean
    public MessageMqttV3FlowStrategy messageEngineMqttStrategy() {
        return new MessageMqttV3FlowStrategy();
    }

    @Bean
    @ConditionalOnBean(MessageEngineMqttV3Switcher.class)
    public MessageMqttV3ConfigDO messageMqttV3ConfigDO() {
        return new MessageMqttV3ConfigDO();
    }

}