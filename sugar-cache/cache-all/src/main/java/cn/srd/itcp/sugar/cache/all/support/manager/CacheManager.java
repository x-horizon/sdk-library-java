package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the cache manager
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheManager {

    /**
     * the singleton instance
     */
    private static final CacheManager INSTANCE = new CacheManager();

    /**
     * the {@link Cache#getNamespace()} mapping {@link Cache}
     */
    private final ConcurrentHashMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheManager getInstance() {
        return INSTANCE;
    }

    /**
     * get {@link Cache}
     *
     * @param namespace             see {@link Cache#getNamespace()}
     * @param cacheTypes            all cache in {@link Cache#getNamespace()}
     * @param allowNullValueInCache see {@link Cache#isAllowNullValueInCache()}
     * @return {@link Cache} instance
     */
    public Cache getCache(String namespace, List<CacheType> cacheTypes, boolean allowNullValueInCache) {
        Cache cache = cacheMap.get(namespace);
        if (Objects.isNotNull(cache)) {
            return cache;
        }

        log.debug("cache system: create multilevel cache instance, the namespace is: [{}]", namespace);

        cache = Cache.builder()
                .namespace(namespace)
                .dataManager(CacheDataManager.build(cacheTypes))
                .allowNullValueInCache(allowNullValueInCache)
                .build();

        Cache oldCache = cacheMap.putIfAbsent(namespace, cache);

        return Objects.isNull(oldCache) ? cache : oldCache;
    }

}
