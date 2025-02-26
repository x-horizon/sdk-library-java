package org.horizon.library.java.concurrent.redis.test;

import org.horizon.library.java.concurrent.redis.autoconfigue.EnableRedisLock;
import org.horizon.library.java.concurrent.redis.strategy.RedisFairLockHandler;
import org.horizon.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.library.java.tool.lang.text.Strings;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <pre>
 * 1、临界区为：将 {@link #semaphore} 进行减一后，睡眠一秒，并输出减一前和减一后的数字
 * 2、多线程操作为：开启和 {@link #semaphore} 一样的线程，每个线程操作一遍临界区
 * 3、若不存在分布式锁，例如进行 multiThreadOperate(this::criticalSection) 这样的操作，最终的 {@link #semaphore} 不会变为 0
 * </pre>
 */
@Setter
@EnableRedisLock
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RedisLockTest {

    private static final String LOCK_NAME = "lock";

    private static final int THREAD_COUNT = 5;

    private int semaphore = THREAD_COUNT;

    @Test
    void redisLockTest() {
        Assertions.assertEquals(0, multiThreadOperate(() -> RedisFairLockHandler.getInstance().lock(criticalSection(), LOCK_NAME)));
    }

    @Test
    void redisTryLockTest() {
        Assertions.assertEquals(0, multiThreadOperate(() -> RedisFairLockHandler.getInstance().tryLock(criticalSection(), LOCK_NAME, 20L)));
    }

    private <T> Supplier<T> criticalSection() {
        return () -> {
            int currentSemaphore = semaphore - 1;
            System.out.println(Strings.format("{}: {} -> {}", Thread.currentThread().getName(), semaphore, currentSemaphore));
            setSemaphore(currentSemaphore);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new LibraryJavaInternalException(e);
            }
            return null;
        };
    }

    /**
     * 开启 {@link #THREAD_COUNT} 个线程操作 supplier
     *
     * @param supplier
     * @param <T>
     * @return
     */
    @SneakyThrows
    public <T> int multiThreadOperate(Supplier<T> supplier) {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(semaphore);
        for (int i = 1; i <= THREAD_COUNT; ++i) {
            new Thread(() -> {
                try {
                    startSignal.await();
                    supplier.get();
                    endSignal.countDown();
                } catch (InterruptedException e) {
                    throw new LibraryJavaInternalException(e);
                }
            }).start();
        }
        startSignal.countDown();
        endSignal.await();
        return semaphore;
    }

}