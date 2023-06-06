package cn.srd.itcp.sugar.cache.map.core;

import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map Cache Operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MapCaches<K> implements MapCacheTemplate<K> {

    /**
     * default initial capacity
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * combine {@link ConcurrentHashMap}
     */
    private ConcurrentHashMap<K, Object> cache;

    /**
     * get instance
     *
     * @param <K> cache key type
     * @return instance
     */
    public static <K> MapCaches<K> newInstance() {
        return newInstance(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * get instance
     *
     * @param initialCapacity initial capacity
     * @param <K>             cache key type
     * @return instance
     */
    public static <K> MapCaches<K> newInstance(int initialCapacity) {
        return MapCaches.<K>builder().cache(new ConcurrentHashMap<>(initialCapacity)).build();
    }

    @Override
    public <V> void set(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public <V> boolean setIfExists(K key, V value) {
        if (Objects.isNotNull(get(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public <V> boolean setIfAbsent(K key, V value) {
        if (Objects.isNull(get(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public Object get(K key) {
        return convertWithNullValue(cache.get(key));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> List<V> get(K... keys) {
        return get(List.of(keys));
    }

    @Override
    public <V> List<V> get(Collection<K> keys) {
        return CollectionsUtil.toList(getMap(keys));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> Map<K, V> getMap(K... keys) {
        return getMap(List.of(keys));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> Map<K, V> getMap(Collection<K> keys) {
        Map<K, V> output = new HashMap<>();
        keys.forEach(key -> {
            V value = (V) get(key);
            if (Objects.isNotNull(value)) {
                output.put(key, value);
            }
        });
        return output;
    }

    @Override
    public Object getAndSet(K key, Object value) {
        Object output = get(key);
        set(key, value);
        return output;
    }

    @Override
    public Object getAndDelete(K key) {
        Object output = get(key);
        delete(key);
        return output;
    }

    @Override
    public void delete(K key) {
        cache.remove(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int delete(K... keys) {
        return delete(List.of(keys));
    }

    @Override
    public int delete(Collection<K> keys) {
        keys.forEach(key -> delete(keys));
        return -1;
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }

}
