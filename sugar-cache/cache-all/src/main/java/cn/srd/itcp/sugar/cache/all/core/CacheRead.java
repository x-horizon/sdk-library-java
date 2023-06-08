package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheType;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheRead {

    String key() default "";

    String[] namespaces() default {};

    CacheType[] cacheTypes() default {CacheType.MAP};

    boolean enablePreventCachePenetrate() default false;

}
