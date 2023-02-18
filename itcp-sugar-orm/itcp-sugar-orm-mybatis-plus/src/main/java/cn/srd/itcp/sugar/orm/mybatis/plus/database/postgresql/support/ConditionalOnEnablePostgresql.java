package cn.srd.itcp.sugar.orm.mybatis.plus.database.postgresql.support;

import cn.srd.itcp.sugar.orm.mybatis.plus.support.SugarMybatisPlusProperties;
import cn.srd.itcp.sugar.tool.constant.DatabaseType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * Conditional For {@link SugarMybatisPlusProperties#database} Is {@link DatabaseType#POSTGRE_SQL}
 *
 * @author wjm
 * @since 2023-02-18 10:14:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Inherited
@ConditionalOnProperty(name = "sugar.orm.mybatis-plus.database", havingValue = DatabaseType.POSTGRE_SQL)
public @interface ConditionalOnEnablePostgresql {

}
