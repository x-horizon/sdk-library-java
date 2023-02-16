package cn.srd.itcp.sugar.database.redisson.webmvc.core;

import cn.srd.itcp.sugar.database.redisson.webmvc.support.RedissonWebMVCExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link RedissonWebMVCExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RedissonWebMVCExceptionHandler.class)
public @interface EnableRedissonWebMVCExceptionHandler {

}
