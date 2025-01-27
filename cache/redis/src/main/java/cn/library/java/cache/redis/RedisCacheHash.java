package cn.library.java.cache.redis;

import cn.library.java.contract.model.throwable.UnsupportedException;
import cn.library.java.tool.lang.time.DurationWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Map;

/**
 * Redis 缓存操作（哈希）
 *
 * @author wjm
 * @since 2023-01-12 10:37
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisCacheHash implements RedisCacheTemplate {

    /**
     * singleton pattern
     */
    protected static final RedisCacheHash INSTANCE = new RedisCacheHash();

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