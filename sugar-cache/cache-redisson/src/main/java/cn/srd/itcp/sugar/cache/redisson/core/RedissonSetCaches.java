package cn.srd.itcp.sugar.cache.redisson.core;

import cn.srd.itcp.sugar.tool.core.time.DurationWrapper;
import cn.srd.itcp.sugar.tool.exceptions.UnsupportedOperationException;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Redisson 缓存操作（Set）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
public class RedissonSetCaches implements RedissonCacheTemplate {

    /**
     * protected block constructor
     */
    protected RedissonSetCaches() {
    }

    /**
     * singleton pattern
     */
    public static final RedissonSetCaches INSTANCE = new RedissonSetCaches();

    @Override
    public <V> void set(String key, V value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> boolean setIfExists(String key, V value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> boolean setIfAbsent(String key, V value, Duration expiration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> boolean compareAndSet(String key, V expectedValue, V updateValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> List<V> get(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> List<V> get(Collection<String> keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> Map<String, V> getMap(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> Map<String, V> getMap(Collection<String> keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> List<V> getByNamespace(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> List<V> getByPattern(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long delete(String... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long delete(Collection<String> keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long deleteByNamespace(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long deleteByPattern(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DurationWrapper getExpirationTime(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DurationWrapper getTimeToLive(String key) {
        throw new UnsupportedOperationException();
    }

}
