package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;

/**
 * the cache mode strategy
 *
 * @author wjm
 * @since 2023-06-12 20:49:21
 */
public interface CacheModeStrategy {

    Object getAndSet(CacheDataManager dataManager, String key);

}


