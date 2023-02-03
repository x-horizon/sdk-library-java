package cn.srd.itcp.sugar.spring.tool.common.core.enums.autowired.exception;

import cn.srd.itcp.sugar.spring.tool.common.core.enums.autowired.EnumAutowired;

import java.io.Serial;

/**
 * 根据 {@link EnumAutowired#autowiredBeanClass()} 无法找到其实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredBeanImplNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2515941124997405689L;

    /**
     *
     */
    public EnumAutowiredBeanImplNotFoundException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public EnumAutowiredBeanImplNotFoundException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public EnumAutowiredBeanImplNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public EnumAutowiredBeanImplNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
