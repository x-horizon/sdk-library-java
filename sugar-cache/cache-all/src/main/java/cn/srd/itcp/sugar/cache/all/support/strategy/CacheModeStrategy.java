package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheMode;

/**
 * the cache mode strategy
 *
 * @author wjm
 * @since 2023-06-19 21:26:47
 */
public interface CacheModeStrategy {

    /**
     * the default cache mode
     */
    CacheMode DEFAULT_CACHE_MODE = CacheMode.READ_ONLY;

}


