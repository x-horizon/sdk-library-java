package cn.srd.library.java.tool.convert.mapstruct.utils;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * 解析最高位
 *
 * @author xiongjing
 * @since 2022-11-14 09:38:12
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface ParseHighestOneBit {
}