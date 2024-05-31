package cn.srd.library.java.cache.all.manager;

import cn.srd.library.java.cache.all.model.enums.CacheType;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.object.Nil;
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
 * @since 2023-06-07 16:48
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
     * @param namespace       the cache namespace, one namespace represents one {@link Cache} instance;
     * @param cacheTypes      see {@link CacheType}
     * @param allowEmptyValue allow or not to set a {@link NullValue} in cache
     * @return {@link Cache} instance
     */
    public Cache getCache(String namespace, List<CacheType> cacheTypes, boolean allowEmptyValue) {
        Cache cache = cacheMap.get(namespace);
        if (Nil.isNotNull(cache)) {
            return cache;
        }

        log.debug("{}create cache instance, the namespace is: [{}]", ModuleView.CACHE_SYSTEM, namespace);

        cache = Cache.builder()
                .namespace(namespace)
                .dataManager(CacheDataManager.build(cacheTypes))
                .allowEmptyValue(allowEmptyValue)
                .build();

        // keep thread safe: other thread may create a cache instance and put in map earlier, so use putIfAbsent to avoid this situation.
        Cache oldCache = cacheMap.putIfAbsent(namespace, cache);
        return Nil.isNull(oldCache) ? cache : oldCache;
    }

}