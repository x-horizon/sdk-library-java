package cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.exception;

import cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.EnumAutowired;

import java.io.Serial;

/**
 * 根据 {@link EnumAutowired#findBeanNameToAutowiredRule()} 无法找到最终要注入的实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredAutowiredBeanNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6283571063586018310L;

    /**
     * public constructor
     */
    public EnumAutowiredAutowiredBeanNotFoundException() {
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public EnumAutowiredAutowiredBeanNotFoundException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param cause exception wrapper
     */
    public EnumAutowiredAutowiredBeanNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   exception wrapper
     */
    public EnumAutowiredAutowiredBeanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
