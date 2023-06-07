package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.tool.core.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Slf4j
public class MultilevelCacheManager implements CacheManager {

    private List<CacheType> cacheTypes;

    private boolean preventCachePenetrate;

    private Map<String, Cache> cacheMap = new ConcurrentHashMap<>();

    public MultilevelCacheManager(List<CacheType> cacheTypes, boolean preventCachePenetrate) {
        this.cacheTypes = cacheTypes;
        this.preventCachePenetrate = preventCachePenetrate;
    }

    @Override
    public Cache getCache(@NonNull String namespace) {
        Cache cache = cacheMap.get(namespace);
        if (Objects.isNotNull(cache)) {
            return cache;
        }

        log.debug("Multilevel Cache System: create multilevel cache instance, the namespace is: [{}]", namespace);

        cache = new MultilevelCache<>(namespace, cacheTypes, preventCachePenetrate);
        Cache oldCache = cacheMap.putIfAbsent(namespace, cache);
        return Objects.isNull(oldCache) ? cache : oldCache;
    }

    @NotNull
    @Override
    public Collection<String> getCacheNames() {
        return new HashSet<>();
    }

}
