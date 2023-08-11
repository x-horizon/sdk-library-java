package cn.srd.itcp.sugar.cache.redis.core;

import cn.srd.sugar.tool.lang.core.time.DurationWrapper;
import cn.srd.sugar.contract.throwable.core.UnsupportedOperationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Map;

/**
 * Redis 缓存操作（Sorted Set）
 *
 * @author wjm
 * @since 2023-01-12 10:37:12
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisCacheSortedSet implements RedisCacheTemplate {

    /**
     * singleton pattern
     */
    public static final RedisCacheSortedSet INSTANCE = new RedisCacheSortedSet();

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
