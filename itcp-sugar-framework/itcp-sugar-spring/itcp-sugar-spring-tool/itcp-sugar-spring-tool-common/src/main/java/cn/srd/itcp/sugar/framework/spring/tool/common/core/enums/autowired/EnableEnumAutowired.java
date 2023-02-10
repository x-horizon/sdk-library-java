package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired;

import java.lang.annotation.*;

/**
 * 必须标记了该注解，才启用 {@link EnumAutowired}
 *
 * @author wjm
 * @since 2021-07-14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableEnumAutowired {

}
