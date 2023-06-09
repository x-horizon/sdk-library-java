package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheType;

import java.lang.annotation.*;

/**
 * TODO wjm 待实现：keyGenerator、condition、unless
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheRead {

    String[] namespaces() default {};

    String key() default "";

    CacheType[] cacheTypes() default {};

    boolean enablePreventCachePenetrate() default false;

}
