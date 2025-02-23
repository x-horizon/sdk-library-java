package org.horizon.library.java.cache.redis;

import org.horizon.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.library.java.tool.lang.time.DurationWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Map;

/**
 * Redis 缓存操作（Set）
 *
 * @author wjm
 * @since 2023-01-12 10:37
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisCacheSet implements RedisCacheTemplate {

    /**
     * singleton pattern
     */
    public static final RedisCacheSet INSTANCE = new RedisCacheSet();

    @Override
    public Object get(String key) {
        throw new UnsupportedException();
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(String namespace) {
        throw new UnsupportedException();
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedException();
    }

    @Override
    public long deleteAll(String namespace) {
        throw new UnsupportedException();
    }

    @Override
    public <V> boolean compareAndSet(String key, V expectedValue, V updateValue) {
        throw new UnsupportedException();
    }

    @Override
    public <V> Map<String, V> getMapByPattern(String pattern) {
        throw new UnsupportedException();
    }

    @Override
    public long deleteByNamespace(String namespace) {
        throw new UnsupportedException();
    }

    @Override
    public long deleteByPattern(String pattern) {
        throw new UnsupportedException();
    }

    @Override
    public <V> void set(String key, V value, Duration expiration) {
        throw new UnsupportedException();
    }

    @Override
    public <V> boolean setIfExists(String key, V value, Duration expiration) {
        throw new UnsupportedException();
    }

    @Override
    public <V> boolean setIfAbsent(String key, V value, Duration expiration) {
        throw new UnsupportedException();
    }

    @Override
    public DurationWrapper getExpirationTime(String key) {
        throw new UnsupportedException();
    }

    @Override
    public DurationWrapper getTimeToLive(String key) {
        throw new UnsupportedException();
    }

}