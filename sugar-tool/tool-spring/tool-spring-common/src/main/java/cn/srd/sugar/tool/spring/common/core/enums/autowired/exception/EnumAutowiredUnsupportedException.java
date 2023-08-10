package cn.srd.sugar.tool.spring.common.core.enums.autowired.exception;

import cn.srd.sugar.tool.spring.common.core.enums.autowired.EnumAutowired;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 不支持被 {@link EnumAutowired} 标记的异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@StandardException
public class EnumAutowiredUnsupportedException extends RuntimeException {

    @Serial private static final long serialVersionUID = 3787765610600778202L;

}
