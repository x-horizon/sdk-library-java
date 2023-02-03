package cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.exception;

import cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.EnumAutowired;

import java.io.Serial;

/**
 * 匹配到多个要注入实现类的字段时异常，参考 {@link EnumAutowired#autowiredFiledName()}
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredMultiMatchedFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1568176615358892865L;

    /**
     * public constructor
     */
    public EnumAutowiredMultiMatchedFieldException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public EnumAutowiredMultiMatchedFieldException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public EnumAutowiredMultiMatchedFieldException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public EnumAutowiredMultiMatchedFieldException(String message, Throwable cause) {
        super(message, cause);
    }

}
