package cn.srd.itcp.sugar.cache.all.core;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Caching {

    CacheRead[] read() default {};

    CacheWrite[] write() default {};

    CacheEvict[] evict() default {};

}
