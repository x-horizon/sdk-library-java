package org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * Mapstruct 属性映射转换器注解，String =&gt; List&lt;String&gt;
 *
 * @author wjm
 * @since 2021-03-11 10:25
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructStringToListString {

}