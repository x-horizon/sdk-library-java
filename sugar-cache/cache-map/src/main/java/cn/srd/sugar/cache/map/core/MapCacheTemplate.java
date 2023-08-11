package cn.srd.sugar.cache.map.core;

import cn.srd.sugar.cache.contract.core.CacheTemplate;

/**
 * Map Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
public interface MapCacheTemplate<K> extends CacheTemplate<K> {

    /**
     * delete all cache
     *
     * @return affected number
     */
    long deleteAll();

}
