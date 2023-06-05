package cn.srd.itcp.sugar.component.lock.redisson.core;

import cn.srd.itcp.sugar.context.redisson.core.RedissonManager;
import jakarta.annotation.PostConstruct;
import org.redisson.api.RLock;

/**
 * Redisson 分布式单点公平锁操作，参考 {@link RedissonFairLock}
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonFairLockHandler implements RedissonLockTemplate {

    /**
     * instance
     */
    private static RedissonFairLockHandler instance = null;

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
    public static RedissonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedissonManager.getClient().getFairLock(lockName);
    }

}
