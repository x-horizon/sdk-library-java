package cn.srd.library.java.cache.caffeine;

import cn.srd.library.java.cache.contract.CacheTemplate;

/**
 * Caffeine Cache Template
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
public interface CaffeineCacheTemplate<K> extends CacheTemplate<K> {

    /**
     * delete all cache
     *
     * @return affected number
     */
    long deleteAll();

}
