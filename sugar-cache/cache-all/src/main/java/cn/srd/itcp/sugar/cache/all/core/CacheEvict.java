package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheDefaultKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheKeyGenerator;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * annotation to evict cache
 * TODO wjm unimplemented：condition、unless
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheEvict {

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
     * see {@link CacheRead#key()}
     *
     * @return the cache key
     */
    String key() default "";

    /**
     * see {@link CacheConfig#keyGenerator()}
     *
     * @return the key generator
     */
    Class<? extends CacheKeyGenerator> keyGenerator() default CacheDefaultKeyGenerator.class;

    /**
     * see {@link CacheConfig#allowEmptyValue()}
     *
     * @return allow or not to set a {@link NullValue} in cache
     */
    boolean allowEmptyValue() default false;

    /**
     * <pre>
     * it will execute method first then evict from cache in default,
     * if evict from cache first, other threads may appear to read the old data and put it into the cache, after that, the cache data has always been the old data,
     * set it to true to evict from cache first if you really need.
     * </pre>
     *
     * @return need to evict from cache first
     */
    boolean needEvictBeforeProceed() default false;

    /**
     * <pre>
     * if set it to true, all data in {@link #namespaces()} or {@link CacheConfig#namespaces()} will be evict,
     * in this case, {@link #key()} or {@link #keyGenerator()} or {@link CacheConfig#keyGenerator()} or {@link #allowEmptyValue()} or {@link CacheConfig#allowEmptyValue()} will not work.
     * </pre>
     *
     * @return need to evict all data in {@link #namespaces()} or {@link CacheConfig#namespaces()}
     */
    boolean needEvictAllInNamespaces() default false;

}
