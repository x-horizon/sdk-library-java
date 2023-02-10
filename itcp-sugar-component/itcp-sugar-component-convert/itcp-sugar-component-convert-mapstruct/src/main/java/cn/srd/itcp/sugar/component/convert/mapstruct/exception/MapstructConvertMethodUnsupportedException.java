package cn.srd.itcp.sugar.component.convert.mapstruct.exception;

import java.io.Serial;

/**
 * 不支持的转换方法异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructConvertMethodUnsupportedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2781116473610565298L;

    /**
     * public constructor
     */
    public MapstructConvertMethodUnsupportedException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public MapstructConvertMethodUnsupportedException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public MapstructConvertMethodUnsupportedException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public MapstructConvertMethodUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

}
