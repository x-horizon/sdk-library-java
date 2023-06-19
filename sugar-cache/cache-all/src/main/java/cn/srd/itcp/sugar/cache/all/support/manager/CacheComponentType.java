package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeDistributedStrategy;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeLocalStrategy;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeStrategy;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Supplier;

/**
 * the cache type
 *
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
@AllArgsConstructor
public enum CacheComponentType {

    /**
     * use cache by map
     */
    MAP(CacheModeLocalStrategy.getInstance()),

    /**
     * use cache by caffeine
     */
    CAFFEINE(CacheModeLocalStrategy.getInstance()),

    /**
     * use cache by redis
     */
    REDIS(CacheModeDistributedStrategy.getInstance()),

    ;

    /**
     * the cache mode strategy
     */
    private final CacheModeStrategy strategy;

    /**
     * the cache template init
     */
    public static final Map<CacheComponentType, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            MAP, Caches::withMap,
            CAFFEINE, Caches::withCaffeine,
            REDIS, () -> Caches.withRedis().withBucket()
    );

}


