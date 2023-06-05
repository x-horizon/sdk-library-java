package cn.srd.itcp.sugar.cache.redisson.common.core;

import cn.srd.itcp.sugar.tool.exceptions.UnsupportedOperationException;

import java.time.Duration;
import java.util.List;

/**
 * Redisson 缓存操作（Sorted Set）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonSortedSetCaches implements RedissonCacheTemplate {

    /**
     * protected block constructor
     */
    protected RedissonSortedSetCaches() {
    }

    /**
     * singleton pattern
     */
    public static final RedissonSortedSetCaches INSTANCE = new RedissonSortedSetCaches();

    @Override
    public <T> void set(String key, T value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean setIfExists(String key, T value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean setIfAbsent(String key, T value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean compareAndSet(String key, T expectedValue, T updateValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> get(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> getByPattern(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSet(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getAndSet(String key, T value, Class<T> oldClazz, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getAndDelete(String key, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int deleteByNamespace(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int deleteByPattern(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getExpirationTime(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getTimeToLive(String key) {
        throw new UnsupportedOperationException();
    }

}
