package org.horizon.sdk.library.java.cache.map;

import org.horizon.sdk.library.java.cache.contract.CacheTemplate;

/**
 * Map Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-06 16:14
 */
public interface MapCacheTemplate<K> extends CacheTemplate<K> {

    /**
     * delete all cache
     *
     * @return affected number
     */
    long deleteAll();

}