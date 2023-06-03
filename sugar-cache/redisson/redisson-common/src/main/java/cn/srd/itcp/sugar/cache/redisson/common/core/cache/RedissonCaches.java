package cn.srd.itcp.sugar.cache.redisson.common.core.cache;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;

/**
 * Redisson 缓存操作
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonCaches {

    /**
     * private block constructor
     */
    private RedissonCaches() {
    }

    /**
     * singleton pattern
     */
    private static final RedissonCaches INSTANCE = new RedissonCaches();

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static RedissonCaches getInstance() {
        return INSTANCE;
    }

    /**
     * 获取缓存操作实例（桶）
     *
     * @return 缓存操作实例
     */
    public CacheTemplate withBucket() {
        return RedissonBucketCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（哈希）
     *
     * @return 缓存操作实例
     */
    public CacheTemplate withHash() {
        return RedissonHashCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（List）
     *
     * @return 缓存操作实例
     */
    public CacheTemplate withList() {
        return RedissonListCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Set）
     *
     * @return 缓存操作实例
     */
    public CacheTemplate withSet() {
        return RedissonSetCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Sorted Set）
     *
     * @return 缓存操作实例
     */
    public CacheTemplate withSortedSet() {
        return RedissonSortedSetCaches.INSTANCE;
    }

}
