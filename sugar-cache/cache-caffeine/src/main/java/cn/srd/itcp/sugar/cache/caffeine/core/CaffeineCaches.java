package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.tool.core.Objects;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Redisson 缓存操作
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CaffeineCaches implements CaffeineCacheTemplate {

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
        if (Objects.isNotNull(cache.getIfPresent(key))) {
            set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public <T> boolean setIfAbsent(String key, T value) {
        if (Objects.isNull(cache.getIfPresent(key))) {
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
        return null;
    }

    @Override
    public <T> List<T> getByPattern(String pattern) {
        return null;
    }

    @Override
    public Object getAndSet(String key, Object value) {
        return null;
    }

    @Override
    public <T> T getAndDelete(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public int delete(String... keys) {
        return 0;
    }

    @Override
    public int deleteByNamespace(String namespace) {
        return 0;
    }

    @Override
    public int deleteByPattern(String pattern) {
        return 0;
    }

}
