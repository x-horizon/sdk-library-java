package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.config.properties.CacheProperties;
import cn.srd.itcp.sugar.cache.all.core.Caches;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;
import cn.srd.itcp.sugar.component.lock.redisson.common.core.RedissonNonFairLockHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex) {
        return RedissonNonFairLockHandler.getInstance().tryLock(
                dataManager, namespace, key, findCacheTypeNameIndex,
                (t1, t2, t3, t4) -> CacheModeLocalStrategy.getInstance().get(dataManager, namespace, key, findCacheTypeNameIndex),
                LOCK_NAME_PREFIX + Caches.withRedisson().withBucket().resolveKey(key, namespace),
                CacheProperties.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheWaitTime(),
                CacheProperties.getInstance().getMultilevel().getInternalBlockToHitDistributedCacheLeaseTime(),
                TimeUnit.MILLISECONDS
        );
    }

}


