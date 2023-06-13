package cn.srd.itcp.sugar.cache.all.config.properties;

import cn.srd.itcp.sugar.component.lock.redisson.common.core.RedissonFairLock;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * properties for multilevel cache
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.cache.multilevel")
public class CacheMultilevelProperties {

    /**
     * instance
     */
    private static CacheMultilevelProperties instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        setInternalBlockToHitDistributedCacheWaitTime(TimeUtil.wrapper(getBlockToHitDistributedCacheWaitTime()).toMillisecond().toMillis());
        setInternalBlockToHitDistributedCacheLeaseTime(TimeUtil.wrapper(getBlockToHitDistributedCacheLeaseTime()).toMillisecond().toMillis());
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static CacheMultilevelProperties getInstance() {
        return instance;
    }

    /**
     * the max wait time to hit distributed cache, see {@link RedissonFairLock#waitTime()}
     */
    private String blockToHitDistributedCacheWaitTime;

    /**
     * the max lease time to hit distributed cache, see {@link RedissonFairLock#leaseTime()} ()}
     */
    private String blockToHitDistributedCacheLeaseTime;

    /**
     * the millisecond of {@link #blockToHitDistributedCacheWaitTime}
     */
    private long internalBlockToHitDistributedCacheWaitTime;

    /**
     * the millisecond of {@link #blockToHitDistributedCacheLeaseTime}
     */
    private long internalBlockToHitDistributedCacheLeaseTime;

}
