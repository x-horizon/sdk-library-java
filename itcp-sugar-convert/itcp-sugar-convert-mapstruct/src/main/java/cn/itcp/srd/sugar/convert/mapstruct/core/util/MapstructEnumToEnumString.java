package cn.itcp.srd.sugar.convert.mapstruct.core.util;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapstruct 属性映射转换器注解，Enum => Enum 的字符串字段值
 *
 * @author wjm
 * @date 2021/3/11 10:25
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MapstructEnumToEnumString {
}