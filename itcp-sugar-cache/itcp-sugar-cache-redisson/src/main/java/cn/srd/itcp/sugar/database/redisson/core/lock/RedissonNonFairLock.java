package cn.srd.itcp.sugar.database.redisson.core.lock;

import cn.srd.itcp.sugar.database.redisson.support.lock.RedissonNonFairLockAspect;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redisson 的分布式单点非公平锁，直接标记在方法上，无需侵入代码即可完成加锁释放锁操作；
 * <pre>
 *   详细用法参考 {@link RedissonFairLock}
 * </pre>
 *
 * @author wjm
 * @see RedissonNonFairLockAspect
 * @since 2020/12/12 18:06
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonNonFairLock {

    /**
     * 参考：{@link RedissonFairLock#lockName()}
     *
     * @return {@link RedissonFairLock#lockName()}
     */
    String lockName() default "";

    /**
     * 参考：{@link RedissonFairLock#fieldName()}
     *
     * @return {@link RedissonFairLock#fieldName()}
     */
    String fieldName() default "";

    /**
     * 参考：{@link RedissonFairLock#fieldOrder()}
     *
     * @return {@link RedissonFairLock#fieldOrder()}
     */
    int fieldOrder() default 1;

    /**
     * 参考：{@link RedissonFairLock#waitTime()}
     *
     * @return {@link RedissonFairLock#waitTime()}
     */
    long waitTime() default RedissonLockTemplate.DEFAULT_WAIT_TIME;

    /**
     * 参考：{@link RedissonFairLock#leaseTime()}
     *
     * @return {@link RedissonFairLock#leaseTime()}
     */
    long leaseTime() default RedissonLockTemplate.DEFAULT_LEASE_TIME;

    /**
     * 参考：{@link RedissonFairLock#timeUnit()}
     *
     * @return {@link RedissonFairLock#timeUnit()}
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 参考：{@link RedissonFairLock#redissonLockTemplate()}
     *
     * @return {@link RedissonFairLock#redissonLockTemplate()}
     */
    Class<? extends RedissonLockTemplate> redissonLockTemplate() default RedissonNonFairLockHandler.class;

}
