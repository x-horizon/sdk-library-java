package org.horizon.library.java.tool.convert.mapstruct.plus.support;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * 解析最高位
 *
 * @author xiongjing
 * @since 2022-11-14 09:38
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface ParseHighestOneBit {

}