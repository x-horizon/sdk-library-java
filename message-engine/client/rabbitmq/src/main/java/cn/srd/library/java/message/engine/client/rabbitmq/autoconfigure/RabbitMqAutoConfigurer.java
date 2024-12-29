// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.rabbitmq.autoconfigure;

import cn.srd.library.java.message.engine.client.rabbitmq.model.property.RabbitMqProperty;
import cn.srd.library.java.message.engine.client.rabbitmq.strategy.RabbitMqConfigStrategy;
import cn.srd.library.java.message.engine.client.rabbitmq.strategy.RabbitMqFlowStrategy;
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
@EnableConfigurationProperties(RabbitMqProperty.class)
public class RabbitMqAutoConfigurer {

    @Bean
    public RabbitMqConfigStrategy rabbitMqConfigStrategy() {
        return new RabbitMqConfigStrategy();
    }

    @Bean
    public RabbitMqFlowStrategy rabbitMqFlowStrategy() {
        return new RabbitMqFlowStrategy();
    }

}