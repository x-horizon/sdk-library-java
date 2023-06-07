package cn.srd.itcp.sugar.cache.all.support;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Policy;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.Getter;
import lombok.NonNull;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Getter
public class MultilevelCache<K, V> extends AbstractValueAdaptingCache implements Cache<K, V> {

    private String namespace;

    private List<CacheType> cacheTypes;

    public MultilevelCache(String namespace, List<CacheType> cacheTypes, boolean allowNullValues) {
        super(allowNullValues);
        this.cacheTypes = cacheTypes;
    }

    @Override
    protected Object lookup(@NonNull Object key) {
        // Object value;
        // for (CacheType cacheType : cacheTypes) {
        //     value = cacheType.getTemplate().get(key);
        //     if (Objects.isNull(value)) {
        //         continue;
        //     }
        // }
        // Object value = getCaffeineValue(key);
        // if (value != null) {
        //     log.debug("get cache from caffeine, the key is : {}", key);
        //     return value;
        // }
        //
        // value = getRedisValue(key);
        //
        // if (value != null) {
        //     log.debug("get cache from redis and put in caffeine, the key is : {}", key);
        //     setCaffeineValue(key, value);
        // }
        // return value;
        return null;
    }

    @Override
    public String getName() {
        return this.namespace;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public @Nullable V getIfPresent(K key) {
        return null;
    }

    @Override
    public @PolyNull V get(K key, Function<? super K, ? extends @PolyNull V> mappingFunction) {
        return null;
    }

    @Override
    public Map<K, V> getAllPresent(Iterable<? extends K> keys) {
        return null;
    }

    @Override
    public Map<K, V> getAll(Iterable<? extends K> keys, Function<? super Set<? extends K>, ? extends Map<? extends K, ? extends V>> mappingFunction) {
        return null;
    }

    @Override
    public ConcurrentMap<K, V> asMap() {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void invalidate(K key) {

    }

    @Override
    public void invalidateAll(Iterable<? extends K> keys) {

    }

    @Override
    public void invalidateAll() {

    }

    @Override
    public void cleanUp() {

    }

    @Override
    public void clear() {

    }

    @Override
    public Policy<K, V> policy() {
        return null;
    }

    @Override
    public @NonNegative long estimatedSize() {
        return 0;
    }

    @Override
    public CacheStats stats() {
        return null;
    }

}
