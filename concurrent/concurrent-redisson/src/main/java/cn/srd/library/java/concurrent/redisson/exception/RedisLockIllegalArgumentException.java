package cn.srd.library.java.concurrent.redisson.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * Redis 非法参数异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@StandardException
public class RedisLockIllegalArgumentException extends RuntimeException {

    @Serial private static final long serialVersionUID = 5365186821008194503L;

}
