package cn.srd.itcp.sugar.mybatis.plus.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <pre>
 * {@link JSONObject} 与 PostgreSQL 中 JSONB 类型字段的类型映射处理器；
 * 适用于 Java 字段的数据类型为 {@link JSONObject}，PostgreSQL 字段的数据类型为 JSONB 的映射；
 * </pre>
 *
 * @author wjm
 * @since 2022-07-16
 */
public class PostgresqlJsonObjectMappingJsonbTypeHandler extends BaseTypeHandler<JSONObject> {

    private static final String POSTGRESQL_TYPE = "jsonb";

    /**
     * 定义设置参数时，如何把Java类型的参数转换为对应的数据库类型
     *
     * @param preparedStatement
     * @param columnIndex
     * @param parameter
     * @param jdbcType
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, JSONObject parameter, JdbcType jdbcType) {
        if (null != parameter) {
            PGobject jsonObject = new PGobject();
            jsonObject.setType(POSTGRESQL_TYPE);
            jsonObject.setValue(JSON.toJSONString(parameter));
            preparedStatement.setObject(columnIndex, jsonObject);
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     *
     * @param resultSet
     * @param columnName
     * @return
     */
    @Override
    @SneakyThrows
    public JSONObject getNullableResult(ResultSet resultSet, String columnName) {
        return JSON.parseObject(resultSet.getString(columnName));
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     *
     * @param resultSet
     * @param columnIndex
     * @return
     */
    @SneakyThrows
    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int columnIndex) {
        return JSON.parseObject(resultSet.getString(columnIndex));
    }

    /**
     * 定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     *
     * @param callableStatement
     * @param columnIndex
     * @return
     */
    @SneakyThrows
    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return JSON.parseObject(callableStatement.getString(columnIndex));
    }

}

