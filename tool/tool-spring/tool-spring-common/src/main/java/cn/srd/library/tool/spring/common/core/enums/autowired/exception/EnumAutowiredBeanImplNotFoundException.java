package cn.srd.library.tool.spring.common.core.enums.autowired.exception;

import cn.srd.library.tool.spring.common.core.enums.autowired.EnumAutowired;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 根据 {@link EnumAutowired#autowiredBeanClass()} 无法找到其实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@StandardException
public class EnumAutowiredBeanImplNotFoundException extends RuntimeException {

    @Serial private static final long serialVersionUID = -2515941124997405689L;

}
