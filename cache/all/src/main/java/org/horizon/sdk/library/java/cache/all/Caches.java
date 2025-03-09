package org.horizon.sdk.library.java.cache.all;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.cache.caffeine.CaffeineCache;
import org.horizon.sdk.library.java.cache.caffeine.CaffeineCacheTemplate;
import org.horizon.sdk.library.java.cache.caffeine.model.property.CaffeineCacheProperty;
import org.horizon.sdk.library.java.cache.map.MapCache;
import org.horizon.sdk.library.java.cache.map.MapCacheTemplate;
import org.horizon.sdk.library.java.cache.redis.RedisCache;

/**
 * all cache can be used
 *
 * @author wjm
 * @since 2023-06-07 16:48
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
        return MapCache.newInstance();
    }

    /**
     * map implement
     *
     * @param initialCapacity initial capacity
     * @param <K>             cache key type
     * @return cache operation object
     */
    public static <K> MapCacheTemplate<K> withMap(int initialCapacity) {
        return MapCache.newInstance(initialCapacity);
    }

    /**
     * caffeine implement
     *
     * @param <K> cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine() {
        return CaffeineCache.newInstance();
    }

    /**
     * caffeine implement
     *
     * @param caffeineCacheProperty {@link CaffeineCacheProperty}
     * @param <K>                   cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine(CaffeineCacheProperty caffeineCacheProperty) {
        return CaffeineCache.newInstance(caffeineCacheProperty);
    }

    /**
     * redis implement
     *
     * @return cache operation object
     */
    public static RedisCache withRedis() {
        return RedisCache.getInstance();
    }

}