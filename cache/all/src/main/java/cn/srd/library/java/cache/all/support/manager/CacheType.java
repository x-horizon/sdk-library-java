package cn.srd.library.java.cache.all.support.manager;

import cn.srd.library.java.cache.all.Caches;
import cn.srd.library.java.cache.all.support.strategy.CacheTypeDistributedStrategy;
import cn.srd.library.java.cache.all.support.strategy.CacheTypeLocalStrategy;
import cn.srd.library.java.cache.all.support.strategy.CacheTypeStrategy;
import cn.srd.library.java.cache.contract.CacheTemplate;
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
public enum CacheType {

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
    public static final Map<CacheType, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            MAP, Caches::withMap,
            CAFFEINE, Caches::withCaffeine,
            REDIS, () -> Caches.withRedis().withBucket()
    );

}


