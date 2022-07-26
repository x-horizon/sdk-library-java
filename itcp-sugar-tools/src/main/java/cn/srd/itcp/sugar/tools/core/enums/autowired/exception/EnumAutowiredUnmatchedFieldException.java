package cn.srd.itcp.sugar.tools.core.enums.autowired.exception;

import cn.srd.itcp.sugar.tools.core.enums.autowired.EnumAutowired;

/**
 * 无法找到要注入实现类的字段时异常，参考 {@link EnumAutowired#autowiredFiledName()}
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class EnumAutowiredUnmatchedFieldException extends RuntimeException {

    private static final long serialVersionUID = -1191863371222442507L;

    public EnumAutowiredUnmatchedFieldException() {
    }

    public EnumAutowiredUnmatchedFieldException(String message) {
        super(message);
    }

    public EnumAutowiredUnmatchedFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumAutowiredUnmatchedFieldException(Throwable cause) {
        super(cause);
    }

}
