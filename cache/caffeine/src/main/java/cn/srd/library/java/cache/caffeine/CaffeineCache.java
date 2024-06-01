package cn.srd.library.java.cache.caffeine;

import cn.srd.library.java.cache.caffeine.model.properties.CacheCaffeineProperties;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * Caffeine Cache Operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 17:01
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CaffeineCache<K> implements CaffeineCacheTemplate<K> {

    /**
     * combine {@link Cache}
     */
    private Cache<Object, Object> cache;

    /**
     * get instance
     *
     * @param <K> the cache key type
     * @return instance
     */
    public static <K> CaffeineCache<K> newInstance() {
        return newInstance(CacheCaffeineProperties.getInstance());
    }

    /**
     * get instance
     *
     * @param cacheCaffeineProperties {@link CacheCaffeineProperties}
     * @param <K>                     cache key type
     * @return instance
     */
    public static <K> CaffeineCache<K> newInstance(CacheCaffeineProperties cacheCaffeineProperties) {
        return new CaffeineCache<>(CaffeineCacheBuilder.build(cacheCaffeineProperties));
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
    @SuppressWarnings("unchecked")
    public <V> Map<K, V> getMapByNamespace(String namespace) {
        return (Map<K, V>) cache.asMap();
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