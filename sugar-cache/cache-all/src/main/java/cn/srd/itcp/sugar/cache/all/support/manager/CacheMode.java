package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeReadOnlyStrategy;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeReadWriteStrategy;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * the cache mode
 *
 * @author wjm
 * @since 2023-06-19 21:26:47
 */
@Getter
@AllArgsConstructor
public enum CacheMode {

    /**
     * when use this mode, will never update cache value
     */
    READ_ONLY(CacheModeReadOnlyStrategy.getInstance()),

    /**
     * when use this mode, will update cache value
     */
    READ_WRITE(CacheModeReadWriteStrategy.getInstance()),

    ;

    /**
     * the cache mode strategy
     */
    private final CacheModeStrategy strategy;

}


