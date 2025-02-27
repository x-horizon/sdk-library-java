package org.horizon.sdk.library.java.cache.all.strategy;

import org.horizon.sdk.library.java.cache.all.manager.CacheDataManager;
import org.springframework.cache.support.NullValue;

import java.util.Map;

/**
 * the cache type strategy
 *
 * @author wjm
 * @since 2023-06-12 20:49
 */
public interface CacheTypeStrategy {

    /**
     * get cache value by key
     *
     * @param dataManager            see {@link CacheDataManager}
     * @param namespace              the cache namespace
     * @param key                    the cache key
     * @param findCacheTypeNameIndex current find cache type name index
     * @return cache value
     */
    Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex);

    /**
     * get cache value by namespace
     *
     * @param dataManager            see {@link CacheDataManager}
     * @param namespace              the cache namespace
     * @param findCacheTypeNameIndex current find cache type name index
     * @param <V>                    the cache value type
     * @return cache value
     */
    <V> Map<String, V> getMapByNamespace(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex);

    /**
     * get cache value by namespace, the value is {@link NullValue} in map will be filtered
     *
     * @param dataManager            see {@link CacheDataManager}
     * @param namespace              the cache namespace
     * @param findCacheTypeNameIndex current find cache type name index
     * @param <V>                    the cache value type
     * @return cache value
     */
    <V> Map<String, V> getMapByNamespaceWithoutNullValue(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex);

}