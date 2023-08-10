package cn.srd.sugar.orm.mybatis.plus.support;

import cn.srd.sugar.orm.mybatis.plus.config.properties.OrmMybatisPlusProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * Conditional For {@link OrmMybatisPlusProperties#enableMetadata} Is True
 *
 * @author wjm
 * @since 2023-02-18 10:14:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Inherited
@ConditionalOnEnablePostgresql
@ConditionalOnProperty(name = "sugar.orm.mybatis-plus.enableMetadata", havingValue = "true")
public @interface ConditionalOnEnablePostgresqlMetadata {

}
