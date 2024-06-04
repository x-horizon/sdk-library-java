// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rocketmq.autoconfigure;

import cn.srd.library.java.message.engine.rocketmq.model.properties.MessageRocketMqProperties;
import cn.srd.library.java.message.engine.rocketmq.strategy.MessageRocketMqConfigStrategy;
import cn.srd.library.java.message.engine.rocketmq.strategy.MessageRocketMqFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine RocketMQ
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MessageRocketMqProperties.class)
public class MessageEngineRocketMqAutoConfigurer {

    @Bean
    public MessageRocketMqConfigStrategy messageRocketMqConfigStrategy() {
        return new MessageRocketMqConfigStrategy();
    }

    @Bean
    public MessageRocketMqFlowStrategy messageRocketMqFlowStrategy() {
        return new MessageRocketMqFlowStrategy();
    }

}