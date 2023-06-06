package cn.srd.itcp.sugar.cache.contract.core;

import cn.srd.itcp.sugar.tool.core.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.cache.support.NullValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface CacheTemplate<K> {

    /**
     * set cache
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     */
    <V> void set(K key, V value);

    /**
     * cover cache if already exist and do nothing if not exist
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     * @return true if successful, or false if element wasn't set
     */
    @CanIgnoreReturnValue
    <V> boolean setIfExists(K key, V value);

    /**
     * set cache if not exist and do nothing if already exist
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     * @return true if successful, or false if element wasn't set
     */
    @CanIgnoreReturnValue
    <V> boolean setIfAbsent(K key, V value);

    /**
     * get cache
     *
     * @param key cache key
     * @return cache value
     */
    Object get(K key);

    /**
     * see {@link #get(Object)}
     *
     * @param key   cache key
     * @param clazz 缓存对象的类对象
     * @param <V>   cache value type
     * @return cache value
     */
    default <V> V get(K key, Class<V> clazz) {
        return clazz.cast(get(key));
    }

    /**
     * get caches
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache values
     */
    @SuppressWarnings("unchecked")
    <V> List<V> get(K... keys);

    /**
     * see {@link #get(Object[])}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache values
     */
    <V> List<V> get(Collection<K> keys);

    /**
     * see {@link #get(Object[])}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache key mapping cache value map
     */
    @SuppressWarnings("unchecked")
    <V> Map<K, V> getMap(K... keys);

    /**
     * see {@link #get(Object[])}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache key mapping cache value map
     */
    <V> Map<K, V> getMap(Collection<K> keys);

    /**
     * get old cache and set cache
     *
     * @param key   cache key
     * @param value cache value
     * @return old cache
     */
    Object getAndSet(K key, Object value);

    /**
     * see {@link #getAndSet(Object, Object)}
     *
     * @param key      cache key
     * @param value    cache value
     * @param oldClazz old cache value class
     * @param <V>      cache value type
     * @return old cache
     */
    default <V> V getAndSet(K key, V value, Class<V> oldClazz) {
        return oldClazz.cast(getAndSet(key, value));
    }

    /**
     * get old cache and delete cache
     *
     * @param key cache key
     * @return old cache
     */
    Object getAndDelete(K key);

    /**
     * see {@link #getAndDelete(Object)}
     *
     * @param key   cache key
     * @param clazz cache value class
     * @param <V>   cache value type
     * @return old cache
     */
    default <V> V getAndDelete(K key, Class<V> clazz) {
        return clazz.cast(getAndDelete(key));
    }

    /**
     * delete cache
     *
     * @param key cache key
     */
    void delete(K key);

    /**
     * see {@link #delete(Object)}
     *
     * @param keys cache key
     * @return affected number
     */
    @CanIgnoreReturnValue
    @SuppressWarnings("unchecked")
    int delete(K... keys);

    /**
     * see {@link #delete(Object[])}
     *
     * @param keys cache key
     * @return affected number
     */
    @CanIgnoreReturnValue
    int delete(Collection<K> keys);

    /**
     * is it {@link NullValue}
     *
     * @param input checked object
     * @return is it {@link NullValue}
     */
    default boolean isNullValue(Object input) {
        return Objects.isNotNull(input) && Objects.equals(NullValue.class, input.getClass());
    }

    /**
     * is not {@link NullValue}
     *
     * @param input checked object
     * @return is not {@link NullValue}
     */
    default boolean isNotNullValue(Object input) {
        return !isNullValue(input);
    }

    /**
     * convert with {@link NullValue}
     *
     * @param input checked object
     * @param <T>   checked object type
     * @return null if it is {@link NullValue}, or do not convert
     */
    default <T> T convertWithNullValue(T input) {
        return isNullValue(input) ? null : input;
    }

}
