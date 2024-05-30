package cn.srd.library.java.concurrent.redis.autoconfigue;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable redis lock system
 *
 * @author wjm
 * @see RedisLockSwitcher
 * @see RedisLockAutoConfigurer
 * @since 2023-06-13 17:26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisLockSwitcher.class)
public @interface EnableRedisLock {

}