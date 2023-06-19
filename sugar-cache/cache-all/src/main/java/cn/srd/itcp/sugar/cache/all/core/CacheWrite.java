package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheComponentType;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheMode;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * annotation to write cache
 * TODO wjm unimplemented：condition、unless
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheWrite {

    /**
     * see {@link CacheConfig#namespaces()}
     *
     * @return the cache namespace
     */
    String[] namespaces() default {};

    /**
     * see {@link CacheConfig#cacheComponentTypes()}
     *
     * @return the cache type
     */
    CacheComponentType[] cacheComponentTypes() default {};

    /**
     * see {@link CacheMode}
     *
     * @return the cache mode
     */
    CacheMode cacheMode() default CacheMode.READ_ONLY;

    /**
     * see {@link CacheRead#key()}
     *
     * @return the cache key
     */
    String key() default "";

    /**
     * see {@link CacheConfig#allowNullValue()}
     *
     * @return allow or not to set a {@link NullValue} in cache
     */
    boolean allowNullValue() default false;

}
