package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
public enum CacheType {

    MAP,
    CAFFEINE,
    REDIS,

    ;

    public static final Map<CacheType, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            MAP, Caches::withMap,
            CAFFEINE, Caches::withCaffeine,
            REDIS, () -> Caches.withRedisson().withBucket()
    );

}


