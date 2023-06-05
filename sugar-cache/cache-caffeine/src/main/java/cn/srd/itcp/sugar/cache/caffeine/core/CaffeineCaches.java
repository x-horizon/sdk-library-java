package cn.srd.itcp.sugar.cache.caffeine.core;

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
public class CaffeineCaches implements CaffeineCacheTemplate {

    /**
     * combine {@link Cache}
     */
    private Cache<Object, Object> cache;

    /**
     * get multiple instance
     *
     * @return instance
     */
    public static CaffeineCaches getInstance() {
        return new CaffeineCaches(CaffeineCacheBuilder.build());
    }

    @Override
    public <T> void set(String key, T value) {
        cache.put(key, value);
    }

    @Override
    public <T> boolean setIfExists(String key, T value) {
        if (Objects.isNotNull(get(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public <T> boolean setIfAbsent(String key, T value) {
        if (Objects.isNull(get(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public Object get(String key) {
        return cache.getIfPresent(key);
    }

    @Override
    public <T> List<T> get(String... keys) {
        return get(List.of(keys));
    }

    @Override
    public <T> List<T> get(Collection<String> keys) {
        return CollectionsUtil.toList(getMap(keys));
    }

    @Override
    public <V> Map<String, V> getMap(String... keys) {
        return getMap(List.of(keys));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> Map<String, V> getMap(Collection<String> keys) {
        Map<Object, Object> result = cache.getAllPresent(keys);
        if (Objects.isEmpty(result)) {
            return new HashMap<>();
        }
        Map<String, V> output = new HashMap<>();
        result.forEach((key, value) -> {
            if (Objects.isNotNull(value) && Objects.notEquals(NullValue.class, value.getClass())) {
                output.put(key.toString(), (V) value);
            }
        });
        return output;
    }

    @Override
    public Object getAndSet(String key, Object value) {
        Object output = get(key);
        set(key, value);
        return output;
    }

    @Override
    public Object getAndDelete(String key) {
        Object output = get(key);
        delete(key);
        return output;
    }

    @Override
    public void delete(String key) {
        cache.invalidate(key);
    }

    @Override
    public int delete(String... keys) {
        delete(List.of(keys));
        return -1;
    }

    @Override
    public int delete(Collection<String> keys) {
        cache.invalidateAll(keys);
        return -1;
    }

    @Override
    public void deleteAll() {
        cache.invalidateAll();
    }

}
