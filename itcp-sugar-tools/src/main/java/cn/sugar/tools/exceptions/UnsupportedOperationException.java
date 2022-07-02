package cn.sugar.tools.exceptions;


/**
 * 不支持操作的异常
 *
 * @author wjm
 * @date 2021/4/13 11:52
 */
public class UnsupportedOperationException extends RuntimeException {

    private static final long serialVersionUID = -6315727057670035084L;

    public UnsupportedOperationException() {
    }

    public UnsupportedOperationException(String message) {
        super(message);
    }

    public UnsupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedOperationException(Throwable cause) {
        super(cause);
    }

}
