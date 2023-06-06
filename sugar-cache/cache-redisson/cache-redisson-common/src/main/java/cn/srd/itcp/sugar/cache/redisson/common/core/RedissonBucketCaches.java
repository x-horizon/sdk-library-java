package cn.srd.itcp.sugar.cache.redisson.common.core;

import cn.srd.itcp.sugar.context.redisson.core.RedissonManager;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.core.TimeoutUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redisson 缓存操作（桶）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonBucketCaches implements RedissonCacheTemplate {

    /**
     * protected block constructor
     */
    protected RedissonBucketCaches() {
    }

    /**
     * singleton pattern
     */
    protected static final RedissonBucketCaches INSTANCE = new RedissonBucketCaches();

    @Override
    public <V> void set(String key, V value, Duration expiration) {
        RBucket<V> cache = RedissonManager.getClient().getBucket(key);
        if (Objects.equals(Duration.ZERO, expiration)) {
            cache.set(value);
        } else if (TimeoutUtils.hasMillis(expiration)) {
            cache.set(value, expiration.toMillis(), TimeUnit.MILLISECONDS);
        } else {
            cache.set(value, expiration.getSeconds(), TimeUnit.SECONDS);
        }
    }

    @Override
    public <V> boolean setIfExists(String key, V value, Duration expiration) {
        RBucket<V> cache = RedissonManager.getClient().getBucket(key);
        if (Objects.equals(Duration.ZERO, expiration)) {
            return cache.setIfExists(value);
        }
        if (TimeoutUtils.hasMillis(expiration)) {
            return cache.setIfExists(value, expiration.toMillis(), TimeUnit.MILLISECONDS);
        }
        return cache.setIfExists(value, expiration.getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public <V> boolean setIfAbsent(String key, V value, Duration expiration) {
        RBucket<V> cache = RedissonManager.getClient().getBucket(key);
        if (Objects.equals(Duration.ZERO, expiration)) {
            return cache.setIfAbsent(value);
        }
        return cache.setIfAbsent(value, expiration);
    }

    @Override
    public <V> boolean compareAndSet(String key, V expectedValue, V updateValue) {
        return RedissonManager.getClient().getBucket(key).compareAndSet(expectedValue, updateValue);
    }

    @Override
    public Object getAndSet(String key, Object value) {
        Object output = get(key);
        set(key, value);
        return output;
    }

    @Override
    public <V> V getAndSet(String key, V value, Class<V> oldClazz, Duration expiration) {
        V output = get(key, oldClazz);
        if (Objects.equals(Duration.ZERO, expiration)) {
            set(key, value);
        } else if (TimeoutUtils.hasMillis(expiration)) {
            // 实现参考：{@link ValueOperations#set(Object, Object, Duration)}
            set(key, value, expiration.toMillis(), TimeUnit.MILLISECONDS);
        } else {
            set(key, value, expiration.getSeconds(), TimeUnit.SECONDS);
        }
        return output;
    }

    @Override
    public Object get(String key) {
        return convertWithNullValue(RedissonManager.getClient().getBucket(key).get());
    }

    @Override
    public <V> List<V> get(String... keys) {
        return CollectionsUtil.toList(getMap(keys));
    }

    @Override
    public <V> List<V> get(Collection<String> keys) {
        return get(CollectionsUtil.toArray(keys, String.class));
    }

    @Override
    public <V> Map<String, V> getMap(String... keys) {
        return CollectionsUtil.filterNullAndSpecifiedClass(RedissonManager.getClient().getBuckets().get(keys), NullValue.class);
    }

    @Override
    public <V> Map<String, V> getMap(Collection<String> keys) {
        return getMap(CollectionsUtil.toArray(keys, String.class));
    }

    @Override
    public <V> List<V> getByNamespace(String namespace) {
        return getByPattern(namespace + NAMESPACE_KEY_WORD);
    }

    @Override
    public <V> List<V> getByPattern(String pattern) {
        return get(CollectionsUtil.toArray(RedissonManager.getClient().getKeys().getKeysByPattern(pattern), String.class));
    }

    @Override
    public Object getAndDelete(String key) {
        return convertWithNullValue(RedissonManager.getClient().getBucket(key).getAndDelete());
    }

    @Override
    public void delete(String key) {
        RedissonManager.getClient().getBucket(key).getAndDelete();
    }

    @Override
    public int delete(String... keys) {
        RBatch pipeline = RedissonManager.getClient().createBatch();
        pipeline.getKeys().deleteAsync(keys);
        return pipeline.execute().getResponses().size();
    }

    @Override
    public int delete(Collection<String> keys) {
        return delete(CollectionsUtil.toArray(keys, String.class));
    }

    @Override
    public int deleteByNamespace(String namespace) {
        return deleteByPattern(namespace + NAMESPACE_KEY_WORD);
    }

    @Override
    public int deleteByPattern(String pattern) {
        RBatch pipeline = RedissonManager.getClient().createBatch();
        pipeline.getKeys().deleteByPatternAsync(pattern);
        return pipeline.execute().getResponses().size();
    }

    @Override
    public long getExpirationTime(String key) {
        return RedissonManager.getClient().getBucket(key).getExpireTime();
    }

    @Override
    public long getTimeToLive(String key) {
        return RedissonManager.getClient().getBucket(key).remainTimeToLive();
    }

    // TODO wjm 这两个函数应放到 RedissonListCacheHandler 或 其他 Handler 中，包括 get list 的也是在那边实现
    // /**
    //  * 批量设置缓存
    //  *
    //  * @param values         操作集合
    //  * @param getKeyFunction 如何获取要缓存的 key 名，将操作集合中元素本身作为要缓存的对象
    //  * @param <T>            操作集合中元素的类型
    //  */
    // public <T> void set(List<T> values, Function<T, String> getKeyFunction) {
    //     RedissonManager.getClient().getBuckets().set(CollectionsUtil.toMap(values, getKeyFunction));
    // }
    //
    // /**
    //  * 批量设置缓存
    //  *
    //  * @param values           操作集合
    //  * @param getKeyFunction   如何获取要缓存的 key 名
    //  * @param getValueFunction 如何获取要缓存的对象
    //  * @param <T>              操作集合中元素的类型
    //  * @param <V>              要缓存对象的类型
    //  */
    // public <T, V> void set(List<T> values, Function<T, String> getKeyFunction, Function<T, V> getValueFunction) {
    //     RedissonManager.getClient().getBuckets().set(CollectionsUtil.toMap(values, getKeyFunction, getValueFunction));
    // }

    // cache.set(CACHE_NAME1, CACHE_OBJECT1);
    // boolean result21 = cache.compareAndSet(CACHE_NAME1, CACHE_OBJECT2, CACHE_OBJECT2);
    // boolean result22 = cache.compareAndSet(CACHE_NAME1, CACHE_OBJECT1, CACHE_OBJECT2);
    //
    // cache.deleteAll();

}
