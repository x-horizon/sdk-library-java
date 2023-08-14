package cn.srd.library.java.tool.spring.common.core.enums.autowired.exception;

import cn.srd.library.java.tool.spring.common.core.enums.autowired.EnumAutowired;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 根据 {@link EnumAutowired#findBeanNameToAutowiredRule()} 无法找到最终要注入的实现类时异常
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@StandardException
public class EnumAutowiredFinallyAutowiredBeanNotFoundException extends RuntimeException {

    @Serial private static final long serialVersionUID = 6283571063586018310L;

}
