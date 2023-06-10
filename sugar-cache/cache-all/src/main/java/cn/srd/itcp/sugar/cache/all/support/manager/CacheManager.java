package cn.srd.itcp.sugar.cache.all.support.manager;

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

    private final ConcurrentHashMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

    public static CacheManager getInstance() {
        return INSTANCE;
    }

    public Cache getCache(String namespace, List<CacheType> cacheTypes, boolean enablePreventCachePenetrate) {
        Cache cache = cacheMap.get(namespace);
        if (Objects.isNotNull(cache)) {
            return cache;
        }

        log.debug("cache system: create multilevel cache instance, the namespace is: [{}]", namespace);

        cache = Cache.builder()
                .namespace(namespace)
                .dataManager(CacheDataManager.build(cacheTypes))
                .enablePreventCachePenetrate(enablePreventCachePenetrate)
                .build();

        Cache oldCache = cacheMap.putIfAbsent(namespace, cache);

        return Objects.isNull(oldCache) ? cache : oldCache;
    }

}
