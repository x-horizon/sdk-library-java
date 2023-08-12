package cn.srd.library.concurrent.redisson.core;

import cn.srd.library.concurrent.redisson.support.RedisFairLockAspect;
import cn.srd.library.concurrent.redisson.support.RedisLockAspectSupporter;
import cn.srd.library.concurrent.redisson.support.RedisNonFairLockAspect;
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
@Import({
        RedisFairLockHandler.class,
        RedisFairLockAspect.class,
        RedisNonFairLockHandler.class,
        RedisNonFairLockAspect.class,
        RedisLockAspectSupporter.class
})
public @interface EnableRedisLock {

}
