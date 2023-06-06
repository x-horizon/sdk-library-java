package cn.srd.itcp.sugar.cache.contract.core;

import cn.srd.itcp.sugar.tool.core.time.TimeUnitHandler;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Cache Template - Support expiration
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface ExpirationCacheTemplate<K> extends CacheTemplate<K> {

    /**
     * set cache
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     */
    @Override
    default <V> void set(K key, V value) {
        set(key, value, Duration.ZERO);
    }

    /**
     * see {@link #set(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration the expiration time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     */
    default <V> void set(K key, V value, String expiration) {
        set(key, value, TimeUtil.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #set(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expiration time
     * @param timeUnit   expiration time unit
     * @param <V>        cache value type
     */
    default <V> void set(K key, V value, long expiration, TimeUnit timeUnit) {
        set(key, value, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #set(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expiration time
     * @param <V>        cache value type
     */
    <V> void set(K key, V value, Duration expiration);

    /**
     * cover cache if already exist and do nothing if not exist
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     * @return true if successful, or false if element wasn't set
     */
    @Override
    default <V> boolean setIfExists(K key, V value) {
        return setIfExists(key, value, Duration.ZERO);
    }

    /**
     * see {@link #setIfExists(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration the expiration time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfExists(K key, V value, String expiration) {
        return setIfExists(key, value, TimeUtil.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #setIfExists(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expiration time
     * @param timeUnit   expiration time unit
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfExists(K key, V value, long expiration, TimeUnit timeUnit) {
        return setIfExists(key, value, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #setIfExists(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expiration time
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    <V> boolean setIfExists(K key, V value, Duration expiration);

    /**
     * set cache if not exist and do nothing if already exist
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     * @return true if successful, or false if element wasn't set
     */
    @Override
    default <V> boolean setIfAbsent(K key, V value) {
        return setIfAbsent(key, value, Duration.ZERO);
    }

    /**
     * see {@link #setIfAbsent(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration the expiration time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfAbsent(K key, V value, String expiration) {
        return setIfAbsent(key, value, TimeUtil.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #setIfAbsent(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expiration time
     * @param timeUnit   expiration time unit
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfAbsent(K key, V value, long expiration, TimeUnit timeUnit) {
        return setIfAbsent(key, value, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #setIfAbsent(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expiration time
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    <V> boolean setIfAbsent(K key, V value, Duration expiration);

    /**
     * see {@link #getAndSet(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration the expiration time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSet(K key, V value, Class<V> oldClazz, String expiration) {
        return getAndSet(key, value, oldClazz, TimeUtil.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #getAndSet(Object, Object, Class)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expiration time
     * @param timeUnit   expiration time unit
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSet(K key, V value, Class<V> oldClazz, long expiration, TimeUnit timeUnit) {
        return getAndSet(key, value, oldClazz, TimeUtil.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #getAndSet(Object, Object, Class)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expiration time
     * @param <V>        cache value type
     * @return old cache
     */
    <V> V getAndSet(K key, V value, Class<V> oldClazz, Duration expiration);

    /**
     * get specified key expiration time
     *
     * @param key cache key
     * @return expiration time
     */
    long getExpirationTime(K key);

    /**
     * get specified key time to live
     *
     * @param key cache key
     * @return time to live
     */
    long getTimeToLive(K key);

}
