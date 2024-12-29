// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.rabbitmq.autoconfigure;

import cn.srd.library.java.message.engine.client.rabbitmq.model.property.RabbitMqClientProperty;
import cn.srd.library.java.message.engine.client.rabbitmq.strategy.RabbitMqClientConfigStrategy;
import cn.srd.library.java.message.engine.client.rabbitmq.strategy.RabbitMqClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine RabbitMQ
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(RabbitMqClientProperty.class)
public class RabbitMqClientAutoConfigurer {

    @Bean
    public RabbitMqClientConfigStrategy rabbitMqConfigStrategy() {
        return new RabbitMqClientConfigStrategy();
    }

    @Bean
    public RabbitMqClientFlowStrategy rabbitMqFlowStrategy() {
        return new RabbitMqClientFlowStrategy();
    }

}