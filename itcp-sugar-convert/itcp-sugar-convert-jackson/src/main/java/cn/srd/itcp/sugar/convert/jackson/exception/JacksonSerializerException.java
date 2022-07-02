package cn.srd.itcp.sugar.convert.jackson.exception;


/**
 * jackson 序列化异常
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class JacksonSerializerException extends RuntimeException {

    private static final long serialVersionUID = -5404014469580075860L;

    public JacksonSerializerException() {
    }

    public JacksonSerializerException(String message) {
        super(message);
    }

    public JacksonSerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public JacksonSerializerException(Throwable cause) {
        super(cause);
    }

}
