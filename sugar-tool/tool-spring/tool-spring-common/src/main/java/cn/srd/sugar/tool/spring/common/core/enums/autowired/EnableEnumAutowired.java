package cn.srd.sugar.tool.spring.common.core.enums.autowired;

import org.springframework.context.annotation.Import;

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
@Import(EnumAutowiredSupport.class)
public @interface EnableEnumAutowired {

}
