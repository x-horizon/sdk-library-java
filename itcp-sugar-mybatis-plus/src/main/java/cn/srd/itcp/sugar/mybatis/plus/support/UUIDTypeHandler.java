package cn.srd.itcp.sugar.mybatis.plus.support;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * 数据类型转换：String => UUID
 *
 * @author wjm
 * @date 2020/12/25 15:36
 */
public class UUIDTypeHandler extends BaseTypeHandler<String> {

    /**
     * 定义设置参数时，如何把Java类型的参数转换为对应的数据库类型
     *
     * @param ps
     * @param columnIndex
     * @param parameter   Java 类型的参数值
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, String parameter, JdbcType jdbcType) throws SQLException {
        if (null != parameter) {
            // String 转为 jdbcType 的 UUID 类型
            ps.setObject(columnIndex, UUID.fromString(parameter));
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
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
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
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
     * 定义调用存储过程后，如何把数据库类型转换为对应的Java类型
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
