package cn.srd.itcp.sugar.redisson.exception;

/**
 * Redisson 非法参数异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonIllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = 5365186821008194503L;

    public RedissonIllegalArgumentException() {
    }

    public RedissonIllegalArgumentException(String message) {
        super(message);
    }

    public RedissonIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedissonIllegalArgumentException(Throwable cause) {
        super(cause);
    }

}
