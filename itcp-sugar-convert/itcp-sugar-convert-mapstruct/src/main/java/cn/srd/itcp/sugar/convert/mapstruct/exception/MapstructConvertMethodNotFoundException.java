package cn.srd.itcp.sugar.convert.mapstruct.exception;

import java.io.Serial;

/**
 * 未找到转换方法时的异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructConvertMethodNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6760724713927618055L;

    /**
     * public constructor
     */
    public MapstructConvertMethodNotFoundException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public MapstructConvertMethodNotFoundException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public MapstructConvertMethodNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public MapstructConvertMethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
