package cn.srd.library.java.concurrent.redisson.core;

import cn.srd.library.java.contract.cache.redis.core.RedisManager;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
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
    @Getter private static RedisFairLockHandler instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    @Override
    public RLock getLock(String lockName) {
        return RedisManager.getClient().getFairLock(lockName);
    }

}
