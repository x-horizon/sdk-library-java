package cn.srd.itcp.sugar.cache.contract.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.cache.support.NullValue;

import java.util.Collection;
import java.util.HashMap;
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
     * provide an extension point to modify cache key
     *
     * @param key          cache key
     * @param extensionKey extension key
     * @return cache key
     */
    default K resolveKey(K key, K extensionKey) {
        return key;
    }

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
    default <V> boolean setIfExists(K key, V value) {
        if (Objects.isNotNull(get(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

    /**
     * set cache if not exist and do nothing if already exist
     *
     * @param key   cache key
     * @param value cache value
     * @param <V>   cache value type
     * @return true if successful, or false if element wasn't set
     */
    @CanIgnoreReturnValue
    default <V> boolean setIfAbsent(K key, V value) {
        if (Objects.isNull(get(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

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
     * @param clazz cache value class
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
    default <V> List<V> get(K... keys) {
        return get(List.of(keys));
    }

    /**
     * see {@link #get(Object[])}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache values
     */
    default <V> List<V> get(Collection<K> keys) {
        return CollectionsUtil.toList(getMap(keys));
    }

    /**
     * see {@link #get(Object[])}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache key mapping cache value map
     */
    @SuppressWarnings("unchecked")
    default <V> Map<K, V> getMap(K... keys) {
        return getMap(List.of(keys));
    }

    /**
     * see {@link #get(Object[])}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache key mapping cache value map
     */
    @SuppressWarnings("unchecked")
    default <V> Map<K, V> getMap(Collection<K> keys) {
        Map<K, V> output = new HashMap<>();
        keys.forEach(key -> {
            V value = (V) get(key);
            if (Objects.isNotNull(value)) {
                output.put(key, value);
            }
        });
        return output;
    }

    /**
     * get old cache and set cache
     *
     * @param key   cache key
     * @param value cache value
     * @return old cache
     */
    default Object getAndSet(K key, Object value) {
        Object output = get(key);
        set(key, value);
        return output;
    }

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
    default Object getAndDelete(K key) {
        Object output = get(key);
        delete(key);
        return output;
    }

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
     * convert {@link #get(Object)} to null if it is {@link NullValue}
     *
     * @param key cache key
     * @return cache value
     */
    default Object getWithoutNullValue(K key) {
        return NullValueUtil.convertToNullIfNullValue(get(key));
    }

    /**
     * convert {@link #get(Object, Class)} to null if it is {@link NullValue}
     *
     * @param key   cache key
     * @param clazz cache value class
     * @param <V>   cache value type
     * @return cache value
     */
    default <V> V getWithoutNullValue(K key, Class<V> clazz) {
        return clazz.cast(getWithoutNullValue(key));
    }

    /**
     * filter {@link #get(Object[])} if it is {@link NullValue}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache values
     */
    @SuppressWarnings("unchecked")
    default <V> List<V> getWithoutNullValue(K... keys) {
        return getWithoutNullValue(List.of(keys));
    }

    /**
     * filter {@link #get(Collection)} if it is {@link NullValue}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache values
     */
    default <V> List<V> getWithoutNullValue(Collection<K> keys) {
        return CollectionsUtil.toList(getMapWithoutNullValue(keys));
    }

    /**
     * filter {@link #getMap(Object[])} if it is {@link NullValue}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache key mapping cache value map
     */
    @SuppressWarnings("unchecked")
    default <V> Map<K, V> getMapWithoutNullValue(K... keys) {
        return getMapWithoutNullValue(List.of(keys));
    }

    /**
     * filter {@link #getMap(Collection)} if it is {@link NullValue}
     *
     * @param keys cache key
     * @param <V>  cache value type
     * @return cache key mapping cache value map
     */
    @SuppressWarnings("unchecked")
    default <V> Map<K, V> getMapWithoutNullValue(Collection<K> keys) {
        Map<K, V> output = new HashMap<>();
        keys.forEach(key -> {
            V value = (V) getWithoutNullValue(key);
            if (Objects.isNotNull(value)) {
                output.put(key, value);
            }
        });
        return output;
    }

    /**
     * convert {@link #getAndSet(Object, Object)} to null if it is {@link NullValue}
     *
     * @param key   cache key
     * @param value cache value
     * @return old cache
     */
    default Object getAndSetWithoutNullValue(K key, Object value) {
        return NullValueUtil.convertToNullIfNullValue(getAndSet(key, value));
    }

    /**
     * convert {@link #getAndSet(Object, Object, Class)} to null if it is {@link NullValue}
     *
     * @param key      cache key
     * @param value    cache value
     * @param oldClazz old cache value class
     * @param <V>      cache value type
     * @return old cache
     */
    default <V> V getAndSetWithoutNullValue(K key, V value, Class<V> oldClazz) {
        return oldClazz.cast(getAndSetWithoutNullValue(key, value));
    }

    /**
     * convert {@link #getAndDelete(Object)} to null if it is {@link NullValue}
     *
     * @param key cache key
     * @return old cache
     */
    default Object getAndDeleteWithoutNullValue(K key) {
        return NullValueUtil.convertToNullIfNullValue(getAndDelete(key));
    }

    /**
     * convert {@link #getAndDelete(Object, Class)} to null if it is {@link NullValue}
     *
     * @param key   cache key
     * @param clazz cache value class
     * @param <V>   cache value type
     * @return old cache
     */
    default <V> V getAndDeleteWithoutNullValue(K key, Class<V> clazz) {
        return clazz.cast(getAndDeleteWithoutNullValue(key));
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
    default long delete(K... keys) {
        return delete(List.of(keys));
    }

    /**
     * see {@link #delete(Object[])}
     *
     * @param keys cache key
     * @return affected number
     */
    @CanIgnoreReturnValue
    default long delete(Collection<K> keys) {
        keys.forEach(key -> delete(keys));
        // by default, not implement affected number, ignore the return value
        return -1;
    }

}
