package cn.sugar.redisson.exception;

/**
 * Redisson 执行异常
 *
 * @author wjm
 * @date 2020/12/12 18:06
 */
public class RedissonExecuteException extends RuntimeException {

    private static final long serialVersionUID = 1216257915167180153L;

    public RedissonExecuteException() {
    }

    public RedissonExecuteException(String message) {
        super(message);
    }

    public RedissonExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedissonExecuteException(Throwable cause) {
        super(cause);
    }

}
