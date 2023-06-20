package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.handler;

import cn.srd.itcp.sugar.tool.core.object.Objects;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * is the same as {@link ListObjectMappingJsonbTypeHandler} and the generic is {@link Long}
 *
 * @author wjm
 * @since 2023-06-14 09:20:17
 */
public class ListLongMappingJsonbTypeHandler extends BaseTypeHandler<List<Long>> implements JsonbHandler<Long> {

    @Override
    public Class<Long> getTargetClass() {
        return Long.class;
    }

    /**
     * 定义如何把 Java 类型的参数转换为指定的数据库类型
     *
     * @param preparedStatement SQL 预编译对象
     * @param columnIndex       字段索引
     * @param parameters        自定义参数
     * @param jdbcType          JDBC 数据类型
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, List<Long> parameters, JdbcType jdbcType) {
        if (Objects.isNotNull(parameters)) {
            preparedStatement.setObject(columnIndex, convertObjectToJsonb(parameters));
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
    public List<Long> getNullableResult(ResultSet resultSet, String columnName) {
        return convertJsonbStringToList(resultSet.getString(columnName));
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
    public List<Long> getNullableResult(ResultSet resultSet, int columnIndex) {
        return convertJsonbStringToList(resultSet.getString(columnIndex));
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
    public List<Long> getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return convertJsonbStringToList(callableStatement.getString(columnIndex));
    }

}

