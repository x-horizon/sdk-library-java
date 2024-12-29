// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.redis.stream.autoconfigure;

import cn.srd.library.java.message.engine.client.redis.stream.model.property.RedisStreamProperty;
import cn.srd.library.java.message.engine.client.redis.stream.strategy.RedisStreamConfigStrategy;
import cn.srd.library.java.message.engine.client.redis.stream.strategy.RedisStreamFlowStrategy;
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
@EnableConfigurationProperties(RedisStreamProperty.class)
public class RedisStreamAutoConfigurer {

    @Bean
    public RedisStreamConfigStrategy redisStreamConfigStrategy() {
        return new RedisStreamConfigStrategy();
    }

    @Bean
    public RedisStreamFlowStrategy redisStreamFlowStrategy() {
        return new RedisStreamFlowStrategy();
    }

}