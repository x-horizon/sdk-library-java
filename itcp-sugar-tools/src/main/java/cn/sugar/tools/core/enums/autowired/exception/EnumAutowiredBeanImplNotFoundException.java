package cn.sugar.tools.core.enums.autowired.exception;

import cn.sugar.tools.core.enums.autowired.EnumAutowired;


/**
 * 根据 {@link EnumAutowired#autowiredBeanClass()} 无法找到其实现类时异常
 *
 * @author wjm
 * @date 2021/9/8 16:07
 */
public class EnumAutowiredBeanImplNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2515941124997405689L;

    public EnumAutowiredBeanImplNotFoundException() {
    }

    public EnumAutowiredBeanImplNotFoundException(String message) {
        super(message);
    }

    public EnumAutowiredBeanImplNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumAutowiredBeanImplNotFoundException(Throwable cause) {
        super(cause);
    }

}
