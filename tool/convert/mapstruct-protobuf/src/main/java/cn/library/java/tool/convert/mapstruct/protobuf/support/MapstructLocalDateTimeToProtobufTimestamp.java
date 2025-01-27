package cn.library.java.tool.convert.mapstruct.protobuf.support;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2024-07-09 18:56
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructLocalDateTimeToProtobufTimestamp {

}