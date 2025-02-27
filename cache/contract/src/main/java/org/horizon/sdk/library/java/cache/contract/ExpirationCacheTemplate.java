package org.horizon.sdk.library.java.cache.contract;

import org.horizon.sdk.library.java.tool.lang.time.DurationWrapper;
import org.horizon.sdk.library.java.tool.lang.time.TimeUnitHandler;
import org.horizon.sdk.library.java.tool.lang.time.Times;
import org.horizon.sdk.library.java.tool.spring.contract.support.NullValues;
import org.springframework.cache.support.NullValue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Cache Template - Support expiration
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41
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
     * @param expiration expire time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     */
    default <V> void set(K key, V value, String expiration) {
        set(key, value, Times.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #set(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expire time
     * @param timeUnit   expire time unit
     * @param <V>        cache value type
     */
    default <V> void set(K key, V value, long expiration, TimeUnit timeUnit) {
        set(key, value, Times.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #set(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expire time
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
     * @param expiration expire time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfExists(K key, V value, String expiration) {
        return setIfExists(key, value, Times.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #setIfExists(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expire time
     * @param timeUnit   expire time unit
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfExists(K key, V value, long expiration, TimeUnit timeUnit) {
        return setIfExists(key, value, Times.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #setIfExists(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expire time
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
     * @param expiration expire time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfAbsent(K key, V value, String expiration) {
        return setIfAbsent(key, value, Times.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #setIfAbsent(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expire time
     * @param timeUnit   expire time unit
     * @param <V>        cache value type
     * @return true if successful, or false if element wasn't set
     */
    default <V> boolean setIfAbsent(K key, V value, long expiration, TimeUnit timeUnit) {
        return setIfAbsent(key, value, Times.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #setIfAbsent(Object, Object)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiration expire time
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
     * @param expiration expire time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSet(K key, V value, Class<V> oldClazz, String expiration) {
        return getAndSet(key, value, oldClazz, Times.wrapper(expiration).toMillisecond());
    }

    /**
     * see {@link #getAndSet(Object, Object, Class)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expire time
     * @param timeUnit   expire time unit
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSet(K key, V value, Class<V> oldClazz, long expiration, TimeUnit timeUnit) {
        return getAndSet(key, value, oldClazz, Times.wrapper(expiration, timeUnit).toMillisecond());
    }

    /**
     * see {@link #getAndSet(Object, Object, Class)}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expire time
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSet(K key, V value, Class<V> oldClazz, Duration expiration) {
        V output = get(key, oldClazz);
        set(key, value, expiration);
        return output;
    }

    /**
     * convert {@link #getAndSet(Object, Object, Class, String)} to null if it is {@link NullValue}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expire time，example: "2s"、"200ms"... , see {@link TimeUnitHandler}
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSetWithoutNullValue(K key, V value, Class<V> oldClazz, String expiration) {
        return NullValues.convertToNullIfNullValue(getAndSet(key, value, oldClazz, expiration));
    }

    /**
     * convert {@link #getAndSet(Object, Object, Class, long, TimeUnit)} to null if it is {@link NullValue}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expire time
     * @param timeUnit   expire time unit
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSetWithoutNullValue(K key, V value, Class<V> oldClazz, long expiration, TimeUnit timeUnit) {
        return NullValues.convertToNullIfNullValue(getAndSet(key, value, oldClazz, expiration, timeUnit));
    }

    /**
     * convert {@link #getAndSet(Object, Object, Class, Duration)} to null if it is {@link NullValue}
     *
     * @param key        cache key
     * @param value      cache value
     * @param oldClazz   old cache value class
     * @param expiration expire time
     * @param <V>        cache value type
     * @return old cache
     */
    default <V> V getAndSetWithoutNullValue(K key, V value, Class<V> oldClazz, Duration expiration) {
        return NullValues.convertToNullIfNullValue(getAndSet(key, value, oldClazz, expiration));
    }

    /**
     * get specified key expire time
     *
     * @param key cache key
     * @return expire time
     */
    DurationWrapper getExpirationTime(K key);

    /**
     * get specified key time to live
     *
     * @param key cache key
     * @return time to live
     */
    DurationWrapper getTimeToLive(K key);

}