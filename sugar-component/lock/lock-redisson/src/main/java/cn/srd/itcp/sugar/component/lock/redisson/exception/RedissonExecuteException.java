package cn.srd.itcp.sugar.component.lock.redisson.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * Redisson 执行异常
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@StandardException
public class RedissonExecuteException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1216257915167180153L;

}
