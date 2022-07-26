package cn.srd.itcp.sugar.convert.mapstruct.exception;

/**
 * 执行结果类型与提供的目标值类型不一致异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructConvertTargetNotMatchedException extends RuntimeException {

    private static final long serialVersionUID = -5264714970657813977L;

    public MapstructConvertTargetNotMatchedException() {
    }

    public MapstructConvertTargetNotMatchedException(String message) {
        super(message);
    }

    public MapstructConvertTargetNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapstructConvertTargetNotMatchedException(Throwable cause) {
        super(cause);
    }

}
