package cn.srd.itcp.sugar.cache.redisson.common.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * Redisson 生成锁名失败异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@StandardException
public class RedissonGenerateLockNameFailedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4788862752143525070L;

}
