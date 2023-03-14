package cn.srd.itcp.sugar.orm.mybatis.plus.database.postgresql.handler;

import cn.srd.itcp.sugar.tool.core.Objects;
import com.alibaba.fastjson.JSONArray;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <pre>
 * {@link JSONArray} 与 PostgreSQL 中 JSONB 类型字段的类型映射处理器；
 * 适用于 Java 字段的数据类型为 {@link JSONArray} &lt;==&gt; PostgreSQL 字段的数据类型为 JSONB 的相互转换；
 * </pre>
 *
 * @author wjm
 * @since 2022-08-05
 */
public class JsonArrayMappingJsonbTypeHandler extends BaseTypeHandler<JSONArray> implements JsonbHandler<JSONArray> {

    /**
     * 定义如何把 Java 类型的参数转换为指定的数据库类型
     *
     * @param preparedStatement SQL 预编译对象
     * @param columnIndex       字段索引
     * @param parameter         自定义参数
     * @param jdbcType          JDBC 数据类型
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, JSONArray parameter, JdbcType jdbcType) {
        if (Objects.isNotNull(parameter)) {
            preparedStatement.setObject(columnIndex, convertObjectToJsonb(parameter));
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet  结果集
     * @param columnName 字段名称
     * @return 转换结果
     */
    @Override
    @SneakyThrows
    public JSONArray getNullableResult(ResultSet resultSet, String columnName) {
        return convertJsonbStringToJsonArray(resultSet.getString(columnName));
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet   结果集
     * @param columnIndex 字段索引
     * @return 转换结果
     */
    @SneakyThrows
    @Override
    public JSONArray getNullableResult(ResultSet resultSet, int columnIndex) {
        return convertJsonbStringToJsonArray(resultSet.getString(columnIndex));
    }

    /**
     * 定义通过存储过程获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param callableStatement 存储过程执行对象
     * @param columnIndex       字段索引
     * @return 转换结果
     */
    @SneakyThrows
    @Override
    public JSONArray getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return convertJsonbStringToJsonArray(callableStatement.getString(columnIndex));
    }

}

