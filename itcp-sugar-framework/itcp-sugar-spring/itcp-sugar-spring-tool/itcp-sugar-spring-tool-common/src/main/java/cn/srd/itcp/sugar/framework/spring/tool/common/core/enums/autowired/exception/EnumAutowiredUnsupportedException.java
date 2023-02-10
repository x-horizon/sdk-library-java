package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.EnumAutowired;

import java.io.Serial;

/**
 * 不支持被 {@link EnumAutowired} 标记的异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredUnsupportedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3787765610600778202L;

    /**
     * public constructor
     */
    public EnumAutowiredUnsupportedException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public EnumAutowiredUnsupportedException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public EnumAutowiredUnsupportedException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public EnumAutowiredUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

}
