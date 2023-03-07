package cn.srd.itcp.sugar.component.convert.mapstruct.utils;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapstruct 属性映射转换器注解，LocalDateTime =&gt; Long
 *
 * @author wjm
 * @since 2022-07-18 09:59:42
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MapstructLocalDateTimeToLong {
}