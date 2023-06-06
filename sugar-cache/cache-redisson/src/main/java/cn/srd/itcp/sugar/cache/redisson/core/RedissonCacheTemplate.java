package cn.srd.itcp.sugar.cache.redisson.core;

import cn.srd.itcp.sugar.cache.contract.core.CapableExpirationCacheTemplate;

/**
 * Redisson Cache Template
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface RedissonCacheTemplate extends CapableExpirationCacheTemplate<String> {

    /**
     * 模糊查询某个命名空间的关键字
     */
    String NAMESPACE_KEY_WORD = ":*";

}
