package cn.srd.itcp.sugar.cache.redisson.exception;

import java.io.Serial;

/**
 * Redisson 执行异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonExecuteException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1216257915167180153L;

    /**
     * public constructor
     */
    public RedissonExecuteException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public RedissonExecuteException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public RedissonExecuteException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public RedissonExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

}
