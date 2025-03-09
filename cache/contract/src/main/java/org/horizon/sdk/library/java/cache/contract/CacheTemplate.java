package org.horizon.sdk.library.java.cache.contract;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.spring.contract.support.NullValues;
import org.springframework.cache.support.NullValue;

import java.util.*;

/**
 * Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41
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
        if (Nil.isNotNull(get(key))) {
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
        if (Nil.isNull(get(key))) {
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
        return Converts.toMapValues(getMap(keys));
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
            if (Nil.isNotNull(value)) {
                output.put(key, value);
            }
        });
        return output;
    }

    /**
     * get all cache in specified namespace.
     *
     * @param namespace the specified namespace, example: cache
     * @param <V>       cache value type
     * @return cache value
     */
    default <V> List<V> getByNamespace(String namespace) {
        return Converts.toMapValues(getMapByNamespace(namespace));
    }

    /**
     * see {@link #getByNamespace(String)}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> List<V> getByNamespace(String... namespaces) {
        return getByNamespace(List.of(namespaces));
    }

    /**
     * see {@link #getByNamespace(String)}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> List<V> getByNamespace(Collection<String> namespaces) {
        List<V> output = new ArrayList<>();
        for (String namespace : namespaces) {
            output.addAll(getByNamespace(namespace));
        }
        return output;
    }

    /**
     * see {@link #getByNamespace(String)}
     *
     * @param namespace the specified namespace, example: cache
     * @param <V>       cache value type
     * @return cache value
     */
    <V> Map<K, V> getMapByNamespace(String namespace);

    /**
     * see {@link #getByNamespace(String)}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByNamespace(String... namespaces) {
        return getMapByNamespace(List.of(namespaces));
    }

    /**
     * see {@link #getByNamespace(String)}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByNamespace(Collection<String> namespaces) {
        Map<K, V> output = new HashMap<>();
        for (String namespace : namespaces) {
            output.putAll(getMapByNamespace(namespace));
        }
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
        return NullValues.convertToNullIfNullValue(get(key));
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
        return Converts.toMapValues(getMapWithoutNullValue(keys));
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
            if (Nil.isNotNull(value)) {
                output.put(key, value);
            }
        });
        return output;
    }

    /**
     * filter {@link #getByNamespace(String)} if it is {@link NullValue}
     *
     * @param namespace the specified namespace, example: cache
     * @param <V>       cache value type
     * @return cache value
     */
    default <V> List<V> getByNamespaceWithoutNullValue(String namespace) {
        return Converts.toMapValues(getMapByNamespaceWithoutNullValue(namespace));
    }

    /**
     * filter {@link #getByNamespace(String...)} if it is {@link NullValue}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> List<V> getByNamespaceWithoutNullValue(String... namespaces) {
        return getByNamespaceWithoutNullValue(List.of(namespaces));
    }

    /**
     * filter {@link #getByNamespace(Collection)} if it is {@link NullValue}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> List<V> getByNamespaceWithoutNullValue(Collection<String> namespaces) {
        List<V> output = new ArrayList<>();
        for (String namespace : namespaces) {
            output.addAll(getByNamespaceWithoutNullValue(namespace));
        }
        return output;
    }

    /**
     * filter {@link #getByNamespace(String)} if it is {@link NullValue}
     *
     * @param namespace the specified namespace, example: cache
     * @param <V>       cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByNamespaceWithoutNullValue(String namespace) {
        return NullValues.removeNullValue(getMapByNamespace(namespace));
    }

    /**
     * filter {@link #getByNamespace(String...)} if it is {@link NullValue}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByNamespaceWithoutNullValue(String... namespaces) {
        return getMapByNamespaceWithoutNullValue(List.of(namespaces));
    }

    /**
     * filter {@link #getByNamespace(Collection)} if it is {@link NullValue}
     *
     * @param namespaces the specified namespace, example: cache1、cache2
     * @param <V>        cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByNamespaceWithoutNullValue(Collection<String> namespaces) {
        Map<K, V> output = new HashMap<>();
        for (String namespace : namespaces) {
            output.putAll(getMapByNamespaceWithoutNullValue(namespace));
        }
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
        return NullValues.convertToNullIfNullValue(getAndSet(key, value));
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
        return NullValues.convertToNullIfNullValue(getAndDelete(key));
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

    /**
     * delete all cache in specified namespace
     *
     * @param namespace the specified namespace
     * @return affected number
     */
    @CanIgnoreReturnValue
    long deleteAll(String namespace);

}