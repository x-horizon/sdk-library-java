package cn.srd.library.java.concurrent.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable redis lock system
 *
 * @author wjm
 * @since 2023-06-13 17:26:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisLockSwitcher.class)
public @interface EnableRedisLock {

}
