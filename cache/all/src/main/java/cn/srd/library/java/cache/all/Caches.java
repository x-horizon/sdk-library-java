// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.cache.all;

import cn.srd.library.java.cache.caffeine.CaffeineCache;
import cn.srd.library.java.cache.caffeine.CaffeineCacheTemplate;
import cn.srd.library.java.cache.caffeine.properties.CacheCaffeineProperties;
import cn.srd.library.java.cache.map.MapCache;
import cn.srd.library.java.cache.map.MapCacheTemplate;
import cn.srd.library.java.cache.redis.RedisCache;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
     * @param cacheCaffeineProperties {@link CacheCaffeineProperties}
     * @param <K>                     cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine(CacheCaffeineProperties cacheCaffeineProperties) {
        return CaffeineCache.newInstance(cacheCaffeineProperties);
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