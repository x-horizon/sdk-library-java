package cn.srd.itcp.sugar.database.redisson.core.lock;

import cn.srd.itcp.sugar.database.redisson.support.RedissonManager;
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
     * 实例
     */
    public static RedissonNonFairLockHandler instance = null;

    /**
     * 实例初始化
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static RedissonNonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedissonManager.getClient().getLock(lockName);
    }

}
