package cn.srd.itcp.sugar.redisson.core.cache;

/**
 * Redisson 缓存操作
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonCacheHandler {

    public static final RedissonBucketCacheHandler BUCKET = new RedissonBucketCacheHandler();

    public static final RedissonHashCacheHandler HASH = new RedissonHashCacheHandler();

    public static final RedissonListCacheHandler LIST = new RedissonListCacheHandler();

    public static final RedissonSetCacheHandler SET = new RedissonSetCacheHandler();

    public static final RedissonSortedSetCacheHandler SORTED_SET = new RedissonSortedSetCacheHandler();

}
