package org.horizon.sdk.library.java.cache.all.model.property;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.horizon.sdk.library.java.concurrent.redis.RedisFairLock;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.time.Times;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * properties for multilevel cache
 *
 * @author wjm
 * @since 2023-06-07 16:48
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.cache.multilevel")
public class MultilevelCacheProperty {

    /**
     * instance
     */
    @Getter private static MultilevelCacheProperty instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void initialize() {
        if (Nil.isAllNotBlank(getBlockToHitDistributedCacheWaitTime(), getBlockToHitDistributedCacheLeaseTime())) {
            setInternalBlockToHitDistributedCacheWaitTime(Times.wrapper(getBlockToHitDistributedCacheWaitTime()).toMillisecond().toMillis());
            setInternalBlockToHitDistributedCacheLeaseTime(Times.wrapper(getBlockToHitDistributedCacheLeaseTime()).toMillisecond().toMillis());
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