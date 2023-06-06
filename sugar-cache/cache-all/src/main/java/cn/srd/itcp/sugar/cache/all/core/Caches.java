package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCacheTemplate;
import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCaches;
import cn.srd.itcp.sugar.cache.redisson.common.core.RedissonCaches;

public class Caches {

    /**
     * private block constructor
     */
    private Caches() {
    }

    /**
     * caffeine implement
     *
     * @return redisson
     */
    public static <K> CaffeineCacheTemplate<K> withCaffeine() {
        return CaffeineCaches.newInstance();
    }

    /**
     * redisson implement
     *
     * @return redisson
     */
    public static RedissonCaches withRedisson() {
        return RedissonCaches.getInstance();
    }

}
