package cn.srd.itcp.sugar.redisson.core.cache;

/**
 * Redisson 缓存操作
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonCacheHandler {

    public static final RedissonBucketCacheHandler BUCKET = new RedissonBucketCacheHandler();

    public static final RedissonBucketCacheHandler HASH = new RedissonBucketCacheHandler();

    public static final RedissonBucketCacheHandler LIST = new RedissonBucketCacheHandler();

    public static final RedissonBucketCacheHandler SET = new RedissonBucketCacheHandler();

    public static final RedissonBucketCacheHandler SORTED_SET = new RedissonBucketCacheHandler();

}
