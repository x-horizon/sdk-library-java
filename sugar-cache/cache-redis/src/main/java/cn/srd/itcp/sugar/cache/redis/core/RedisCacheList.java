package cn.srd.itcp.sugar.cache.redis.core;

import cn.srd.itcp.sugar.tool.core.time.DurationWrapper;
import cn.srd.itcp.sugar.tool.exceptions.UnsupportedOperationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Map;

/**
 * Redis 缓存操作（List）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisCacheList implements RedisCacheTemplate {

    /**
     * singleton pattern
     */
    protected static final RedisCacheList INSTANCE = new RedisCacheList();

    @Override
    public Object get(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long deleteAll(String namespace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> boolean compareAndSet(String key, V expectedValue, V updateValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> Map<String, V> getMapByPattern(String pattern) {
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
    public DurationWrapper getExpirationTime(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DurationWrapper getTimeToLive(String key) {
        throw new UnsupportedOperationException();
    }

}
