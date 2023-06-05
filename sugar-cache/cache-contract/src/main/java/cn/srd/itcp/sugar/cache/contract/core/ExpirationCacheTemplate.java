package cn.srd.itcp.sugar.cache.contract.core;

import cn.srd.itcp.sugar.tool.core.time.TimeUnitHandler;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 缓存模板（支持过期时间）
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface ExpirationCacheTemplate extends CacheTemplate {

    /**
     * 设置缓存
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     */
    @Override
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
    @Override
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
    @Override
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
     * see {@link #getAndSet(String, Object)}
     *
     * @param key        缓存 key 名
     * @param value      要缓存的对象
     * @param oldClazz   旧缓存对象的类对象
     * @param expiration 过期时间，如 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     * @param <T>        要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    default <T> T getAndSet(String key, T value, Class<T> oldClazz, String expiration) {
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
