package cn.srd.library.java.cache.all.strategy;

import cn.srd.library.java.cache.all.model.enums.CacheMode;

/**
 * the cache mode strategy
 *
 * @author wjm
 * @since 2023-06-19 21:26
 */
public interface CacheModeStrategy {

    /**
     * the default cache mode
     */
    CacheMode DEFAULT_CACHE_MODE = CacheMode.READ_ONLY;

}