package cn.srd.sugar.concurrent.redisson.core;

import cn.srd.sugar.concurrent.redisson.support.RedisNonFairLockAspect;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redis 的分布式单点非公平锁，直接标记在方法上，无需侵入代码即可完成加锁释放锁操作；
 * <pre>
 *   详细用法参考 {@link RedisFairLock}
 * </pre>
 *
 * @author wjm
 * @see RedisNonFairLockAspect
 * @since 2020/12/12 18:06
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisNonFairLock {

    /**
     * 参考：{@link RedisFairLock#lockName()}
     *
     * @return {@link RedisFairLock#lockName()}
     */
    String lockName() default "";

    /**
     * 参考：{@link RedisFairLock#fieldName()}
     *
     * @return {@link RedisFairLock#fieldName()}
     */
    String fieldName() default "";

    /**
     * 参考：{@link RedisFairLock#fieldOrder()}
     *
     * @return {@link RedisFairLock#fieldOrder()}
     */
    int fieldOrder() default 1;

    /**
     * 参考：{@link RedisFairLock#waitTime()}
     *
     * @return {@link RedisFairLock#waitTime()}
     */
    long waitTime() default RedisLockTemplate.DEFAULT_WAIT_TIME;

    /**
     * 参考：{@link RedisFairLock#leaseTime()}
     *
     * @return {@link RedisFairLock#leaseTime()}
     */
    long leaseTime() default RedisLockTemplate.DEFAULT_LEASE_TIME;

    /**
     * 参考：{@link RedisFairLock#timeUnit()}
     *
     * @return {@link RedisFairLock#timeUnit()}
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 参考：{@link RedisFairLock#redisLockTemplate()}
     *
     * @return {@link RedisFairLock#redisLockTemplate()}
     */
    Class<? extends RedisLockTemplate> redisLockTemplate() default RedisNonFairLockHandler.class;

}
