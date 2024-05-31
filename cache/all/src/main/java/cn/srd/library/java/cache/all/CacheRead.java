package cn.srd.library.java.cache.all;

import cn.srd.library.java.cache.all.model.enums.CacheType;
import cn.srd.library.java.cache.all.strategy.CacheDefaultKeyGenerator;
import cn.srd.library.java.cache.all.strategy.CacheKeyGenerator;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * annotation to read cache
 * TODO wjm unimplemented：condition、unless
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheRead {

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
     * specify cache key, support to use spring expression language (SpEL) expression for computing the key dynamically.
     * for example:
     * TODO wjm to be added
     * </pre>
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

}