package cn.srd.itcp.sugar.cache.redisson.common.core.lock;

import cn.srd.itcp.sugar.cache.redisson.common.support.RedissonManager;
import jakarta.annotation.PostConstruct;
import org.redisson.api.RLock;

/**
 * Redisson 分布式单点非公平锁操作，参考 {@link RedissonNonFairLock}
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonNonFairLockHandler implements RedissonLockTemplate {

    /**
     * instance
     */
    public static RedissonNonFairLockHandler instance = null;

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
    public static RedissonNonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedissonManager.getClient().getLock(lockName);
    }

}
