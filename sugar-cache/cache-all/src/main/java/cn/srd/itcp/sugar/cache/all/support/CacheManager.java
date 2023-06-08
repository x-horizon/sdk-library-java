package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Slf4j
public class CacheManager {

    private CacheManager() {
    }

    private static final CacheManager INSTANCE = new CacheManager();

    private static final ConcurrentHashMap<String, Cache> CACHE_MAP = new ConcurrentHashMap<>();

    public static CacheManager getInstance() {
        return INSTANCE;
    }

    public Cache getCache(String namespace, List<CacheType> cacheTypes, boolean enablePreventCachePenetrate) {
        Cache cache = CACHE_MAP.get(namespace);
        if (Objects.isNotNull(cache)) {
            return cache;
        }

        log.debug("cache system: create multilevel cache instance, the namespace is: [{}]", namespace);

        cacheTypes.forEach(CacheType::init);
        cache = Cache.builder()
                .namespace(namespace)
                .cacheTypes(cacheTypes)
                .enablePreventCachePenetrate(enablePreventCachePenetrate)
                .build();

        Cache oldCache = CACHE_MAP.putIfAbsent(namespace, cache);

        return Objects.isNull(oldCache) ? cache : oldCache;
    }

}
