package cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.support;

import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.core.EnableMybatisPlusPostgresqlGeometryInterceptor;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.utils.SQLParser;
import cn.srd.itcp.sugar.tool.constant.SQLPool;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.locationtech.jts.geom.Geometry;

import java.lang.reflect.Field;

/**
 * mybatis-plus 拦截器 - Postgresql Geometry 修改查询语句插件
 * <pre>
 * 该插件的作用参考：{@link PostgresqlGeometryToTextFunctionOnSelectSqlWrapper}；
 * 该插件必须要显式启用了：{@link EnableMybatisPlusPostgresqlGeometryInterceptor} 才会生效；
 * </pre>
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
public class PostgresqlGeometryToTextFunctionOnSelectSqlInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        // 获取相当于 @ResultMap 所有内容
        for (ResultMap resultMap : mappedStatement.getResultMaps()) {
            // 获取与表绑定的持久化模型
            Class<?> modelBoundByTable = resultMap.getType();
            // 获取标记在持久化模型类上的 @TableName 注解
            TableName tableNameAnnotation = modelBoundByTable.getAnnotation(TableName.class);
            if (Objects.isNotNull(tableNameAnnotation)) {
                // 遍历持久化模型所有字段
                Field[] declaredFields = modelBoundByTable.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    // 获取需要替换 sql 的字段
                    PostgresqlGeometryToTextFunctionOnSelectSqlWrapper mark = declaredField.getAnnotation(PostgresqlGeometryToTextFunctionOnSelectSqlWrapper.class);
                    if (Objects.isNotNull(mark) && Objects.equals(Geometry.class, declaredField.getType())) {
                        // 获取字段对应的表列名
                        String columnName = declaredField.getName();
                        // 若标记了 @TableField 注解，使用注解指定的表列名，否则使用持久化模型里的字段名
                        TableField tableField = declaredField.getAnnotation(TableField.class);
                        if (Objects.isNotNull(tableField)) {
                            columnName = tableField.value();
                        }
                        // 原始整条 sql 语句
                        String allSql = boundSql.getSql();
                        // 从原始整条 sql 语句中提取查询结果集 sql 语句出来，如 id,name,location
                        String selectResultSetSql = StringsUtil.subBetween(allSql, SQLPool.SELECT, SQLPool.FROM + StringPool.SPACE + tableNameAnnotation.value());
                        // 将查询结果集 sql 语句替换为加上函数后的语句，如 id,name,ST_ASTEXT(location) AS location
                        String selectResultSetSqlAfterReplace = SQLParser.replaceSelectSQLToGeometryToString(selectResultSetSql, columnName);
                        // 将替换后的结果集语句放回到整条 sql 语句中
                        String allSqlAfterReplace = StringsUtil.replace(allSql, selectResultSetSql, selectResultSetSqlAfterReplace);
                        // 修改原始 sql 语句
                        PluginUtils.mpBoundSql(boundSql).sql(allSqlAfterReplace);
                    }
                }
            }
        }
    }

}
