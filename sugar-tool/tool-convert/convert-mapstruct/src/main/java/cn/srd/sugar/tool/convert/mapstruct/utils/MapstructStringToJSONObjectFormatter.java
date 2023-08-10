package cn.srd.sugar.tool.convert.mapstruct.utils;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * MapStruct 属性映射转换器注解，String =&gt; {@link com.alibaba.fastjson.JSONObject JSONObject}
 *
 * @author wjm
 * @since 2021/3/11 10:25
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructStringToJSONObjectFormatter {
}