package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheDefaultKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.CacheKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.CacheType;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheConfig {

    String[] namespaces() default {};

    CacheType[] cacheTypes() default {};

    Class<? extends CacheKeyGenerator> keyGenerator() default CacheDefaultKeyGenerator.class;

    /**
     * NullValue 失真的问题，且对于同一个 namespace，不能一会开启，一会关闭穿透（因为同一个 namespace 在第一次初始化时，enablePreventCachePenetrate 就已经被固化下来了）
     *
     * @return
     */
    boolean enablePreventCachePenetrate() default false;

}
