package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheDefaultKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.CacheKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.CacheType;

import java.lang.annotation.*;

/**
 * TODO wjm 待实现：condition、unless
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

    CacheType[] cacheTypes() default {};

    String key() default "";

    Class<? extends CacheKeyGenerator> keyGenerator() default CacheDefaultKeyGenerator.class;

    boolean enablePreventCachePenetrate() default false;

}
