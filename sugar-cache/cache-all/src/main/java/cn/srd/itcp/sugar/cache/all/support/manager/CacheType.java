package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.core.Caches;
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
public enum CacheType {

    /**
     * use cache by map
     */
    MAP(CacheMode.LOCAL),

    /**
     * use cache by caffeine
     */
    CAFFEINE(CacheMode.LOCAL),

    /**
     * use cache by redis
     */
    REDIS(CacheMode.DISTRIBUTED),

    ;

    public enum CacheMode {
        LOCAL,
        DISTRIBUTED,

        ;
    }

    private final CacheMode mode;

    /**
     * the cache template init
     */
    public static final Map<CacheType, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            MAP, Caches::withMap,
            CAFFEINE, Caches::withCaffeine,
            REDIS, () -> Caches.withRedisson().withBucket()
    );

}


