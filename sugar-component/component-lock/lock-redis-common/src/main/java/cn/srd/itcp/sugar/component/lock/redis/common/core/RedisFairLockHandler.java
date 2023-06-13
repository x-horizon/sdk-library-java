package cn.srd.itcp.sugar.component.lock.redis.common.core;

import cn.srd.itcp.sugar.context.redis.core.RedisManager;
import jakarta.annotation.PostConstruct;
import org.redisson.api.RLock;

/**
 * Redis 分布式单点公平锁操作，参考 {@link RedisFairLock}
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedisFairLockHandler implements RedisLockTemplate {

    /**
     * instance
     */
    private static RedisFairLockHandler instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static RedisFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedisManager.getClient().getFairLock(lockName);
    }

}
