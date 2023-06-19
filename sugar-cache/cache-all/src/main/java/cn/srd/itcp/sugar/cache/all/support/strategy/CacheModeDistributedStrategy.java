package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.config.properties.CacheProperties;
import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;
import cn.srd.itcp.sugar.component.lock.redis.common.core.RedisNonFairLockHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * the distributed cache mode strategy implement
 *
 * @author wjm
 * @since 2023-06-12 20:49:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheModeDistributedStrategy implements CacheModeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheModeDistributedStrategy INSTANCE = new CacheModeDistributedStrategy();

    /*
     * the lock name prefix
     */
    private static final String LOCK_NAME_PREFIX = "lock:";

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeDistributedStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheComponentTypeNameIndex) {
        return RedisNonFairLockHandler.getInstance().tryLock(
                dataManager, namespace, key, findCacheComponentTypeNameIndex,
                (t1, t2, t3, t4) -> CacheModeLocalStrategy.getInstance().get(dataManager, namespace, key, findCacheComponentTypeNameIndex),
                LOCK_NAME_PREFIX + Caches.withRedis().withBucket().resolveKey(key, namespace),
                CacheProperties.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheWaitTime(),
                CacheProperties.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheLeaseTime(),
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(CacheDataManager dataManager, String namespace, int findCacheComponentTypeNameIndex) {
        return RedisNonFairLockHandler.getInstance().tryLock(
                dataManager, namespace, findCacheComponentTypeNameIndex,
                (t1, t2, t3) -> CacheModeLocalStrategy.getInstance().getMapByNamespace(dataManager, namespace, findCacheComponentTypeNameIndex),
                LOCK_NAME_PREFIX + namespace,
                CacheProperties.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheWaitTime(),
                CacheProperties.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheLeaseTime(),
                TimeUnit.MILLISECONDS
        );
    }

}


