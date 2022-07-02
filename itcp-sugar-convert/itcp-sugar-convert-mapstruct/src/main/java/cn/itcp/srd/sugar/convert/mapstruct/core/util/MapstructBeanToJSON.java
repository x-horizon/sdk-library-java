package cn.itcp.srd.sugar.convert.mapstruct.core.util;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapstruct 属性映射转换器注解，Bean => JSON，使用驼峰原则命名
 *
 * @author wjm
 * @date 2021/9/23 14:25
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MapstructBeanToJSON {
}