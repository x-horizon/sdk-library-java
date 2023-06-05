package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.redisson.common.core.RedissonCaches;

public class Caches {

    /**
     * private block constructor
     */
    private Caches() {
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
