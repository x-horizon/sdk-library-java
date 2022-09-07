package cn.srd.itcp.sugar.mybatis.plus.handler;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * 数据类型转换：{@link UUID} => {@link String}
 *
 * @author wjm
 * @since 2022-07-12
 */
public class UuidToStringTypeHandler extends BaseTypeHandler<UUID> {

    /**
     * 定义如何把 Java 类型的参数转换为指定的数据库类型
     *
     * @param preparedStatement
     * @param columnIndex
     * @param parameter         Java 类型的参数值
     * @param jdbcType
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, UUID parameter, JdbcType jdbcType) {
        if (null != parameter) {
            preparedStatement.setObject(columnIndex, parameter.toString());
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet
     * @param columnName
     * @return
     */
    @Override
    @SneakyThrows
    public UUID getNullableResult(ResultSet resultSet, String columnName) {
        return UUID.fromString(resultSet.getString(columnName));
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet
     * @param columnIndex
     * @return
     */
    @Override
    public UUID getNullableResult(ResultSet resultSet, int columnIndex) {
        return null;
    }

    /**
     * 定义通过存储过程获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param callableStatement
     * @param columnIndex
     * @return
     */
    @Override
    public UUID getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return null;
    }

}
