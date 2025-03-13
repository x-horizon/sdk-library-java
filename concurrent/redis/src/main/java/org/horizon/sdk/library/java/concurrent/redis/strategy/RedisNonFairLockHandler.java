package org.horizon.sdk.library.java.concurrent.redis.strategy;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.horizon.sdk.library.java.concurrent.redis.RedisNonFairLock;
import org.horizon.sdk.library.java.contract.component.redis.RedisManager;
import org.redisson.api.RLock;

/**
 * Redis 分布式单点非公平锁操作，参考 {@link RedisNonFairLock}
 *
 * @author wjm
 * @since 2020-12-12 18:06
 */
public class RedisNonFairLockHandler implements RedisLockTemplate {

    /**
     * instance
     */
    @Getter public static RedisNonFairLockHandler instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void initialize() {
        instance = this;
    }

    @Override
    public RLock getLock(String lockName) {
        return RedisManager.getClient().getLock(lockName);
    }

}