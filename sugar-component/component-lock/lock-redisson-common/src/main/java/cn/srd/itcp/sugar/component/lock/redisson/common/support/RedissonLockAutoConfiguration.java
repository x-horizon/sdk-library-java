package cn.srd.itcp.sugar.component.lock.redisson.common.support;

import cn.srd.itcp.sugar.component.lock.redisson.common.core.RedissonFairLockHandler;
import cn.srd.itcp.sugar.component.lock.redisson.common.core.RedissonNonFairLockHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Cache Redisson
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@AutoConfiguration
public class RedissonLockAutoConfiguration {

    /**
     * 装配 {@link RedissonFairLockHandler}
     *
     * @return 装配对象
     */
    @Bean
    public RedissonFairLockHandler redissonFairLockHandler() {
        return new RedissonFairLockHandler();
    }

    /**
     * 装配 {@link RedissonNonFairLockHandler}
     *
     * @return 装配对象
     */
    @Bean
    public RedissonNonFairLockHandler redissonNonFairLockHandler() {
        return new RedissonNonFairLockHandler();
    }

    /**
     * 装配 {@link RedissonFairLockAspect}
     *
     * @return 装配对象
     */
    @Bean
    public RedissonFairLockAspect redissonFairLockAspect() {
        return new RedissonFairLockAspect();
    }

    /**
     * 装配 {@link RedissonNonFairLockAspect}
     *
     * @return 装配对象
     */
    @Bean
    public RedissonNonFairLockAspect redissonNonFairLockAspect() {
        return new RedissonNonFairLockAspect();
    }

    /**
     * 装配 {@link RedissonLockAspectSupporter}
     *
     * @return 装配对象
     */
    @Bean
    public RedissonLockAspectSupporter redissonLockAspectSupporter() {
        return new RedissonLockAspectSupporter();
    }

}
