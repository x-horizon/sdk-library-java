package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeDistributedStrategy getInstance() {
        return INSTANCE;
    }


    @Override
    public Object getAndSet(CacheDataManager dataManager, String key) {
        return null;
    }
    
}


