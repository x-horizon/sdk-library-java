package cn.srd.itcp.sugar.convert.jackson.exception;

/**
 * jackson 反序列化异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class JacksonDeserializerException extends RuntimeException {

    private static final long serialVersionUID = -2587853132749498768L;

    public JacksonDeserializerException() {
    }

    public JacksonDeserializerException(String message) {
        super(message);
    }

    public JacksonDeserializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public JacksonDeserializerException(Throwable cause) {
        super(cause);
    }

}
