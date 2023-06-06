package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;

/**
 * Caffeine Cache Template
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
public interface CaffeineCacheTemplate extends CacheTemplate<Object> {

    /**
     * delete all cache
     */
    void deleteAll();

}
