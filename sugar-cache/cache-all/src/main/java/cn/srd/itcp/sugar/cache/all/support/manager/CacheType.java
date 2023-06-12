package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import lombok.Getter;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
public enum CacheType {

    LOCAL(CacheModule.MAP, CacheModule.CAFFEINE),
    DISTRIBUTED(CacheModule.REDIS),

    ;

    public enum CacheModule {
        MAP,
        CAFFEINE,
        REDIS,
        ;
    }

    CacheType(CacheModule... modules) {
        this.modules = modules;
    }

    private final CacheModule[] modules;

    public static final Map<CacheType.CacheModule, Supplier<CacheTemplate<String>>> CACHE_TEMPLATE_SUPPLIER = Map.of(
            CacheModule.MAP, Caches::withMap,
            CacheModule.CAFFEINE, Caches::withCaffeine,
            CacheModule.REDIS, () -> Caches.withRedisson().withBucket()
    );

}


