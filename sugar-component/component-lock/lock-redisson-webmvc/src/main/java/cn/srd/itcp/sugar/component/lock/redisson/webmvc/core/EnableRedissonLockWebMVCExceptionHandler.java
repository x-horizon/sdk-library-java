package cn.srd.itcp.sugar.component.lock.redisson.webmvc.core;

import cn.srd.itcp.sugar.component.lock.redisson.webmvc.support.RedissonLockWebMVCExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link RedissonLockWebMVCExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RedissonLockWebMVCExceptionHandler.class)
public @interface EnableRedissonLockWebMVCExceptionHandler {

}
