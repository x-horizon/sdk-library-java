package org.horizon.sdk.library.java.concurrent.redis;

import org.horizon.sdk.library.java.concurrent.redis.aspect.RedisFairLockAspect;
import org.horizon.sdk.library.java.concurrent.redis.strategy.RedisFairLockHandler;
import org.horizon.sdk.library.java.concurrent.redis.strategy.RedisLockTemplate;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.data.id.Snowflake;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 基于 Redis 的分布式单点公平锁，直接标记在方法上，无需侵入代码即可完成加锁释放锁操作；
 * <pre>
 *   一、关于公平与非公平锁的介绍与使用建议：
 *      - 非公平不是完全随机，线程不能任意插队，而是仅仅 “在合适的时机” 插队；
 *      - 关于“合适的时机”：
 *          假设当前线程在请求获取锁的时候，刚好前一个持有锁的线程释放了这把锁，那么当前申请锁的线程可以不顾已经等待的线程而选择立刻插队；
 *          但是如果当前线程请求的时候，前一个线程并没有在那一刻释放锁，那么当前线程还是一样会进入等待队列；
 *      - 非公平锁的设计思想：
 *          假设线程 A 持有一把锁，线程 B 请求这把锁，由于线程 A 已经持有这把锁了，线程 B 会被挂起并阻塞；
 *          当线程 A 释放锁时，本该轮到线程 B 苏醒获取锁，此时线程 C 插队请求这把锁，根据非公平的策略，会把这把锁给线程 C；
 *          因为唤醒线程 B 是需要很大开销的，很有可能在唤醒之前，线程 C 已经拿到了这把锁并且执行完任务释放了这把锁；
 *          相比于等待唤醒线程 B 的漫长过程，插队的行为会让线程 C 本身跳过陷入阻塞的过程，如果临界区执行的内容并不多，线程 C 可以很快完成任务，并且在线程 B 被完全唤醒之前，就把这个锁交出去；
 *          这是一个双赢的局面：
 *             对于线程 C 而言，不需要等待，提高了效率；
 *             对于线程 B 而言，它获得锁的时间也没有推迟，因为等它被唤醒的时候，线程 C 早已释放锁，因为线程 C 的执行速度相比于线程 B 的唤醒速度更快；
 *      - 非公平锁的优点在于整体的运行效率更快，吞吐量更大，缺点是可能产生线程饥饿，如果一直有线程插队，那么在等待队列中的线程可能长时间得不到运行；
 *      - 公平锁与非公平锁的使用建议：
 *          如果临界区代码很快执行完，建议使用非公平锁，有更大的吞吐量；
 *          如果临界区代码执行很久，建议使用公平锁，否则容易产生线程饥饿；
 *
 *   二、由于该注解是方法级别，需要确保临界区只有应该要加锁的逻辑，无关逻辑不应放在标记了该注解的方法里；
 *
 *   三、使用说明：
 *
 *          public class Person {
 *               private Integer id;
 *          }
 *          public class Book {
 *               private Integer id;
 *          }
 *          public class Test {
 *               &#064;{@link RedisFairLock}(
 *                       lockName = "name",
 *                       fieldName = "id",
 *                       fieldOrder = "2",
 *                       waitTime = 3
 *                       leaseTime = 5
 *                       timeUnit = TimeUnit.SECONDS
 *               )
 *               public void test(Person person, Book book) {
 *               }
 *          }
 *
 *      以上表示：
 *       1、在执行 test(Person person, Book book) 前加锁，执行 test(Person person, Book book) 完毕或异常时释放锁，使用 "name" 作为锁名，客户端获取锁时最多等待 3 秒，已持有锁的客户端最多可以持有 5 秒；
 *       2、lockName、fieldName、fieldOrder：
 *          显式指定 lockName = "name"，表示使用 "name" 作为锁的锁名，此时 fieldName、fieldOrder 属性无效；
 *          显式指定 fieldName = "id"，表示使用方法形参列表的第一个参数中的 id 字段值作为锁名，即 person 中 id 的值作为锁名；
 *          显式指定 fieldName = "id", fieldOrder = "2"，表示使用方法形参列表的第二个参数中的 id 字段值作为锁名，即 book 中 id 的值作为锁名；若 id 的值为空，则抛出异常；
 *          当同时指定 lockName = "name", fieldName = "id", fieldOrder = "2"，只有 lockName 属性生效；
 *          当这三个参数都不显式指定时，将自动生成一个分布式 ID 作为锁名，分布式 ID 的生成使用 {@link IdUtil#getSnowflake()} + {@link Snowflake#nextStr()}；
 *       3、waitTime：
 *          表示客户端获取锁时若该锁已被占用，则进行等待的最大时间，当超过该时间后获取锁失败，放弃获取；
 *          当 waitTime 的值为 0 时（默认），使用 {@link RedisLockTemplate#lock(Supplier, String, long, TimeUnit)} 的方式上锁；
 *          当 waitTime 的值大于 0 时，使用 {@link RedisLockTemplate#tryLock(Supplier, String, long, long, TimeUnit)} 的方式上锁；
 *          因此，使用 lock 还是 tryLock 取决于是否显式指定了 waitTime 为一个大于 0 的值；
 *       4、leaseTime：
 *          表示客户端获取锁成功后，可以持有该锁的最大时间，当超过该时间后无论临界区是否执行完成都会释放锁；
 *          当 leaseTime 的值为 -1 时（默认），已持有锁的线程可以一直持有锁，直到临界区完成或抛出了异常才会释放，其原理是 watchdog 机制，会自动对可以持有锁的时间进行续期，默认每隔 10s 自动续期 30s；
 *          使用 -1 的值有个缺陷：若某个客户端已获取到锁并且在执行临界区代码时发生了故障，导致无法执行释放锁操作，此时所有客户端都无法获取该锁；
 *          因此强烈建议不要使用默认的 -1 值，而是根据需要设置合理的值；
 *       5、timeUnit：
 *          表示 waitTime、leaseTime 的时间单位，默认值为秒；
 * </pre>
 *
 * @author wjm
 * @see RedisFairLockAspect
 * @since 2020-12-12 18:06
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisFairLock {

    /**
     * <pre>
     * 锁名：
     * 可直接设置该参数作为锁名
     * </pre>
     *
     * @return 锁名
     */
    String lockName() default "";

    /**
     * <pre>
     * 锁名：
     * 从方法形参列表的第{@link #fieldOrder()}个形参中，使用该形参中字段名为{@link #fieldName()}的值作为锁名；
     * 当显式设置了{@link #lockName()}，该参数不起作用；
     * </pre>
     *
     * @return 获取锁的属性名
     */
    String fieldName() default "";

    /**
     * <pre>
     * 锁名：
     * 从方法形参列表的第{@link #fieldOrder()}个形参中，使用该形参中字段名为{@link #fieldName()}的值作为锁名；
     * 当显式设置了{@link #lockName()}，该参数不起作用；
     * </pre>
     *
     * @return 获取锁的属性索引
     */
    int fieldOrder() default 1;

    /**
     * <pre>
     * 表示客户端获取锁时若该锁已被占用，则进行等待的最大时间，当超过该时间后获取锁失败，放弃获取；
     * 若显式设置了一个大于 0 的值时，使用 {@link RedisLockTemplate#tryLock(Supplier, String, long, long, TimeUnit)} 的方式上锁，否则使用 {@link RedisLockTemplate#lock(Supplier, String, long, TimeUnit)} 的方式上锁；
     * </pre>
     *
     * @return 获取锁等待超时时间
     */
    long waitTime() default RedisLockTemplate.DEFAULT_WAIT_TIME;

    /**
     * <pre>
     * 表示客户端获取锁成功后，可以持有该锁的最大时间，当超过该时间后无论临界区是否执行完成都会释放锁；
     * 当 leaseTime 的值为 -1 时（默认），已持有锁的线程可以一直持有锁，直到临界区完成或抛出了异常才会释放，其原理是 watchdog 机制，会自动对可以持有锁的时间进行续期，默认每隔 10s 自动续期 30s；
     * 使用 -1 的值有个缺陷：若某个客户端已获取到锁并且在执行临界区代码时发生了故障，导致无法执行释放锁操作，此时所有客户端都无法获取该锁；
     * 因此强烈建议不要使用默认的 -1 值，而是根据需要设置合理的值；
     * </pre>
     *
     * @return 持有锁等待超时时间
     */
    long leaseTime() default RedisLockTemplate.DEFAULT_LEASE_TIME;

    /**
     * 表示 {@link #waitTime()}、{@link #leaseTime()} ()} 的时间单位，默认值为秒；
     *
     * @return 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 指定要使用的锁类型
     *
     * @return 锁类型
     */
    Class<? extends RedisLockTemplate> redisLockTemplate() default RedisFairLockHandler.class;

}