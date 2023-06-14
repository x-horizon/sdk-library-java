package cn.srd.itcp.sugar.orm.mybatis.plus.common.handler;

import cn.srd.itcp.sugar.tool.core.object.Objects;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * 数据类型转换：{@link String} =&gt; {@link UUID}
 *
 * @author wjm
 * @since 2020/12/25 15:36
 */
public class StringToUuidTypeHandler extends BaseTypeHandler<String> {

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
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, String parameter, JdbcType jdbcType) {
        // String 转为 jdbcType 的 UUID 类型
        preparedStatement.setObject(columnIndex, Objects.isBlank(parameter) ? null : UUID.fromString(parameter));
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
    public String getNullableResult(ResultSet resultSet, String columnName) {
        return resultSet.getString(columnName);
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet   结果集
     * @param columnIndex 字段索引
     * @return 转换结果
     */
    @Override
    @SneakyThrows
    public String getNullableResult(ResultSet resultSet, int columnIndex) {
        return resultSet.getString(columnIndex);
    }

    /**
     * 定义通过存储过程获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param callableStatement 存储过程执行对象
     * @param columnIndex       字段索引
     * @return 转换结果
     */
    @Override
    @SneakyThrows
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return callableStatement.getString(columnIndex);
    }

}
