package org.horizon.sdk.library.java.concurrent.redis.strategy;

import org.horizon.sdk.library.java.concurrent.redis.RedisFairLock;
import org.horizon.sdk.library.java.concurrent.redis.RedisNonFairLock;
import org.horizon.sdk.library.java.concurrent.redis.aspect.RedisFairLockAspect;
import org.horizon.sdk.library.java.concurrent.redis.aspect.RedisLockAspect;
import org.horizon.sdk.library.java.concurrent.redis.aspect.RedisNonFairLockAspect;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.control.Try;
import lombok.NonNull;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Redis 分布式锁操作模板
 *
 * @author wjm
 * @see RedisFairLock
 * @see RedisNonFairLock
 * @see RedisFairLockAspect
 * @see RedisNonFairLockAspect
 * @see RedisLockAspect
 * @see RedisFairLockHandler
 * @see RedisNonFairLockHandler
 * @since 2020-12-12 18:06
 */
public interface RedisLockTemplate {

    /**
     * 获取锁时的等待时间，参考 {@link RedisFairLock#waitTime()}
     */
    long DEFAULT_WAIT_TIME = 0L;

    /**
     * 默认持有锁的时间，参考 {@link RedisFairLock#leaseTime()}
     */
    long DEFAULT_LEASE_TIME = -1L;

    /**
     * 默认时间单位，参考 {@link RedisFairLock#timeUnit()}
     */
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 获取 Redis Based Reentrant Lock
     *
     * @param lockName 锁名
     * @return Redis Based Reentrant Lock
     */
    RLock getLock(String lockName);

