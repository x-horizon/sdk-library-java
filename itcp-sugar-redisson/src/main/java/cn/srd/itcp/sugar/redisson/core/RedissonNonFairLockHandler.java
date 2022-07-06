package cn.srd.itcp.sugar.redisson.core;

import cn.srd.itcp.sugar.redisson.support.RedissonLockTemplate;
import cn.srd.itcp.sugar.redisson.support.RedissonManager;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Redisson 分布式单点非公平锁操作，参考 {@link RedissonNonFairLock}
 *
 * @author wjm
 * @date 2020/12/12 18:06
 */
@Component
public class RedissonNonFairLockHandler implements RedissonLockTemplate {

    public static RedissonNonFairLockHandler instance = null;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static RedissonNonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedissonManager.getRedissonClient().getLock(lockName);
    }

}
