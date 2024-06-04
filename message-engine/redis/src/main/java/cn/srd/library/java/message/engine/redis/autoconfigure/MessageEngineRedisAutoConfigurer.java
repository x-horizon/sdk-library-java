// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.redis.autoconfigure;

import cn.srd.library.java.message.engine.redis.model.properties.MessageRedisProperties;
import cn.srd.library.java.message.engine.redis.strategy.MessageRedisConfigStrategy;
import cn.srd.library.java.message.engine.redis.strategy.MessageRedisFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine Redis
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MessageRedisProperties.class)
public class MessageEngineRedisAutoConfigurer {

    @Bean
    public MessageRedisConfigStrategy messageRedisConfigStrategy() {
        return new MessageRedisConfigStrategy();
    }

    @Bean
    public MessageRedisFlowStrategy messageRedisFlowStrategy() {
        return new MessageRedisFlowStrategy();
    }

}