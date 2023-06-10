package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.cache.caffeine.config.properties.CaffeineCacheProperties;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * Caffeine Cache Operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CaffeineCaches<K> implements CaffeineCacheTemplate<K> {

    /**
     * combine {@link Cache}
     */
    private Cache<Object, Object> cache;

    /**
     * get instance
     *
     * @param <K> cache key type
     * @return instance
     */
    public static <K> CaffeineCaches<K> newInstance() {
        return newInstance(CaffeineCacheProperties.getInstance());
    }

    /**
     * get instance
     *
     * @param caffeineCacheProperties {@link CaffeineCacheProperties}
     * @param <K>                     cache key type
     * @return instance
     */
    public static <K> CaffeineCaches<K> newInstance(CaffeineCacheProperties caffeineCacheProperties) {
        return new CaffeineCaches<>(CaffeineCacheBuilder.build(caffeineCacheProperties));
    }

    @Override
    public <V> void set(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public Object get(K key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void delete(K key) {
        cache.invalidate(key);
    }

    @Override
    public long delete(Collection<K> keys) {
        cache.invalidateAll(keys);
        // not implement affected number, ignore the return value
        return -1;
    }

    @Override
    public long deleteAll(String namespace) {
        return deleteAll();
    }

    @Override
    public long deleteAll() {
        cache.invalidateAll();
        // not implement affected number, ignore the return value
        return -1;
    }

}
