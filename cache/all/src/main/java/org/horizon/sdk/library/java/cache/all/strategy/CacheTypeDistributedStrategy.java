package org.horizon.sdk.library.java.cache.all.strategy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.cache.all.Caches;
import org.horizon.sdk.library.java.cache.all.manager.CacheDataManager;
import org.horizon.sdk.library.java.cache.all.model.property.CacheProperty;
import org.horizon.sdk.library.java.concurrent.redis.strategy.RedisNonFairLockHandler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * the distributed cache type strategy implement
 *
 * @author wjm
 * @since 2023-06-12 20:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheTypeDistributedStrategy implements CacheTypeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheTypeDistributedStrategy INSTANCE = new CacheTypeDistributedStrategy();

    /*
     * the lock name prefix
     */
    private static final String LOCK_NAME_PREFIX = "lock:";

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheTypeDistributedStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex) {
        return RedisNonFairLockHandler.getInstance().tryLock(
                dataManager, namespace, key, findCacheTypeNameIndex,
                (t1, t2, t3, t4) -> CacheTypeLocalStrategy.getInstance().get(dataManager, namespace, key, findCacheTypeNameIndex),
                LOCK_NAME_PREFIX + Caches.withRedis().withBucket().resolveKey(key, namespace),
                CacheProperty.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheWaitTime(),
                CacheProperty.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheLeaseTime(),
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex) {
        return RedisNonFairLockHandler.getInstance().tryLock(
                dataManager, namespace, findCacheTypeNameIndex,
                (t1, t2, t3) -> CacheTypeLocalStrategy.getInstance().getMapByNamespace(dataManager, namespace, findCacheTypeNameIndex),
                LOCK_NAME_PREFIX + Caches.withRedis().withBucket().resolveFuzzyKey(namespace),
                CacheProperty.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheWaitTime(),
                CacheProperty.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheLeaseTime(),
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public <V> Map<String, V> getMapByNamespaceWithoutNullValue(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex) {
        return RedisNonFairLockHandler.getInstance().tryLock(
                dataManager, namespace, findCacheTypeNameIndex,
                (t1, t2, t3) -> CacheTypeLocalStrategy.getInstance().getMapByNamespaceWithoutNullValue(dataManager, namespace, findCacheTypeNameIndex),
                LOCK_NAME_PREFIX + Caches.withRedis().withBucket().resolveFuzzyKey(namespace),
                CacheProperty.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheWaitTime(),
                CacheProperty.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheLeaseTime(),
                TimeUnit.MILLISECONDS
        );
    }

}