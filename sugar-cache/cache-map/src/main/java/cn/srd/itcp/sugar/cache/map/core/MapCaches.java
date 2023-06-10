package cn.srd.itcp.sugar.cache.map.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Map Cache Operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
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
    public Object get(K key) {
        return cache.get(key);
    }

    @Override
    public void delete(K key) {
        cache.remove(key);
    }

    @Override
    public long deleteAll(String namespace) {
        return deleteAll();
    }

    @Override
    public long deleteAll() {
        cache.clear();
        // not implement affected number, ignore the return value
        return -1;
    }
}
