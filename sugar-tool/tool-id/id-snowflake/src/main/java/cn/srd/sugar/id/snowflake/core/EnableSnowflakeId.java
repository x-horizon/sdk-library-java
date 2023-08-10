package cn.srd.sugar.id.snowflake.core;

import cn.srd.sugar.id.snowflake.support.ComponentSnowflakeIdAutoConfiguration;
import org.springframework.context.annotation.Import;

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
@Import({ComponentSnowflakeIdAutoConfiguration.class})
public @interface EnableSnowflakeId {

}
