package cn.srd.library.java.concurrent.redis.test;

import cn.srd.library.java.concurrent.redis.EnableRedisLock;
import cn.srd.library.java.concurrent.redis.RedisFairLockHandler;
import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@EnableRedisLock
@RunWith(SpringRunner.class)
@SpringBootTest
@Setter
public class RedisLockTest {

    private static final String LOCK_NAME = "lock";

    private static final int THREAD_COUNT = 5;

    private int semaphore = THREAD_COUNT;

    @Test
    public void redisLockTest() {
        Assert.assertEquals(multiThreadOperate(() -> RedisFairLockHandler.getInstance().lock(criticalSection(), LOCK_NAME)), 0);
    }

    @Test
    public void redisTryLockTest() {
        Assert.assertEquals(multiThreadOperate(() -> RedisFairLockHandler.getInstance().tryLock(criticalSection(), LOCK_NAME, 20L)), 0);
    }

    private <T> Supplier<T> criticalSection() {
        return () -> {
            int currentSemaphore = semaphore - 1;
            System.out.println(Strings.format("{}: {} -> {}", Thread.currentThread().getName(), semaphore, currentSemaphore));
            setSemaphore(currentSemaphore);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RunningException(e);
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
                    throw new RunningException(e);
                }
            }).start();
        }
        startSignal.countDown();
        endSignal.await();
        return semaphore;
    }

}
