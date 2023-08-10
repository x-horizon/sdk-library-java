package cn.srd.sugar.tool.spring.common.core.enums.autowired.exception;

import cn.srd.sugar.tool.spring.common.core.enums.autowired.EnumAutowired;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 根据 {@link EnumAutowired#findBeanNamesMayAutowiredRule()} 无法找到可能要注入的实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@StandardException
public class EnumAutowiredMayAutowiredBeansNotFoundException extends RuntimeException {

    @Serial private static final long serialVersionUID = 5574291562911269061L;

}
