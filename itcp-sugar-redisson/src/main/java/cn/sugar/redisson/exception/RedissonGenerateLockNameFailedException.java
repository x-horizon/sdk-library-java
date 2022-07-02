package cn.sugar.redisson.exception;

/**
 * Redisson 生成锁名失败异常
 *
 * @author wjm
 * @date 2020/12/12 18:06
 */
public class RedissonGenerateLockNameFailedException extends RuntimeException {

    private static final long serialVersionUID = 4788862752143525070L;

    public RedissonGenerateLockNameFailedException() {
    }

    public RedissonGenerateLockNameFailedException(String message) {
        super(message);
    }

    public RedissonGenerateLockNameFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedissonGenerateLockNameFailedException(Throwable cause) {
        super(cause);
    }

}
