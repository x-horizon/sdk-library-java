package cn.sugar.tools.core.enums.autowired.exception;

import cn.sugar.tools.core.enums.autowired.EnumAutowired;


/**
 * 根据 {@link EnumAutowired#findBeanNameToAutowiredRule()} 无法找到最终要注入的实现类时异常
 *
 * @author wjm
 * @date 2021/9/8 16:07
 */
public class EnumAutowiredAutowiredBeanNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6283571063586018310L;

    public EnumAutowiredAutowiredBeanNotFoundException() {
    }

    public EnumAutowiredAutowiredBeanNotFoundException(String message) {
        super(message);
    }

    public EnumAutowiredAutowiredBeanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumAutowiredAutowiredBeanNotFoundException(Throwable cause) {
        super(cause);
    }

}
