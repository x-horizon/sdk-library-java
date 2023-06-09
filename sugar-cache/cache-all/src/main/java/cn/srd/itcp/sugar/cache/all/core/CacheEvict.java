package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheType;

import java.lang.annotation.*;

/**
 * TODO wjm 待实现：keyGenerator、condition、unless、allEntries、beforeInvocation
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheEvict {

    String[] namespaces() default {};

    String key() default "";

    CacheType[] cacheTypes() default {};

    boolean enablePreventCachePenetrate() default false;

}
