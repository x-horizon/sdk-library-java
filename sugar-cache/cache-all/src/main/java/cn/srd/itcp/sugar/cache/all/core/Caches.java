package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.caffeine.config.properties.CaffeineCacheProperties;
import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCacheTemplate;
import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCaches;
import cn.srd.itcp.sugar.cache.map.core.MapCacheTemplate;
import cn.srd.itcp.sugar.cache.map.core.MapCaches;
import cn.srd.itcp.sugar.cache.redisson.core.RedissonCaches;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * all cache can be used
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Caches {

    /**
     * map implement
     *
     * @param <K> cache key type
     * @return cache operation object
     */
    public static <K> MapCacheTemplate<K> withMap() {
        return MapCaches.newInstance();
    }

    /**
     * map implement
     *
     * @param initialCapacity initial capacity
     * @param <K>             cache key type
     * @return cache operation object
     */
    public static <K> MapCacheTemplate<K> withMap(int initialCapacity) {
        return MapCaches.newInstance(initialCapacity);
    }

    /**
     * caffeine implement
     *
     * @param <K> cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine() {
        return CaffeineCaches.newInstance();
    }

    /**
     * caffeine implement
     *
     * @param caffeineCacheProperties {@link CaffeineCacheProperties}
     * @param <K>                     cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine(CaffeineCacheProperties caffeineCacheProperties) {
        return CaffeineCaches.newInstance(caffeineCacheProperties);
    }

    /**
     * redisson implement
     *
     * @return cache operation object
     */
    public static RedissonCaches withRedisson() {
        return RedissonCaches.getInstance();
    }

}
