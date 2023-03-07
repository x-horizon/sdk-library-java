package cn.srd.itcp.sugar.component.convert.jackson.exception;

import java.io.Serial;

/**
 * jackson 反序列化异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class JacksonDeserializerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2587853132749498768L;

    /**
     * public constructor
     */
    public JacksonDeserializerException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public JacksonDeserializerException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public JacksonDeserializerException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public JacksonDeserializerException(String message, Throwable cause) {
        super(message, cause);
    }

}
