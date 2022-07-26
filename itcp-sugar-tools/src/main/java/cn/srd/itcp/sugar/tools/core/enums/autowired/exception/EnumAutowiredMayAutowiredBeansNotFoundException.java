package cn.srd.itcp.sugar.tools.core.enums.autowired.exception;

import cn.srd.itcp.sugar.tools.core.enums.autowired.EnumAutowired;

/**
 * 根据 {@link EnumAutowired#findBeanNamesMayAutowiredRule()} 无法找到可能要注入的实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredMayAutowiredBeansNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5574291562911269061L;

    public EnumAutowiredMayAutowiredBeansNotFoundException() {
    }

    public EnumAutowiredMayAutowiredBeansNotFoundException(String message) {
        super(message);
    }

    public EnumAutowiredMayAutowiredBeansNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumAutowiredMayAutowiredBeansNotFoundException(Throwable cause) {
        super(cause);
    }

}
