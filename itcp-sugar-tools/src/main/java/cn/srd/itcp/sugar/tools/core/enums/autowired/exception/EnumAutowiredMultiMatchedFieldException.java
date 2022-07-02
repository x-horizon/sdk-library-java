package cn.srd.itcp.sugar.tools.core.enums.autowired.exception;

import cn.srd.itcp.sugar.tools.core.enums.autowired.EnumAutowired;


/**
 * 匹配到多个要注入实现类的字段时异常，参考 {@link EnumAutowired#autowiredFiledName()}
 *
 * @author wjm
 * @date 2021/9/8 16:07
 */
public class EnumAutowiredMultiMatchedFieldException extends RuntimeException {

    private static final long serialVersionUID = 1568176615358892865L;

    public EnumAutowiredMultiMatchedFieldException() {
    }

    public EnumAutowiredMultiMatchedFieldException(String message) {
        super(message);
    }

    public EnumAutowiredMultiMatchedFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumAutowiredMultiMatchedFieldException(Throwable cause) {
        super(cause);
    }

}
