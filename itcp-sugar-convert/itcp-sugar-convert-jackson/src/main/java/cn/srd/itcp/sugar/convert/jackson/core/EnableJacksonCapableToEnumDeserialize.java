package cn.srd.itcp.sugar.convert.jackson.core;

import cn.srd.itcp.sugar.convert.jackson.support.JacksonCapableToEnumDeserializer;

import java.lang.annotation.*;

/**
 * 启用 {@link JacksonCapableToEnumDeserializer} 的功能
 *
 * @author wjm
 * @date 2022-07-19 20:25:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableJacksonCapableToEnumDeserialize {

}
