package org.horizon.sdk.library.java.cache.all.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.cache.all.Caches;
import org.horizon.sdk.library.java.cache.all.strategy.CacheTypeDistributedStrategy;
import org.horizon.sdk.library.java.cache.all.strategy.CacheTypeLocalStrategy;
import org.horizon.sdk.library.java.cache.all.strategy.CacheTypeStrategy;
import org.horizon.sdk.library.java.cache.contract.CacheTemplate;

import java.util.Map;
import java.util.function.Supplier;

/**
 * the cache component type
 *
 * @author wjm
 * @since 2023-06-06 16:14
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