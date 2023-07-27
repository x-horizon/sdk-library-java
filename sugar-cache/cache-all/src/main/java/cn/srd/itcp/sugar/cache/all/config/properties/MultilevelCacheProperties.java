package cn.srd.itcp.sugar.cache.all.config.properties;

import cn.srd.itcp.sugar.component.lock.redis.common.core.RedisFairLock;
import cn.srd.itcp.sugar.tool.core.object.Objects;
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
public class MultilevelCacheProperties {

    /**
     * instance
     */
    @Getter private static MultilevelCacheProperties instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        if (Objects.isNotBlank(getBlockToHitDistributedCacheWaitTime(), getBlockToHitDistributedCacheLeaseTime())) {
            setInternalBlockToHitDistributedCacheWaitTime(TimeUtil.wrapper(getBlockToHitDistributedCacheWaitTime()).toMillisecond().toMillis());
            setInternalBlockToHitDistributedCacheLeaseTime(TimeUtil.wrapper(getBlockToHitDistributedCacheLeaseTime()).toMillisecond().toMillis());
        }
        instance = this;
    }

    /**
     * the max wait time to hit distributed cache, see {@link RedisFairLock#waitTime()}
     */
    private String blockToHitDistributedCacheWaitTime;

    /**
     * the max lease time to hit distributed cache, see {@link RedisFairLock#leaseTime()} ()}
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
