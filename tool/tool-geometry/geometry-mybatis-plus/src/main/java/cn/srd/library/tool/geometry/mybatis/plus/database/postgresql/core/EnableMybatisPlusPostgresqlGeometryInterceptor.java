package cn.srd.library.tool.geometry.mybatis.plus.database.postgresql.core;

import cn.srd.library.tool.geometry.mybatis.plus.database.postgresql.support.MybatisPlusPostgresqlGeometryInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link MybatisPlusPostgresqlGeometryInterceptor}
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MybatisPlusPostgresqlGeometryInterceptor.class)
public @interface EnableMybatisPlusPostgresqlGeometryInterceptor {

}
