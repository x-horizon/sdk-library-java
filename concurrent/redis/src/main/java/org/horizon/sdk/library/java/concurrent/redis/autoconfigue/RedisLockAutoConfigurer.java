package org.horizon.sdk.library.java.concurrent.redis.autoconfigue;

import org.horizon.sdk.library.java.concurrent.redis.aspect.RedisFairLockAspect;
import org.horizon.sdk.library.java.concurrent.redis.aspect.RedisNonFairLockAspect;
import org.horizon.sdk.library.java.concurrent.redis.strategy.RedisFairLockHandler;
import org.horizon.sdk.library.java.concurrent.redis.strategy.RedisNonFairLockHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Cache Redis
 *
 * @author wjm
 * @since 2020-12-12 18:06
 */
@AutoConfiguration
public class RedisLockAutoConfigurer {

    @Bean
    @ConditionalOnBean(RedisLockRegistrar.class)
    public RedisFairLockHandler redisFairLockHandler() {
        return new RedisFairLockHandler();
    }

    @Bean
    @ConditionalOnBean(RedisLockRegistrar.class)
    public RedisFairLockAspect redisFairLockAspect() {
        return new RedisFairLockAspect();
    }

    @Bean
    @ConditionalOnBean(RedisLockRegistrar.class)
    public RedisNonFairLockHandler redisNonFairLockHandler() {
        return new RedisNonFairLockHandler();
    }

    @Bean
    @ConditionalOnBean(RedisLockRegistrar.class)
    public RedisNonFairLockAspect redisNonFairLockAspect() {
        return new RedisNonFairLockAspect();
    }

}