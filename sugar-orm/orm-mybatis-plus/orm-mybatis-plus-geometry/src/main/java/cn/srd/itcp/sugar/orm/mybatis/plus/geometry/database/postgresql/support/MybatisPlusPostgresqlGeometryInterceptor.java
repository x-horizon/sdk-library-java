package cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.support;

import cn.srd.itcp.sugar.orm.mybatis.plus.common.interceptor.MybatisPlusInnerInterceptorsConfigurer;
import cn.srd.itcp.sugar.orm.mybatis.plus.common.interceptor.MybatisPlusInterceptors;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * mybatis-plus 拦截器 - Postgresql Geometry 相关插件
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class MybatisPlusPostgresqlGeometryInterceptor implements MybatisPlusInterceptors {

    public void addInterceptor() {
        MybatisPlusInnerInterceptorsConfigurer.set(new PostgresqlGeometryToTextFunctionOnSelectSqlInterceptor());
    }

}
