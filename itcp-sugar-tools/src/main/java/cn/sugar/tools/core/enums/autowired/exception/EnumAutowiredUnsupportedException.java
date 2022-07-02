package cn.sugar.tools.core.enums.autowired.exception;

import cn.sugar.tools.core.enums.autowired.EnumAutowired;


/**
 * 不支持被 {@link EnumAutowired} 标记的异常
 *
 * @author wjm
 * @date 2021/9/8 16:07
 */
public class EnumAutowiredUnsupportedException extends RuntimeException {

    private static final long serialVersionUID = 3787765610600778202L;

    public EnumAutowiredUnsupportedException() {
    }

    public EnumAutowiredUnsupportedException(String message) {
        super(message);
    }

    public EnumAutowiredUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumAutowiredUnsupportedException(Throwable cause) {
        super(cause);
    }

}
