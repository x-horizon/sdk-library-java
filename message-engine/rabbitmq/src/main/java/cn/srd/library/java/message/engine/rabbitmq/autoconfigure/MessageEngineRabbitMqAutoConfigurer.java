// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rabbitmq.autoconfigure;

import cn.srd.library.java.message.engine.rabbitmq.model.properties.MessageRabbitMqProperties;
import cn.srd.library.java.message.engine.rabbitmq.strategy.MessageRabbitMqConfigStrategy;
import cn.srd.library.java.message.engine.rabbitmq.strategy.MessageRabbitMqFlowStrategy;
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
@EnableConfigurationProperties(MessageRabbitMqProperties.class)
public class MessageEngineRabbitMqAutoConfigurer {

    @Bean
    public MessageRabbitMqConfigStrategy messageRabbitMqConfigStrategy() {
        return new MessageRabbitMqConfigStrategy();
    }

    @Bean
    public MessageRabbitMqFlowStrategy messageRabbitMqFlowStrategy() {
        return new MessageRabbitMqFlowStrategy();
    }

}