package cn.srd.itcp.sugar.component.convert.mapstruct.exception;

import java.io.Serial;

/**
 * 执行结果类型与提供的目标值类型不一致异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructConvertTargetNotMatchedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5264714970657813977L;

    /**
     * public constructor
     */
    public MapstructConvertTargetNotMatchedException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public MapstructConvertTargetNotMatchedException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause wrapper exception
     */
    public MapstructConvertTargetNotMatchedException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public MapstructConvertTargetNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

}
