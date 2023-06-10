package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.CacheType;

import java.lang.annotation.*;

/**
 * TODO wjm 待实现：condition、unless
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheWrite {

    String[] namespaces() default {};

    CacheType[] cacheTypes() default {};

    String key() default "";

    Class<? extends CacheKeyGenerator> keyGenerator() default CacheKeyGenerator.DEFAULT_KEY_GENERATOR;

    boolean enablePreventCachePenetrate() default false;

}
