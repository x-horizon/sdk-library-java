package cn.srd.itcp.sugar.database.redisson.core.cache;

/**
 * Redisson 缓存操作
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonCacheHandler {

    /**
     * 缓存操作实例（桶）
     */
    public static final RedissonBucketCacheHandler BUCKET = new RedissonBucketCacheHandler();

    /**
     * 缓存操作实例（哈希）
     */
    public static final RedissonHashCacheHandler HASH = new RedissonHashCacheHandler();

    /**
     * 缓存操作实例（List）
     */
    public static final RedissonListCacheHandler LIST = new RedissonListCacheHandler();

    /**
     * 缓存操作实例（Set）
     */
    public static final RedissonSetCacheHandler SET = new RedissonSetCacheHandler();

    /**
     * 缓存操作实例（Sorted Set）
     */
    public static final RedissonSortedSetCacheHandler SORTED_SET = new RedissonSortedSetCacheHandler();

}
