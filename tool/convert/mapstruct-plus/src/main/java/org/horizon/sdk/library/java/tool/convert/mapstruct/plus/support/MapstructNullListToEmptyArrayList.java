package org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * Mapstruct 属性映射转换器注解，null List =&gt; Empty ArrayList
 *
 * @author wjm
 * @since 2022-07-20 11:37
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructNullListToEmptyArrayList {

}