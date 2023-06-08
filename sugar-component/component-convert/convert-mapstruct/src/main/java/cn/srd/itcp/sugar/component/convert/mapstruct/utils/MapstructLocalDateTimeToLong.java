package cn.srd.itcp.sugar.component.convert.mapstruct.utils;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * Mapstruct 属性映射转换器注解，LocalDateTime =&gt; Long
 *
 * @author wjm
 * @since 2022-07-18 09:59:42
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructLocalDateTimeToLong {
}