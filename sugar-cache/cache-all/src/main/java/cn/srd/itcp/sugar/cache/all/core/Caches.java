package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.caffeine.core.CacheCaffeineTemplate;
import cn.srd.itcp.sugar.cache.caffeine.core.CacheCaffeines;
import cn.srd.itcp.sugar.cache.map.core.CacheMapTemplate;
import cn.srd.itcp.sugar.cache.map.core.CacheMaps;
import cn.srd.itcp.sugar.cache.redisson.core.CacheRedissons;
import cn.srd.itcp.sugar.context.caffeine.config.properties.CacheCaffeineProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * all cache can be used
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Caches {

    /**
     * map implement
     *
     * @param <K> cache key type
     * @return cache operation object
     */
    public static <K> CacheMapTemplate<K> withMap() {
        return CacheMaps.newInstance();
    }

    /**
     * map implement
     *
     * @param initialCapacity initial capacity
     * @param <K>             cache key type
     * @return cache operation object
     */
    public static <K> CacheMapTemplate<K> withMap(int initialCapacity) {
        return CacheMaps.newInstance(initialCapacity);
    }

    /**
     * caffeine implement
     *
     * @param <K> cache key type
     * @return cache operation object
     */
    public static <K> CacheCaffeineTemplate<K> withCaffeine() {
        return CacheCaffeines.newInstance();
    }

    /**
     * caffeine implement
     *
     * @param cacheCaffeineProperties {@link CacheCaffeineProperties}
     * @param <K>                     cache key type
     * @return cache operation object
     */
    public static <K> CacheCaffeineTemplate<K> withCaffeine(CacheCaffeineProperties cacheCaffeineProperties) {
        return CacheCaffeines.newInstance(cacheCaffeineProperties);
    }

    /**
     * redisson implement
     *
     * @return cache operation object
     */
    public static CacheRedissons withRedisson() {
        return CacheRedissons.getInstance();
    }

}
