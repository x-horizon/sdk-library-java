package cn.srd.itcp.sugar.cache.redisson.common.exception;

import java.io.Serial;

/**
 * Redisson 非法参数异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonIllegalArgumentException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5365186821008194503L;

    /**
     * public constructor
     */
    public RedissonIllegalArgumentException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public RedissonIllegalArgumentException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public RedissonIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public RedissonIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

}
