package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheTypeDistributedStrategy;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheTypeLocalStrategy;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheTypeStrategy;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Supplier;

/**
 * the cache component type
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
    MAP(CacheTypeLocalStrategy.getInstance()),

    /**
     * use cache by caffeine
     */
    CAFFEINE(CacheTypeLocalStrategy.getInstance()),

    /**
     * use cache by redis
     */
    REDIS(CacheTypeDistributedStrategy.getInstance()),

    ;

    /**
     * the cache type strategy
     */
    private final CacheTypeStrategy strategy;

    /**
     * the cache template init
     */
    public static final Map<CacheComponentType, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            MAP, Caches::withMap,
            CAFFEINE, Caches::withCaffeine,
            REDIS, () -> Caches.withRedis().withBucket()
    );

}


