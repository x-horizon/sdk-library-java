package cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.exception;

import cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.EnumAutowired;

import java.io.Serial;

/**
 * 无法找到要注入实现类的字段时异常，参考 {@link EnumAutowired#autowiredFiledName()}
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredUnmatchedFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1191863371222442507L;

    /**
     * public constructor
     */
    public EnumAutowiredUnmatchedFieldException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public EnumAutowiredUnmatchedFieldException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public EnumAutowiredUnmatchedFieldException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public EnumAutowiredUnmatchedFieldException(String message, Throwable cause) {
        super(message, cause);
    }

}