    /**
     * see {@link RLock#isLocked()}
     *
     * @param lockName the lock name
     * @return true if locked otherwise false
     */
    default boolean isLocked(String lockName) {
        return getLock(lockName).isLocked();
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier 临界区
     * @param lockName 参考 {@link RedisFairLock#lockName()}
     * @param <T>      临界区类型
     * @return 临界区响应值
     */
    default <T> T lock(@NonNull Supplier<T> supplier, @NonNull String lockName) {
        return lock(supplier, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param consumer 临界区
     * @param lockName 参考 {@link RedisFairLock#lockName()}
     * @param <T>      临界区类型
     */
    default <T> void lock(T param, @NonNull Consumer<T> consumer, @NonNull String lockName) {
        lock(param, consumer, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> boolean lock(T param, @NonNull Predicate<T> predicate, @NonNull String lockName) {
        return lock(param, predicate, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param function 临界区
     * @param lockName 参考 {@link RedisFairLock#lockName()}
     * @param <T>      临界区类型
     * @param <R>      临界区类型
     * @return 临界区响应值
     */
    default <T, R> R lock(T param, @NonNull Function<T, R> function, @NonNull String lockName) {
        return lock(param, function, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> T lock(@NonNull Supplier<T> supplier, @NonNull String lockName, long leaseTime) {
        return lock(supplier, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     */
    default <T> void lock(T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long leaseTime) {
        lock(param, consumer, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> boolean lock(T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long leaseTime) {
        return lock(param, predicate, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     * @param <R>       临界区类型
     * @return 临界区响应值
     */
    default <T, R> R lock(T param, @NonNull Function<T, R> function, @NonNull String lockName, long leaseTime) {
        return lock(param, function, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> T lock(@NonNull Supplier<T> supplier, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        return lock(null, Converts.toFunction(supplier), lockName, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     */
    default <T> void lock(T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        lock(param, Converts.toFunction(consumer), lockName, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> boolean lock(T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        return lock(param, Converts.toFunction(predicate), lockName, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     * @param <R>       临界区类型
     * @return 临界区响应值
     */
    default <T, R> R lock(T param, @NonNull Function<T, R> function, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(param, function, lockName, DEFAULT_WAIT_TIME, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier 临界区
     * @param lockName 参考 {@link RedisFairLock#lockName()}
     * @param waitTime 参考 {@link RedisFairLock#waitTime()}
     * @param <T>      临界区类型
     * @return 临界区响应值
     */
    default <T> T tryLock(@NonNull Supplier<T> supplier, @NonNull String lockName, long waitTime) {
        return tryLock(supplier, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param consumer 临界区
     * @param lockName 参考 {@link RedisFairLock#lockName()}
     * @param waitTime 参考 {@link RedisFairLock#waitTime()}
     * @param <T>      临界区类型
     */
    default <T> void tryLock(T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long waitTime) {
        tryLock(param, consumer, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> boolean tryLock(T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long waitTime) {
        return tryLock(param, predicate, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param function 临界区
     * @param lockName 参考 {@link RedisFairLock#lockName()}
     * @param waitTime 参考 {@link RedisFairLock#waitTime()}
     * @param <T>      临界区类型
     * @param <R>      临界区类型
     * @return 临界区响应值
     */
    default <T, R> R tryLock(T param, @NonNull Function<T, R> function, @NonNull String lockName, long waitTime) {
        return tryLock(param, function, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> T tryLock(@NonNull Supplier<T> supplier, @NonNull String lockName, long waitTime, long leaseTime) {
        return tryLock(supplier, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     */
    default <T> void tryLock(T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long waitTime, long leaseTime) {
        tryLock(param, consumer, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     * @return 操作是否成功
     */
    default <T> boolean tryLock(T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long waitTime, long leaseTime) {
        return tryLock(param, predicate, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param <T>       临界区类型
     * @param <R>       临界区类型
     * @return 临界区响应值
     */
    default <T, R> R tryLock(T param, @NonNull Function<T, R> function, @NonNull String lockName, long waitTime, long leaseTime) {
        return tryLock(param, function, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> T tryLock(@NonNull Supplier<T> supplier, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(null, Converts.toFunction(supplier), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     */
    default <T> void tryLock(T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        tryLock(param, Converts.toFunction(consumer), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     * @return 临界区响应值
     */
    default <T> boolean tryLock(T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(param, Converts.toFunction(predicate), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T>       临界区类型
     * @param <R>       临界区类型
     * @return 临界区响应值
     */
    default <T, R> R tryLock(T param, @NonNull Function<T, R> function, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(param, null, null, Converts.toFunction3(function), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param1    临界区用到的参数1
     * @param param2    临界区用到的参数2
     * @param param3    临界区用到的参数3
     * @param function  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T1>      临界区类型
     * @param <T2>      临界区类型
     * @param <T3>      临界区类型
     * @param <R>       临界区类型
     * @return 临界区响应值
     */
    default <T1, T2, T3, R> R tryLock(T1 param1, T2 param2, T3 param3, @NonNull Function3<T1, T2, T3, R> function, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(param1, param2, param3, null, Converts.toFunction4(function), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param1    临界区用到的参数1
     * @param param2    临界区用到的参数2
     * @param param3    临界区用到的参数3
     * @param param4    临界区用到的参数4
     * @param function  临界区
     * @param lockName  参考 {@link RedisFairLock#lockName()}
     * @param waitTime  参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedisFairLock#timeUnit()}
     * @param <T1>      临界区类型
     * @param <T2>      临界区类型
     * @param <T3>      临界区类型
     * @param <T4>      临界区类型
     * @param <R>       临界区类型
     * @return 临界区响应值
     */
    default <T1, T2, T3, T4, R> R tryLock(T1 param1, T2 param2, T3 param3, T4 param4, @NonNull Function4<T1, T2, T3, T4, R> function, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        RLock rLock = getLock(lockName);
        return Try.of(() -> {
                    R result = null;
                    if (waitTime > DEFAULT_WAIT_TIME) {
                        if (rLock.tryLock(waitTime, leaseTime, timeUnit)) {
                            result = function.apply(param1, param2, param3, param4);
                        }
                    } else {
                        rLock.lock(leaseTime, timeUnit);
                        result = function.apply(param1, param2, param3, param4);
                    }
                    return result;
                })
                .andFinally(() -> unlockSafely(rLock))
                .get();
    }

    /**
     * 安全地释放锁
     *
     * @param rLock 锁
     */
    default void unlockSafely(RLock rLock) {
        /**
         * 需要先使用 {@link RLock#isHeldByCurrentThread()} 判断是否为当前线程持有后，才能使用 {@link RLock#unlock()} 释放锁；
         * 原因：
         *  假设有 A、B 两个客户端/线程，并指定了允许持有锁的最大时间，A 执行业务超过了允许持有锁的时间，A 释放了锁；
         *  B 成功获取到了锁，此时 A 执行业务完毕，就又会释放一次锁，由于该锁已不被 A 持有，则会抛出异常；
         */
        if (rLock != null && rLock.isHeldByCurrentThread()) {
            rLock.unlock();
        }
    }

}