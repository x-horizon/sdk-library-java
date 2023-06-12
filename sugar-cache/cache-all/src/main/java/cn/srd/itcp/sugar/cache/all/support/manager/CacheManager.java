package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.NullValue;

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
     * @param namespace             the cache namespace, one namespace represents one {@link Cache} instance;
     * @param cacheTypes            see {@link CacheType.CacheModule}
     * @param allowNullValueInCache allow or not to set a {@link NullValue} in cache
     * @return {@link Cache} instance
     */
    public Cache getCache(String namespace, List<CacheType.CacheModule> cacheTypes, boolean allowNullValueInCache) {
        Cache cache = cacheMap.get(namespace);
        if (Objects.isNotNull(cache)) {
            return cache;
        }

        log.debug("cache system: create cache instance, the namespace is: [{}]", namespace);

        cache = Cache.builder()
                .namespace(namespace)
                .dataManager(CacheDataManager.build(cacheTypes))
                .allowNullValueInCache(allowNullValueInCache)
                .build();

        // keep thread safe: other thread may create a cache instance and put in map earlier, so use putIfAbsent to avoid this situation.
        Cache oldCache = cacheMap.putIfAbsent(namespace, cache);
        return Objects.isNull(oldCache) ? cache : oldCache;
    }

}
