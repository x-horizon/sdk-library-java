package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
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
public enum CacheType {

    /**
     * cache in local
     */
    LOCAL(CacheModule.MAP, CacheModule.CAFFEINE),

    /**
     * distributed cache
     */
    DISTRIBUTED(CacheModule.REDIS),

    ;

    /**
     * the cache module
     */
    public enum CacheModule {

        /**
         * use cache by map
         */
        MAP,

        /**
         * use cache by caffeine
         */
        CAFFEINE,

        /**
         * use cache by redis
         */
        REDIS,
        ;
    }

    /**
     * the constructor
     *
     * @param modules see {@link CacheModule}
     */
    CacheType(CacheModule... modules) {
        this.modules = modules;
    }

    /**
     * the cache modules
     */
    private final CacheModule[] modules;

    /**
     * the cache template init
     */
    public static final Map<CacheType.CacheModule, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            CacheModule.MAP, Caches::withMap,
            CacheModule.CAFFEINE, Caches::withCaffeine,
            CacheModule.REDIS, () -> Caches.withRedisson().withBucket()
    );

}


