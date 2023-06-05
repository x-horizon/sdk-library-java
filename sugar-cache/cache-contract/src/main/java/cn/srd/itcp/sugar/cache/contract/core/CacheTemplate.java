package cn.srd.itcp.sugar.cache.contract.core;

import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.time.TimeUnitHandler;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存模板
 *
 * @author wjm
 * @since 2023-06-12 16:41:28
 */
public interface CacheTemplate {

    /**
     * 设置缓存
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     */
    default <T> void set(String key, T value) {
        set(key, value, Duration.ZERO);
    }

    /**
     * see {@link #set(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间，如 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     * @param <T>        要缓存对象的类型
     */
    default <T> void set(String key, T value, String expiration) {
        set(key, value, TimeUtil.wrapper(expiration).toMillisecond());
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
    default <T> void set(String key, T value, long expiration, TimeUnit timeUnit) {
        set(key, value, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #set(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param <T>        要缓存对象的类型
     */
    <T> void set(String key, T value, Duration expiration);

    /**
     * 如果已存在缓存，将其覆盖，如果不存在缓存，不进行设置
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> boolean setIfExists(String key, T value) {
        return setIfExists(key, value, Duration.ZERO);
    }

    /**
     * see {@link #setIfExists(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间，如 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> boolean setIfExists(String key, T value, String expiration) {
        return setIfExists(key, value, TimeUtil.wrapper(expiration).toMillisecond());
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
    default <T> boolean setIfExists(String key, T value, long expiration, TimeUnit timeUnit) {
        return setIfExists(key, value, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #setIfExists(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    <T> boolean setIfExists(String key, T value, Duration expiration);

    /**
     * 如果不存在缓存，则设置进去，否则不进行设置
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> boolean setIfAbsent(String key, T value) {
        return setIfAbsent(key, value, Duration.ZERO);
    }

    /**
     * see {@link #setIfAbsent(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间，如 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> boolean setIfAbsent(String key, T value, String expiration) {
        return setIfAbsent(key, value, TimeUtil.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #setIfAbsent(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param timeUnit   过期时间单位
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> boolean setIfAbsent(String key, T value, long expiration, TimeUnit timeUnit) {
        return setIfAbsent(key, value, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #setIfAbsent(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param expiration 过期时间
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    <T> boolean setIfAbsent(String key, T value, Duration expiration);

    /**
     * 若旧缓存为 <code>expectedValue</code>，则将其更新为 <code>updateValue</code>，否则不进行操作
     *
     * @param key           缓存 key 名
     * @param expectedValue 期望值
     * @param updateValue   更新至
     * @param <T>           要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    <T> boolean compareAndSet(String key, T expectedValue, T updateValue);

    /**
     * 获取缓存
     *
     * @param key 缓存 key 名
     * @return 缓存对象
     */
    Object get(String key);

    /**
     * 获取缓存
     *
     * @param key   缓存 key 名
     * @param clazz 缓存对象的类对象
     * @param <T>   缓存对象的类型
     * @return 缓存对象
     */
    default <T> T get(String key, Class<T> clazz) {
        return clazz.cast(get(key));
    }

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <T>  缓存对象的类型
     * @return 缓存对象
     */
    <T> List<T> get(String... keys);

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <T>  缓存对象的类型
     * @return 缓存对象
     */
    default <T> List<T> get(Collection<String> keys) {
        return get(CollectionsUtil.toArray(keys, String.class));
    }

    /**
     * 模糊查询缓存
     *
     * @param pattern key 表达式，如 cache:*
     * @param <T>     缓存对象的类型
     * @return 缓存对象
     */
    <T> List<T> getByPattern(String pattern);

    /**
     * 获取旧的缓存对象，并将新的缓存对象设置进去
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @return 旧缓存对象
     */
    Object getAndSet(String key, Object value);

    /**
     * 获取旧的缓存对象，并将新的缓存对象设置进去
     *
     * @param key      缓存 key 名
     * @param value    要缓存的对象
     * @param oldClazz 旧缓存对象的类对象
     * @param <T>      要缓存对象的类型
     * @return 旧缓存对象
     */
    default <T> T getAndSet(String key, T value, Class<T> oldClazz) {
        return oldClazz.cast(getAndSet(key, value));
    }

    /**
     * see {@link #setIfAbsent(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param oldClazz   旧缓存对象的类对象
     * @param expiration 过期时间，如 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> T setIfAbsent(String key, T value, Class<T> oldClazz, String expiration) {
        return getAndSet(key, value, oldClazz, TimeUtil.wrapper(expiration).toMillisecond());
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
    default <T> T getAndSet(String key, T value, Class<T> oldClazz, long expiration, TimeUnit timeUnit) {
        return getAndSet(key, value, oldClazz, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #getAndSet(String, Object, Class)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param oldClazz   旧缓存对象的类对象
     * @param expiration 过期时间
     * @param <T>        要缓存对象的类型
     * @return 旧缓存对象
     */
    <T> T getAndSet(String key, T value, Class<T> oldClazz, Duration expiration);

    /**
     * 获取缓存对象，并将其删除
     *
     * @param key   缓存 key 名
     * @param clazz 缓存对象的类对象
     * @param <T>   缓存对象的类型
     * @return 缓存对象
     */
    <T> T getAndDelete(String key, Class<T> clazz);

    /**
     * 删除缓存对象
     *
     * @param key 缓存 key 名
     */
    void delete(String key);

    /**
     * 删除缓存对象
     *
     * @param keys 缓存 key 名
     * @return 受影响个数
     */
    int delete(String... keys);

    /**
     * 删除缓存对象
     *
     * @param keys 缓存 key 名
     * @return 受影响个数
     */
    default int delete(Collection<String> keys) {
        return delete(CollectionsUtil.toArray(keys, String.class));
    }

    /**
     * 删除指定命名空间下的所有缓存对象
     *
     * @param namespace 命名空间
     * @return 受影响个数
     */
    int deleteByNamespace(String namespace);

    /**
     * 删除缓存对象
     *
     * @param pattern key 表达式，如 cache:*
     * @return 受影响个数
     */
    int deleteByPattern(String pattern);

    /**
     * 获取过期时间
     *
     * @param key 缓存 key 名
     * @return 过期时间
     */
    long getExpirationTime(String key);

    /**
     * 获取存活时间
     *
     * @param key 缓存 key 名
     * @return 存活时间
     */
    long getTimeToLive(String key);

}
