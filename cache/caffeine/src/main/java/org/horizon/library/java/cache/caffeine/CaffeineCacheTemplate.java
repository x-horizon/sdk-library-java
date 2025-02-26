package org.horizon.library.java.cache.caffeine;

import org.horizon.library.java.cache.contract.CacheTemplate;

/**
 * Caffeine Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 17:01
 */
public interface CaffeineCacheTemplate<K> extends CacheTemplate<K> {

    /**
     * delete all cache
     *
     * @return affected number
     */
    long deleteAll();

}