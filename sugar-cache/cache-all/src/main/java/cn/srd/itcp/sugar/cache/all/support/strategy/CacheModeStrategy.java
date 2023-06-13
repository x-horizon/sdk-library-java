package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;

/**
 * the cache mode strategy
 *
 * @author wjm
 * @since 2023-06-12 20:49:21
 */
public interface CacheModeStrategy {

    /**
     * get cache value
     *
     * @param dataManager            see {@link CacheDataManager}
     * @param namespace              the cache namespace
     * @param key                    the cache key
     * @param findCacheTypeNameIndex current find cache type name index
     * @return cache value
     */
    Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex);

}


