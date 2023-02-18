package cn.srd.itcp.sugar.orm.mybatis.plus.database.postgresql.metadata.support;

import cn.srd.itcp.sugar.orm.mybatis.plus.support.SugarMybatisPlusProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * Conditional For {@link SugarMybatisPlusProperties#enableMetadata} Is True
 *
 * @author wjm
 * @since 2023-02-18 10:14:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Inherited
@ConditionalOnProperty(name = "sugar.orm.mybatis-plus.enableMetadata", havingValue = "true")
public @interface ConditionalOnEnableMetadata {

}
