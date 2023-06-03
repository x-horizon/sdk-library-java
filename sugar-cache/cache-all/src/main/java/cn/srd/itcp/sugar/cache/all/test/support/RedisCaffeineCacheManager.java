package cn.srd.itcp.sugar.cache.all.test.support;

import cn.srd.itcp.sugar.cache.all.test.enums.CacheOperation;
import cn.srd.itcp.sugar.cache.all.test.properties.CacheConfigProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.time.Duration;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

@Slf4j
@Getter
@Setter
public class RedisCaffeineCacheManager implements CacheManager {

    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

    private CacheConfigProperties cacheConfigProperties;

    private boolean dynamic;

    private Set<String> cacheNames;

    private Object serverId;

    public RedisCaffeineCacheManager(CacheConfigProperties cacheConfigProperties) {
        super();
        this.cacheConfigProperties = cacheConfigProperties;
        this.dynamic = cacheConfigProperties.isDynamic();
        this.cacheNames = cacheConfigProperties.getCacheNames();
        this.serverId = cacheConfigProperties.getServerId();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if (cache != null) {
            return cache;
        }
        if (!dynamic && !cacheNames.contains(name)) {
            return null;
        }

        cache = createCache(name);
        Cache oldCache = cacheMap.putIfAbsent(name, cache);
        log.debug("create cache instance, the cache name is : {}", name);
        return oldCache == null ? cache : oldCache;
    }

    public RedisCaffeineCache createCache(String name) {
        return new RedisCaffeineCache(name, caffeineCache(), cacheConfigProperties);
    }

    public com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeineCache() {
        return caffeineCacheBuilder().build();
    }

    public Caffeine<Object, Object> caffeineCacheBuilder() {
        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();
        doIfPresent(cacheConfigProperties.getCaffeine().getExpireAfterAccess(), cacheBuilder::expireAfterAccess);
        doIfPresent(cacheConfigProperties.getCaffeine().getExpireAfterWrite(), cacheBuilder::expireAfterWrite);
        doIfPresent(cacheConfigProperties.getCaffeine().getRefreshAfterWrite(), cacheBuilder::refreshAfterWrite);
        if (cacheConfigProperties.getCaffeine().getInitialCapacity() > 0) {
            cacheBuilder.initialCapacity(cacheConfigProperties.getCaffeine().getInitialCapacity());
        }
        if (cacheConfigProperties.getCaffeine().getMaximumSize() > 0) {
            cacheBuilder.maximumSize(cacheConfigProperties.getCaffeine().getMaximumSize());
        }
        if (cacheConfigProperties.getCaffeine().getKeyStrength() != null) {
            switch (cacheConfigProperties.getCaffeine().getKeyStrength()) {
                case WEAK -> cacheBuilder.weakKeys();
                case SOFT -> throw new UnsupportedOperationException("caffeine 不支持 key 软引用");
                default -> {
                }
            }
        }
        if (cacheConfigProperties.getCaffeine().getValueStrength() != null) {
            switch (cacheConfigProperties.getCaffeine().getValueStrength()) {
                case WEAK -> cacheBuilder.weakValues();
                case SOFT -> cacheBuilder.softValues();
                default -> {
                }
            }
        }
        return cacheBuilder;
    }

    protected static void doIfPresent(Duration duration, Consumer<Duration> consumer) {
        if (duration != null && !duration.isNegative()) {
            consumer.accept(duration);
        }
    }

    @Override
    public Collection<String> getCacheNames() {
        return this.cacheNames;
    }

    public void clearLocal(String cacheName, Object key) {
        clearLocal(cacheName, key, CacheOperation.EVICT);
    }

    @SuppressWarnings("unchecked")
    public void clearLocal(String cacheName, Object key, CacheOperation operation) {
        Cache cache = cacheMap.get(cacheName);
        if (cache == null) {
            return;
        }

        RedisCaffeineCache redisCaffeineCache = (RedisCaffeineCache) cache;
        if (CacheOperation.EVICT_BATCH.equals(operation)) {
            redisCaffeineCache.clearLocalBatch((Iterable<Object>) key);
        } else {
            redisCaffeineCache.clearLocal(key);
        }
    }

}
