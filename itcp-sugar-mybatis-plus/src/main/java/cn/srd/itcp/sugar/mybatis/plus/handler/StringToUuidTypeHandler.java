package cn.srd.itcp.sugar.mybatis.plus.handler;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * 数据类型转换：{@link String} => {@link UUID}
 *
 * @author wjm
 * @since 2020/12/25 15:36
 */
public class StringToUuidTypeHandler extends BaseTypeHandler<String> {

    /**
     * 定义如何把 Java 类型的参数转换为指定的数据库类型
     *
     * @param ps
     * @param columnIndex
     * @param parameter   Java 类型的参数值
     * @param jdbcType
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, String parameter, JdbcType jdbcType) {
        if (null != parameter) {
            // String 转为 jdbcType 的 UUID 类型
            ps.setObject(columnIndex, UUID.fromString(parameter));
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param rs
     * @param columnName
     * @return
     */
    @Override
    @SneakyThrows
    public String getNullableResult(ResultSet rs, String columnName) {
        return rs.getString(columnName);
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param rs
     * @param columnIndex
     * @return
     */
    @Override
    @SneakyThrows
    public String getNullableResult(ResultSet rs, int columnIndex) {
        return rs.getString(columnIndex);
    }

    /**
     * 定义通过存储过程获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param cs
     * @param columnIndex
     * @return
     */
    @Override
    @SneakyThrows
    public String getNullableResult(CallableStatement cs, int columnIndex) {
        return cs.getString(columnIndex);
    }

}
