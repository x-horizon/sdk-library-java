// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rocketmq.autoconfigure;

import cn.srd.library.java.message.engine.rocketmq.model.property.RocketMqProperty;
import cn.srd.library.java.message.engine.rocketmq.strategy.RocketMqConfigStrategy;
import cn.srd.library.java.message.engine.rocketmq.strategy.RocketMqFlowStrategy;
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
@EnableConfigurationProperties(RocketMqProperty.class)
public class RocketMqAutoConfigurer {

    @Bean
    public RocketMqConfigStrategy rocketMqConfigStrategy() {
        return new RocketMqConfigStrategy();
    }

    @Bean
    public RocketMqFlowStrategy rocketMqFlowStrategy() {
        return new RocketMqFlowStrategy();
    }

}