package cn.srd.itcp.sugar.cache.map.core;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;

/**
 * Map Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
public interface CacheMapTemplate<K> extends CacheTemplate<K> {

    /**
     * delete all cache
     *
     * @return affected number
     */
    long deleteAll();

}
