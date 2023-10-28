package cn.srd.library.java.cache.all;

import cn.srd.library.java.cache.all.support.manager.CacheType;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * annotation to read all cache
 * TODO wjm unimplemented：condition、unless
 *
 * @author wjm
 * @since 2023-06-18 02:20:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheReadAll {

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
     * <pre>
     * see {@link CacheRead#key()}.
     * when cannot find value in cache, it will execute pointcut and use this field to generate a key and set to cache.
     * </pre>
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
