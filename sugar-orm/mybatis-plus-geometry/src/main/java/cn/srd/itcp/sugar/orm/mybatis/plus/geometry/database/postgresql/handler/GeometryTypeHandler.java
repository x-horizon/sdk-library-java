package cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.handler;

import cn.srd.itcp.sugar.component.geometry.core.GeometryUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBWriter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 数据类型转换：{@link Geometry} =&gt; PostgreSQL Geometry
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
public class GeometryTypeHandler extends BaseTypeHandler<Geometry> {

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
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, Geometry parameter, JdbcType jdbcType) {
        preparedStatement.setBytes(columnIndex, new WKBWriter(GeometryUtil.getDimension(parameter), true).write(parameter));
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
    public Geometry getNullableResult(ResultSet resultSet, String columnName) {
        return GeometryUtil.toGeometry(resultSet.getString(columnName));
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
    public Geometry getNullableResult(ResultSet resultSet, int columnIndex) {
        return GeometryUtil.toGeometry(resultSet.getString(columnIndex));
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
    public Geometry getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return GeometryUtil.toGeometry(callableStatement.getString(columnIndex));
    }

}
