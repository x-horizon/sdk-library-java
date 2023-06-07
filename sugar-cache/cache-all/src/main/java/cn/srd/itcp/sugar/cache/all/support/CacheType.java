package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
@AllArgsConstructor
public enum CacheType {

    MAP(Caches.withMap()),
    CAFFEINE(Caches.withCaffeine()),
    REDIS(Caches.withCaffeine()),

    ;

    private final CacheTemplate<Object> template;

}


