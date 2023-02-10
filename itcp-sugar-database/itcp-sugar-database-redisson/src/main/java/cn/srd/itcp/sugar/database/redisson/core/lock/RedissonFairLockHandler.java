package cn.srd.itcp.sugar.database.redisson.core.lock;

import cn.srd.itcp.sugar.database.redisson.support.RedissonManager;
import jakarta.annotation.PostConstruct;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

/**
 * Redisson 分布式单点公平锁操作，参考 {@link RedissonFairLock}
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@Component
public class RedissonFairLockHandler implements RedissonLockTemplate {

    /**
     * 实例
     */
    private static RedissonFairLockHandler instance = null;

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
    public static RedissonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedissonManager.getClient().getFairLock(lockName);
    }

}
