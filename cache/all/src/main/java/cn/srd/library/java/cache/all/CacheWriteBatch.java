package cn.srd.library.java.cache.all;

import cn.srd.library.java.cache.all.manager.CacheMode;
import cn.srd.library.java.cache.all.manager.CacheType;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;
import java.util.Collection;

/**
 * annotation to write batch cache, it will write cache after pointcut, the return type of pointcut return value must be {@link Collection}
 * TODO wjm unimplemented：condition、unless
 *
 * @author wjm
 * @since 2023-06-28 09:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheWriteBatch {

    /**
     * see {@link CacheConfig#namespaces()}
     *
     * @return the cache namespace
     */
    String[] namespaces() default {};

    /**
     * see {@link CacheConfig#cacheTypes()}
     *
     * @return the cache type
     */
    CacheType[] cacheTypes() default {};

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
     * see {@link CacheConfig#allowEmptyValue()}
     *
     * @return allow or not to set a {@link NullValue} in cache
     */
    boolean allowEmptyValue() default false;

}