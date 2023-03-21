package cn.srd.itcp.sugar.cache.redisson.common.core.cache;

import cn.srd.itcp.sugar.cache.redisson.common.support.RedissonManager;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import org.redisson.api.RBatch;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Redisson 缓存操作（桶）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonBucketCacheHandler {

    /**
     * protected block constructor
     */
    protected RedissonBucketCacheHandler() {
    }

    /**
     * 设置缓存
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     */
    public <T> void set(String key, T value) {
        RedissonManager.getClient().getBucket(key).set(value);
    }

    /**
     * 批量设置缓存
     *
     * @param values         操作集合
     * @param getKeyFunction 如何获取要缓存的 key 名，将操作集合中元素本身作为要缓存的对象
     * @param <T>            操作集合中元素的类型
     */
    public <T> void set(List<T> values, Function<T, String> getKeyFunction) {
        RedissonManager.getClient().getBuckets().set(CollectionsUtil.toMap(values, getKeyFunction));
    }

    /**
     * 批量设置缓存
     *
     * @param values           操作集合
     * @param getKeyFunction   如何获取要缓存的 key 名
     * @param getValueFunction 如何获取要缓存的对象
     * @param <T>              操作集合中元素的类型
     * @param <V>              要缓存对象的类型
     */
    public <T, V> void set(List<T> values, Function<T, String> getKeyFunction, Function<T, V> getValueFunction) {
        RedissonManager.getClient().getBuckets().set(CollectionsUtil.toMap(values, getKeyFunction, getValueFunction));
    }

    /**
     * see {@link #set(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param timeUnit   过期时间单位
     * @param <T>        要缓存对象的类型
     */
    public <T> void set(String key, T value, long expiration, TimeUnit timeUnit) {
        RedissonManager.getClient().getBucket(key).set(value, expiration, timeUnit);
    }

    /**
     * 如果已存在缓存，将其覆盖，如果不存在缓存，不进行设置
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    public <T> boolean setIfExists(String key, T value) {
        return RedissonManager.getClient().getBucket(key).setIfExists(value);
    }

    /**
     * see {@link #setIfExists(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param timeUnit   过期时间单位
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    public <T> boolean setIfExists(String key, T value, long expiration, TimeUnit timeUnit) {
        return RedissonManager.getClient().getBucket(key).setIfExists(value, expiration, timeUnit);
    }

    /**
     * 如果不存在缓存，则设置进去，否则不进行设置
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    public <T> boolean setIfNotExists(String key, T value) {
        return RedissonManager.getClient().getBucket(key).trySet(value);
    }

    /**
     * see {@link #setIfNotExists(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param timeUnit   过期时间单位
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    public <T> boolean setIfNotExists(String key, T value, long expiration, TimeUnit timeUnit) {
        return RedissonManager.getClient().getBucket(key).trySet(value, expiration, timeUnit);
    }

    /**
     * 若旧缓存为 <code>expectedValue</code>，则将其更新为 <code>updateValue</code>，否则不进行操作
     *
     * @param key           缓存 key 名
     * @param expectedValue 期望值
     * @param updateValue   更新至
     * @param <T>           要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    public <T> boolean compareAndSet(String key, T expectedValue, T updateValue) {
        return RedissonManager.getClient().getBucket(key).compareAndSet(expectedValue, updateValue);
    }

    /**
     * 获取缓存
     *
     * @param key   缓存 key 名
     * @param clazz 缓存对象的类对象
     * @param <T>   缓存对象的类型
     * @return 缓存对象
     */
    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(RedissonManager.getClient().getBucket(key).get());
    }

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <T>  缓存对象的类型
     * @return 缓存对象
     */
    public <T> List<T> get(String... keys) {
        return CollectionsUtil.toList(RedissonManager.getClient().getBuckets().get(keys));
    }

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <T>  缓存对象的类型
     * @return 缓存对象
     */
    public <T> List<T> get(List<String> keys) {
        return CollectionsUtil.toList(RedissonManager.getClient().getBuckets().get(CollectionsUtil.toArray(keys, String.class)));
    }

    /**
     * 模糊查询缓存
     *
     * @param pattern key 表达式，如 cache:*
     * @param <T>     缓存对象的类型
     * @return 缓存对象
     */
    public <T> List<T> getByPattern(String pattern) {
        String[] keys = CollectionsUtil.toArray(RedissonManager.getClient().getKeys().getKeysByPattern(pattern), String.class);
        return CollectionsUtil.toList(RedissonManager.getClient().getBuckets().get(keys));
    }

    /**
     * 获取旧的缓存对象，并将新的缓存对象设置进去
     *
     * @param key      缓存 key 名
     * @param value    要缓存的对象
     * @param oldClazz 旧缓存对象的类对象
     * @param <T>      要缓存对象的类型
     * @return 旧缓存对象
     */
    public <T> T getAndSet(String key, T value, Class<T> oldClazz) {
        return oldClazz.cast(RedissonManager.getClient().getBucket(key).getAndSet(value));
    }

    /**
     * see {@link #getAndSet(String, Object, Class)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param oldClazz   旧缓存对象的类对象
     * @param expiration 过期时间
     * @param timeUnit   过期时间单位
     * @param <T>        要缓存对象的类型
     * @return 旧缓存对象
     */
    public <T> T getAndSet(String key, T value, Class<T> oldClazz, long expiration, TimeUnit timeUnit) {
        return oldClazz.cast(RedissonManager.getClient().getBucket(key).getAndSet(value, expiration, timeUnit));
    }

    /**
     * 获取缓存对象，并将其删除
     *
     * @param key   缓存 key 名
     * @param clazz 缓存对象的类对象
     * @param <T>   缓存对象的类型
     * @return 缓存对象
     */
    public <T> T getAndDelete(String key, Class<T> clazz) {
        return clazz.cast(RedissonManager.getClient().getBucket(key).getAndDelete());
    }

    /**
     * 删除缓存对象
     *
     * @param key 缓存 key 名
     */
    public void delete(String key) {
        RedissonManager.getClient().getBucket(key).getAndDelete();
    }

    /**
     * 删除缓存对象
     *
     * @param keys 缓存 key 名
     * @return 受影响个数
     */
    public int delete(String... keys) {
        RBatch pipeline = RedissonManager.getClient().createBatch();
        pipeline.getKeys().deleteAsync(keys);
        return pipeline.execute().getResponses().size();
    }

    /**
     * 删除缓存对象
     *
     * @param keys 缓存 key 名
     * @return 受影响个数
     */
    public int delete(List<String> keys) {
        RBatch pipeline = RedissonManager.getClient().createBatch();
        pipeline.getKeys().deleteAsync(CollectionsUtil.toArray(keys, String.class));
        return pipeline.execute().getResponses().size();
    }

    /**
     * 删除缓存对象
     *
     * @param pattern key 表达式，如 cache:*
     * @return 受影响个数
     */
    public int deleteByPattern(String pattern) {
        RBatch pipeline = RedissonManager.getClient().createBatch();
        pipeline.getKeys().deleteByPatternAsync(pattern);
        return pipeline.execute().getResponses().size();
    }

}
