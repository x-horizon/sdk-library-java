package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;

/**
 * Caffeine 缓存模板
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
public interface CaffeineCacheTemplate extends CacheTemplate<Object> {

    /**
     * 删除所有缓存对象
     */
    void deleteAll();

}
