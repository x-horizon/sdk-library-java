package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.caffeine.core.CaffeineCaches;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
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
    public static CacheTemplate withCaffeine() {
        return CaffeineCaches.getInstance();
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
