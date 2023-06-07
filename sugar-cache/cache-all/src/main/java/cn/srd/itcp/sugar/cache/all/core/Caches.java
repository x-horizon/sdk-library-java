package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCacheTemplate;
import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCaches;
import cn.srd.itcp.sugar.cache.map.core.MapCacheTemplate;
import cn.srd.itcp.sugar.cache.map.core.MapCaches;
import cn.srd.itcp.sugar.cache.redisson.core.RedissonCaches;

public class Caches {

    /**
     * private block constructor
     */
    private Caches() {
    }

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
     * caffeine implement
     *
     * @param <K> cache key type
     * @return cache operation object
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine() {
        return CaffeineCaches.newInstance();
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
