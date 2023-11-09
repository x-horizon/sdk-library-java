package cn.srd.library.java.concurrent.redis.autoconfigue;

import cn.srd.library.java.concurrent.redis.RedisFairLockHandler;
import cn.srd.library.java.concurrent.redis.RedisLockSwitcher;
import cn.srd.library.java.concurrent.redis.RedisNonFairLockHandler;
import cn.srd.library.java.concurrent.redis.support.RedisFairLockAspect;
import cn.srd.library.java.concurrent.redis.support.RedisNonFairLockAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Cache Redis
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@AutoConfiguration
@ConditionalOnBean(RedisLockSwitcher.class)
public class RedisLockAutoConfigurer {

    @Bean
    public RedisFairLockHandler redisFairLockHandler() {
        return new RedisFairLockHandler();
    }

    @Bean
    public RedisFairLockAspect redisFairLockAspect() {
        return new RedisFairLockAspect();
    }

    @Bean
    public RedisNonFairLockHandler redisNonFairLockHandler() {
        return new RedisNonFairLockHandler();
    }

    @Bean
    public RedisNonFairLockAspect redisNonFairLockAspect() {
        return new RedisNonFairLockAspect();
    }

}
