package cn.srd.itcp.sugar.convert.mapstruct.util;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapstruct 属性映射转换器注解，Byte[] => Hex String
 *
 * @author wjm
 * @date 2022-07-06
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MapstructByteArrayToHexString {
}