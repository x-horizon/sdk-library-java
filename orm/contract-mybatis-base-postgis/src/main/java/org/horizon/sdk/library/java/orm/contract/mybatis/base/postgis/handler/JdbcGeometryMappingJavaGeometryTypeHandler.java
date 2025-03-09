package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgis.handler;

import lombok.SneakyThrows;
import net.postgis.jdbc.jts.JtsGeometry;
import org.apache.ibatis.type.JdbcType;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcComplexTypeHandler;
import org.horizon.sdk.library.java.tool.geometry.Geometries;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBWriter;

import java.sql.PreparedStatement;

/**
 * @author wjm
 * @since 2023-03-14 15:39
 */
public class JdbcGeometryMappingJavaGeometryTypeHandler extends AbstractJdbcComplexTypeHandler<Geometry> {

    @Override
    protected byte[] toJdbcObject(Geometry javaObject) {
        return new WKBWriter(Geometries.getDimension(javaObject), true).write(javaObject);
    }

    @SneakyThrows
    @Override
    protected Geometry toJavaObject(String columnName, String columnValue) {
        return Nil.isBlank(columnValue) ? null : JtsGeometry.geomFromString(columnValue);
    }

    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, Geometry javaObject, JdbcType jdbcType) {
        if (Nil.isNotNull(javaObject)) {
            preparedStatement.setBytes(columnIndex, toJdbcObject(javaObject));
        }
    }

}