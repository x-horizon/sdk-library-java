package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.context.caffeine.config.properties.CacheCaffeineProperties;
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
public class CacheCaffeines<K> implements CacheCaffeineTemplate<K> {

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
    public static <K> CacheCaffeines<K> newInstance() {
        return newInstance(CacheCaffeineProperties.getInstance());
    }

    /**
     * get instance
     *
     * @param cacheCaffeineProperties {@link CacheCaffeineProperties}
     * @param <K>                     cache key type
     * @return instance
     */
    public static <K> CacheCaffeines<K> newInstance(CacheCaffeineProperties cacheCaffeineProperties) {
        return new CacheCaffeines<>(CacheCaffeineBuilder.build(cacheCaffeineProperties));
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
