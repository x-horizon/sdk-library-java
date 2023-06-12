package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.manager.Cache;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheDefaultKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheKeyGenerator;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * <pre>
 * global configuration for following:
 *
 * {@link CacheRead}
 * {@link CacheWrite}
 * {@link CacheEvict}
 * {@link Caching}
 * </pre>
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheConfig {

    /**
     * <pre>
     * specify cache namespace:
     * 1. one namespace represents one {@link Cache} instance;
     * 2. in local cache type, use the key name as cache key;
     * 3. in distributed cache type, use namespace and key name as cache key, like "my-cache:1";
     *
     * it will take effect on all methods under the class which marked {@link CacheConfig}, if not specify namespace on method, will use this namespace, or else use namespace on method;
     * </pre>
     *
     * @return the cache namespace
     */
    String[] namespaces() default {};

    /**
     * <pre>
     * you can save multilevel cache by use this field, it will do cache in asc order, example:
     *  if you set cacheType to {{@link CacheType#MAP}, {@link CacheType#REDIS}}:
     *    when use @{@link CacheRead}:
     *     first read from cache, the read order is: map -> redis -> method, once read, will not continue reading;
     *     then write cache if not miss from cache, the write order is: redis -> map;
     *    when use @{@link CacheWrite}:
     *     it will write cache when get a not null result from method return, the write order is: redis -> map;
     *    when use @{@link CacheEvict}:
     *     the evict order is: redis -> map, clear the cache data in lowest cache level first, and then clear higher level cache to avoid loading other requests from lowest cache into higher cache in a short period of time;
     *
     * it will take effect on all methods under the class which marked {@link CacheConfig}, if not specify cache type on method, will use this cache type, or else use cache type on method;
     * </pre>
     *
     * @return the cache type
     */
    CacheType[] cacheTypes() default {};

    /**
     * <pre>
     * specify the way to generate cache key,
     * if the cache key is not blank, going to use the specified key as cache key,
     * else use the specified key generator to generate key.
     *
     * it will take effect on all methods under the class which marked {@link CacheConfig}, only use key generator on method if specify generator which is not {@link CacheDefaultKeyGenerator} on method
     * </pre>
     *
     * @return the key generator
     */
    Class<? extends CacheKeyGenerator> keyGenerator() default CacheDefaultKeyGenerator.class;

    /**
     * <pre>
     * allow or not to set a {@link NullValue} in cache,
     * if set it true: it will set a {@link NullValue} to cache in the following case:
     * 1. when use {@link CacheWrite} method return null;
     * 2. when use {@link CacheEvict} to delete some cache;
     * 3. if set {@link CacheEvict#needEvictAllInNamespaces()} to true, it will delete all cache in specified namespaces instead of set {@link NullValue};
     *
     * this field can prevent cache penetrate, when hit a null value, it can read from cache and never execute method;
     *
     * the most important you need to focus:
     * for the same namespace, only true or false can be set,
     * once the namespace's {@link Cache} instance init, allow or not {@link NullValue} will be fixed and never change in the future,
     *
     * it will take effect on all methods under the class which marked {@link CacheConfig}, please ensure set the same value for the same namespace;
     * </pre>
     *
     * @return allow or not to set a {@link NullValue} in cache
     */
    boolean allowNullValueInCache() default false;

}
