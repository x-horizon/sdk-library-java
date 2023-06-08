package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
@AllArgsConstructor
public enum CacheType {

    MAP(null),
    CAFFEINE(null),
    REDIS(null),

    ;

    private CacheTemplate<String> template;

    private static final Map<CacheType, Supplier<CacheTemplate<String>>> cacheTypeSupplier = Map.of(
            CacheType.MAP, Caches::withMap,
            CacheType.CAFFEINE, Caches::withCaffeine,
            CacheType.REDIS, () -> Caches.withRedisson().withBucket()
    );

    public void init() {
        for (CacheType cacheType : CacheType.values()) {
            cacheType.template = cacheTypeSupplier.get(cacheType).get();
        }
    }

}


