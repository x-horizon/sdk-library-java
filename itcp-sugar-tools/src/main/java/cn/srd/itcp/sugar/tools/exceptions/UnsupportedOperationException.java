package cn.srd.itcp.sugar.tools.exceptions;

import java.io.Serial;

/**
 * 不支持操作的异常
 *
 * @author wjm
 * @since 2021/4/13 11:52
 */
public class UnsupportedOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6315727057670035084L;

    /**
     * public constructor
     */
    public UnsupportedOperationException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public UnsupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public UnsupportedOperationException(Throwable cause) {
        super(cause);
    }

}
