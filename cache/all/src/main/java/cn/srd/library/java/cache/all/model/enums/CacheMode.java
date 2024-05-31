package cn.srd.library.java.cache.all.model.enums;

import cn.srd.library.java.cache.all.strategy.CacheModeReadOnlyStrategy;
import cn.srd.library.java.cache.all.strategy.CacheModeReadWriteStrategy;
import cn.srd.library.java.cache.all.strategy.CacheModeStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * the cache mode
 *
 * @author wjm
 * @since 2023-06-19 21:26
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