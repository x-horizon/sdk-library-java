package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.EnumAutowired;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 匹配到多个要注入实现类的字段时异常，参考 {@link EnumAutowired#autowiredFiledName()}
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@StandardException
public class EnumAutowiredMultiMatchedFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1568176615358892865L;

}
