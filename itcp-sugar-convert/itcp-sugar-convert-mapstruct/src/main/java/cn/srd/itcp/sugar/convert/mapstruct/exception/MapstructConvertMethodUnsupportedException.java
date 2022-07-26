package cn.srd.itcp.sugar.convert.mapstruct.exception;

/**
 * 不支持的转换方法异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructConvertMethodUnsupportedException extends RuntimeException {

    private static final long serialVersionUID = 2781116473610565298L;

    public MapstructConvertMethodUnsupportedException() {
    }

    public MapstructConvertMethodUnsupportedException(String message) {
        super(message);
    }

    public MapstructConvertMethodUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapstructConvertMethodUnsupportedException(Throwable cause) {
        super(cause);
    }

}
