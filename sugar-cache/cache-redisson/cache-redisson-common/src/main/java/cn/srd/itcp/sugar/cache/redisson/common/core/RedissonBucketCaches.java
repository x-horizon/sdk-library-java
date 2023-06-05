package cn.srd.itcp.sugar.cache.redisson.common.core;

import cn.srd.itcp.sugar.context.redisson.core.RedissonManager;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.redisson.api.RBatch;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.List;
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
    public <T> void set(String key, T value, Duration expiration) {
        if (Objects.equals(Duration.ZERO, expiration)) {
            RedissonManager.getClient().getBucket(key).set(value);
            return;
        }
        /**
         * 实现参考：{@link ValueOperations#set(Object, Object, Duration)}
         */
        if (TimeoutUtils.hasMillis(expiration)) {
            RedissonManager.getClient().getBucket(key).set(value, expiration.toMillis(), TimeUnit.MILLISECONDS);
            return;
        }
        RedissonManager.getClient().getBucket(key).set(value, expiration.getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setIfExists(String key, T value, Duration expiration) {
        if (Objects.equals(Duration.ZERO, expiration)) {
            return RedissonManager.getClient().getBucket(key).setIfExists(value);
        }
        /**
         * 实现参考：{@link ValueOperations#set(Object, Object, Duration)}
         */
        if (TimeoutUtils.hasMillis(expiration)) {
            return RedissonManager.getClient().getBucket(key).setIfExists(value, expiration.toMillis(), TimeUnit.MILLISECONDS);
        }
        return RedissonManager.getClient().getBucket(key).setIfExists(value, expiration.getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setIfAbsent(String key, T value, Duration expiration) {
        if (Objects.equals(Duration.ZERO, expiration)) {
            return RedissonManager.getClient().getBucket(key).setIfAbsent(value);
        }
        return RedissonManager.getClient().getBucket(key).setIfAbsent(value, expiration);
    }

    @Override
    public <T> boolean compareAndSet(String key, T expectedValue, T updateValue) {
        return RedissonManager.getClient().getBucket(key).compareAndSet(expectedValue, updateValue);
    }

    @Override
    public Object get(String key) {
        return convertWithNullValue(RedissonManager.getClient().getBucket(key).get());
    }

    @Override
    public <T> List<T> get(String... keys) {
        return CollectionsUtil.toListIgnoreNullAndSpecifiedClass(RedissonManager.getClient().getBuckets().get(keys), NullValue.class);
    }

    @Override
    public <T> List<T> getByPattern(String pattern) {
        return get(CollectionsUtil.toArray(RedissonManager.getClient().getKeys().getKeysByPattern(pattern), String.class));
    }

    public Object getAndSet(String key, Object value) {
        return convertWithNullValue(RedissonManager.getClient().getBucket(key).getAndSet(value));
    }

    public <T> T getAndSet(String key, T value, Class<T> oldClazz, Duration expiration) {
        if (Objects.equals(Duration.ZERO, expiration)) {
            return getAndSet(key, value, oldClazz);
        }
        /**
         * 实现参考：{@link ValueOperations#set(Object, Object, Duration)}
         */
        if (TimeoutUtils.hasMillis(expiration)) {
            return oldClazz.cast(convertWithNullValue(RedissonManager.getClient().getBucket(key).getAndSet(value, expiration.toMillis(), TimeUnit.MILLISECONDS)));
        }
        return oldClazz.cast(convertWithNullValue(RedissonManager.getClient().getBucket(key).getAndSet(value, expiration.getSeconds(), TimeUnit.SECONDS)));
    }

    @Override
    public <T> T getAndDelete(String key, Class<T> clazz) {
        return clazz.cast(convertWithNullValue(RedissonManager.getClient().getBucket(key).getAndDelete()));
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
    public int deleteByNamespace(String namespace) {
        return deleteByPattern(namespace + ":*");
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

}
