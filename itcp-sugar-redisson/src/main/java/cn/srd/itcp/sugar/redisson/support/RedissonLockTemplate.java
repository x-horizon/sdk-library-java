package cn.srd.itcp.sugar.redisson.support;

import cn.srd.itcp.sugar.redisson.core.RedissonFairLock;
import cn.srd.itcp.sugar.redisson.core.RedissonFairLockHandler;
import cn.srd.itcp.sugar.redisson.core.RedissonNonFairLock;
import cn.srd.itcp.sugar.redisson.core.RedissonNonFairLockHandler;
import cn.srd.itcp.sugar.redisson.exception.RedissonExecuteException;
import cn.srd.itcp.sugar.tools.core.convert.Converts;
import org.redisson.api.RLock;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Redisson 分布式锁操作模板
 *
 * @author wjm
 * @date 2020/12/12 18:06
 * @see RedissonFairLock
 * @see RedissonNonFairLock
 * @see RedissonFairLockAspect
 * @see RedissonNonFairLockAspect
 * @see RedissonLockAspectSupporter
 * @see RedissonFairLockHandler
 * @see RedissonNonFairLockHandler
 */
public interface RedissonLockTemplate {

    /**
     * 获取锁时的等待时间，参考 {@link RedissonFairLock#waitTime()}
     */
    long DEFAULT_WAIT_TIME = 0L;

    /**
     * 默认持有锁的时间，参考 {@link RedissonFairLock#leaseTime()}
     */
    long DEFAULT_LEASE_TIME = -1L;

    /**
     * 默认时间单位，参考 {@link RedissonFairLock#timeUnit()}
     */
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 获取 Redisson Based Reentrant Lock
     *
     * @param lockName 锁名
     * @return
     */
    RLock getRLock(String lockName);

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier 临界区
     * @param lockName 参考 {@link RedissonFairLock#lockName()}
     * @param <T>
     * @return
     */
    default <T> T lock(@NonNull Supplier<T> supplier, @NonNull String lockName) {
        return lock(supplier, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param consumer 临界区
     * @param lockName 参考 {@link RedissonFairLock#lockName()}
     * @param <T>
     */
    default <T> void lock(@Nullable T param, @NonNull Consumer<T> consumer, @NonNull String lockName) {
        lock(param, consumer, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param <T>
     * @return
     */
    default <T> boolean lock(@Nullable T param, @NonNull Predicate<T> predicate, @NonNull String lockName) {
        return lock(param, predicate, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param function 临界区
     * @param lockName 参考 {@link RedissonFairLock#lockName()}
     * @param <T>
     * @param <R>
     * @return
     */
    default <T, R> R lock(@Nullable T param, @NonNull Function<T, R> function, @NonNull String lockName) {
        return lock(param, function, lockName, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     * @return
     */
    default <T> T lock(@NonNull Supplier<T> supplier, @NonNull String lockName, long leaseTime) {
        return lock(supplier, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     */
    default <T> void lock(@Nullable T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long leaseTime) {
        lock(param, consumer, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     * @return
     */
    default <T> boolean lock(@Nullable T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long leaseTime) {
        return lock(param, predicate, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     * @param <R>
     * @return
     */
    default <T, R> R lock(@Nullable T param, @NonNull Function<T, R> function, @NonNull String lockName, long leaseTime) {
        return lock(param, function, lockName, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     * @return
     */
    default <T> T lock(@NonNull Supplier<T> supplier, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        return lock(null, Converts.toFunction(supplier), lockName, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     */
    default <T> void lock(@Nullable T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        lock(param, Converts.toFunction(param, consumer), lockName, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     * @return
     */
    default <T> boolean lock(@Nullable T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        return lock(param, Converts.toFunction(param, predicate), lockName, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     * @param <R>
     * @return
     */
    default <T, R> R lock(@Nullable T param, @NonNull Function<T, R> function, @NonNull String lockName, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(param, function, lockName, DEFAULT_WAIT_TIME, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier 临界区
     * @param lockName 参考 {@link RedissonFairLock#lockName()}
     * @param waitTime 参考 {@link RedissonFairLock#waitTime()}
     * @param <T>
     * @return
     */
    default <T> T tryLock(@NonNull Supplier<T> supplier, @NonNull String lockName, long waitTime) {
        return tryLock(supplier, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param consumer 临界区
     * @param lockName 参考 {@link RedissonFairLock#lockName()}
     * @param waitTime 参考 {@link RedissonFairLock#waitTime()}
     * @param <T>
     */
    default <T> void tryLock(@Nullable T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long waitTime) {
        tryLock(param, consumer, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param <T>
     * @return
     */
    default <T> boolean tryLock(@Nullable T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long waitTime) {
        return tryLock(param, predicate, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param    临界区用到的参数
     * @param function 临界区
     * @param lockName 参考 {@link RedissonFairLock#lockName()}
     * @param waitTime 参考 {@link RedissonFairLock#waitTime()}
     * @param <T>
     * @param <R>
     * @return
     */
    default <T, R> R tryLock(@Nullable T param, @NonNull Function<T, R> function, @NonNull String lockName, long waitTime) {
        return tryLock(param, function, lockName, waitTime, DEFAULT_LEASE_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     * @return
     */
    default <T> T tryLock(@NonNull Supplier<T> supplier, @NonNull String lockName, long waitTime, long leaseTime) {
        return tryLock(supplier, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     */
    default <T> void tryLock(@Nullable T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long waitTime, long leaseTime) {
        tryLock(param, consumer, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     */
    default <T> boolean tryLock(@Nullable T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long waitTime, long leaseTime) {
        return tryLock(param, predicate, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param <T>
     * @param <R>
     */
    default <T, R> R tryLock(@Nullable T param, @NonNull Function<T, R> function, @NonNull String lockName, long waitTime, long leaseTime) {
        return tryLock(param, function, lockName, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param supplier  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     * @return
     */
    default <T> T tryLock(@NonNull Supplier<T> supplier, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(null, Converts.toFunction(supplier), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param consumer  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     */
    default <T> void tryLock(@Nullable T param, @NonNull Consumer<T> consumer, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        tryLock(param, Converts.toFunction(param, consumer), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param predicate 临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     * @return
     */
    default <T> boolean tryLock(@Nullable T param, @NonNull Predicate<T> predicate, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        return tryLock(param, Converts.toFunction(param, predicate), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param param     临界区用到的参数
     * @param function  临界区
     * @param lockName  参考 {@link RedissonFairLock#lockName()}
     * @param waitTime  参考 {@link RedissonFairLock#waitTime()}
     * @param leaseTime 参考 {@link RedissonFairLock#leaseTime()}
     * @param timeUnit  参考 {@link RedissonFairLock#timeUnit()}
     * @param <T>
     * @param <R>
     * @return
     */
    default <T, R> R tryLock(@Nullable T param, @NonNull Function<T, R> function, @NonNull String lockName, long waitTime, long leaseTime, @NonNull TimeUnit timeUnit) {
        RLock rLock = getRLock(lockName);
        try {
            R result = null;
            if (waitTime > DEFAULT_WAIT_TIME) {
                if (rLock.tryLock(waitTime, leaseTime, timeUnit)) {
                    result = function.apply(param);
                }
            } else {
                rLock.lock(leaseTime, timeUnit);
                result = function.apply(param);
            }
            return result;
        } catch (Exception exception) {
            throw new RedissonExecuteException(exception);
        } finally {
            unlockSafely(rLock);
        }
    }

    /**
     * 安全地释放锁
     *
     * @param rLock
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
