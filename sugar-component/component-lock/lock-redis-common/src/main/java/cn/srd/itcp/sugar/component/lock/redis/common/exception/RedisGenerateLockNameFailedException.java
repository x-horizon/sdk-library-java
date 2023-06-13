package cn.srd.itcp.sugar.component.lock.redis.common.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * Redis 生成锁名失败异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@StandardException
public class RedisGenerateLockNameFailedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4788862752143525070L;

}
