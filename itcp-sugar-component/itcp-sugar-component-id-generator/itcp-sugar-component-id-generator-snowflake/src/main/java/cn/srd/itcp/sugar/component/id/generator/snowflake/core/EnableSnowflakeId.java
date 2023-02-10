package cn.srd.itcp.sugar.component.id.generator.snowflake.core;

import java.lang.annotation.*;

/**
 * 启用本模块的雪花 id 算法
 *
 * @author wjm
 * @since 2022-08-29 09:04:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableSnowflakeId {

}
