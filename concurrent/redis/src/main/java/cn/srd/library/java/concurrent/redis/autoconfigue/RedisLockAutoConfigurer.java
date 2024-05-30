package cn.srd.library.java.concurrent.redis.autoconfigue;

import cn.srd.library.java.concurrent.redis.aspect.RedisFairLockAspect;
import cn.srd.library.java.concurrent.redis.aspect.RedisNonFairLockAspect;
import cn.srd.library.java.concurrent.redis.strategy.RedisFairLockHandler;
import cn.srd.library.java.concurrent.redis.strategy.RedisNonFairLockHandler;
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
    @ConditionalOnBean(RedisLockSwitcher.class)
    public RedisFairLockHandler redisFairLockHandler() {
        return new RedisFairLockHandler();
    }

    @Bean
    @ConditionalOnBean(RedisLockSwitcher.class)
    public RedisFairLockAspect redisFairLockAspect() {
        return new RedisFairLockAspect();
    }

    @Bean
    @ConditionalOnBean(RedisLockSwitcher.class)
    public RedisNonFairLockHandler redisNonFairLockHandler() {
        return new RedisNonFairLockHandler();
    }

    @Bean
    @ConditionalOnBean(RedisLockSwitcher.class)
    public RedisNonFairLockAspect redisNonFairLockAspect() {
        return new RedisNonFairLockAspect();
    }

}