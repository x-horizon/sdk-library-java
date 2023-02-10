package cn.srd.itcp.sugar.component.convert.jackson.exception;

import java.io.Serial;

/**
 * jackson 序列化异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class JacksonSerializerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5404014469580075860L;

    /**
     * public constructor
     */
    public JacksonSerializerException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public JacksonSerializerException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public JacksonSerializerException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public JacksonSerializerException(String message, Throwable cause) {
        super(message, cause);
    }

}
