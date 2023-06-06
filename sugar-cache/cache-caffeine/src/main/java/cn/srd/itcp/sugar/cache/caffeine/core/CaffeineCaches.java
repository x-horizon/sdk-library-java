package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.cache.caffeine.config.properties.CaffeineCacheProperties;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.cache.support.NullValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redisson 缓存操作
 *
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
     * @return instance
     */
    public static <K> CaffeineCaches<K> newInstance() {
        return newInstance(CaffeineCacheProperties.getInstance());
    }

    /**
     * get instance
     *
     * @param caffeineCacheProperties {@link CaffeineCacheProperties}
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
        return convertWithNullValue(cache.getIfPresent(key));
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
        Map<Object, Object> result = cache.getAllPresent(keys);
        if (Objects.isEmpty(result)) {
            return new HashMap<>();
        }
        Map<K, V> output = new HashMap<>();
        result.forEach((key, value) -> {
            if (Objects.isNotNull(value) && Objects.notEquals(NullValue.class, value.getClass())) {
                output.put((K) key, (V) value);
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
        cache.invalidate(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int delete(K... keys) {
        delete(List.of(keys));
        return -1;
    }

    @Override
    public int delete(Collection<K> keys) {
        cache.invalidateAll(keys);
        return -1;
    }

    @Override
    public void deleteAll() {
        cache.invalidateAll();
    }

}
