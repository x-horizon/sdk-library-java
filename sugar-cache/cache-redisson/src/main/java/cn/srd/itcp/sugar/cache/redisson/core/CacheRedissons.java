package cn.srd.itcp.sugar.cache.redisson.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Redisson Cache Operation
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheRedissons {

    /**
     * singleton pattern
     */
    private static final CacheRedissons INSTANCE = new CacheRedissons();

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static CacheRedissons getInstance() {
        return INSTANCE;
    }

    /**
     * 获取缓存操作实例（桶）
     *
     * @return 缓存操作实例
     */
    public CacheRedissonTemplate withBucket() {
        return CacheRedissonBucket.INSTANCE;
    }

    /**
     * 获取缓存操作实例（哈希）
     *
     * @return 缓存操作实例
     */
    public CacheRedissonTemplate withHash() {
        return CacheRedissonHash.INSTANCE;
    }

    /**
     * 获取缓存操作实例（List）
     *
     * @return 缓存操作实例
     */
    public CacheRedissonTemplate withList() {
        return CacheRedissonList.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Set）
     *
     * @return 缓存操作实例
     */
    public CacheRedissonTemplate withSet() {
        return CacheRedissonSet.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Sorted Set）
     *
     * @return 缓存操作实例
     */
    public CacheRedissonTemplate withSortedSet() {
        return CacheRedissonSortedSet.INSTANCE;
    }

}
