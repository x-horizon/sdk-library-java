package cn.srd.sugar.contract.throwable.core;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 不支持操作的异常
 *
 * @author wjm
 * @since 2021/4/13 11:52
 */
@StandardException
public class UnsupportedOperationException extends RuntimeException {

    @Serial private static final long serialVersionUID = -6315727057670035084L;

}
