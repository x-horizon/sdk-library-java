package cn.srd.itcp.sugar.component.convert.mapstruct.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 执行结果类型与提供的目标值类型不一致异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@StandardException
public class MapstructConvertTargetNotMatchedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5264714970657813977L;

}
