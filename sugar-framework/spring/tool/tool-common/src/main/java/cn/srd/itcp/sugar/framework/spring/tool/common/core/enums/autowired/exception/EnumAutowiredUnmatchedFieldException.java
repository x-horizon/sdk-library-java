package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.EnumAutowired;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 无法找到要注入实现类的字段时异常，参考 {@link EnumAutowired#autowiredFiledName()}
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@StandardException
public class EnumAutowiredUnmatchedFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1191863371222442507L;

}
