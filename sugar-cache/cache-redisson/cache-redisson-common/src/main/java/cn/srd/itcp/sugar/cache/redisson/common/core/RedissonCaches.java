package cn.srd.itcp.sugar.cache.redisson.common.core;

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
     * get singleton instance
     *
     * @return instance
     */
    public static RedissonCaches getInstance() {
        return INSTANCE;
    }

    /**
     * 获取缓存操作实例（桶）
     *
     * @return 缓存操作实例
     */
    public RedissonCacheTemplate withBucket() {
        return RedissonBucketCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（哈希）
     *
     * @return 缓存操作实例
     */
    public RedissonCacheTemplate withHash() {
        return RedissonHashCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（List）
     *
     * @return 缓存操作实例
     */
    public RedissonCacheTemplate withList() {
        return RedissonListCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Set）
     *
     * @return 缓存操作实例
     */
    public RedissonCacheTemplate withSet() {
        return RedissonSetCaches.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Sorted Set）
     *
     * @return 缓存操作实例
     */
    public RedissonCacheTemplate withSortedSet() {
        return RedissonSortedSetCaches.INSTANCE;
    }

}
