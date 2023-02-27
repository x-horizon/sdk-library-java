package cn.srd.itcp.sugar.cache.redisson.exception;

import java.io.Serial;

/**
 * Redisson 生成锁名失败异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonGenerateLockNameFailedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4788862752143525070L;

    /**
     * public constructor
     */
    public RedissonGenerateLockNameFailedException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public RedissonGenerateLockNameFailedException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public RedissonGenerateLockNameFailedException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public RedissonGenerateLockNameFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
