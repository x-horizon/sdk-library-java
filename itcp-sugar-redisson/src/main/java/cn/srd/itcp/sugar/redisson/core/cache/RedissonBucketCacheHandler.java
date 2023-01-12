package cn.srd.itcp.sugar.redisson.core.cache;

import cn.srd.itcp.sugar.redisson.support.RedissonManager;

import java.util.concurrent.TimeUnit;

/**
 * Redisson 缓存操作（桶）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonBucketCacheHandler {

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

}
