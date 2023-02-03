package cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.exception;

import cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.EnumAutowired;

import java.io.Serial;

/**
 * 根据 {@link EnumAutowired#findBeanNamesMayAutowiredRule()} 无法找到可能要注入的实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredMayAutowiredBeansNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5574291562911269061L;

    /**
     * public constructor
     */
    public EnumAutowiredMayAutowiredBeansNotFoundException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public EnumAutowiredMayAutowiredBeansNotFoundException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public EnumAutowiredMayAutowiredBeansNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public EnumAutowiredMayAutowiredBeansNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
