package cn.srd.library.cache.all.core;

import cn.srd.library.cache.caffeine.core.CaffeineCache;
import cn.srd.library.cache.caffeine.core.CaffeineCacheTemplate;
import cn.srd.library.cache.map.core.MapCache;
import cn.srd.library.cache.map.core.MapCacheTemplate;
import cn.srd.library.cache.redis.core.RedisCache;
import cn.srd.library.context.caffeine.config.properties.CaffeineCacheProperties;
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
     * @param caffeineCacheProperties {@link CaffeineCacheProperties}
     * @param <K>                     cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine(CaffeineCacheProperties caffeineCacheProperties) {
        return CaffeineCache.newInstance(caffeineCacheProperties);
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
