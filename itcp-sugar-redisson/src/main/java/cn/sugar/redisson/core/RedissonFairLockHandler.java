package cn.sugar.redisson.core;

import cn.sugar.redisson.core.support.RedissonLockTemplate;
import cn.sugar.redisson.core.support.RedissonManager;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Redisson 分布式单点公平锁操作，参考 {@link RedissonFairLock}
 *
 * @author wjm
 * @date 2020/12/12 18:06
 */
@Component
public class RedissonFairLockHandler implements RedissonLockTemplate {

    private static RedissonFairLockHandler instance = null;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static RedissonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedissonManager.getRedissonClient().getFairLock(lockName);
    }

}
