package org.horizon.library.java.orm.contract.mybatis.base.handler;

import org.horizon.library.java.contract.constant.module.ModuleView;
import org.horizon.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.library.java.tool.lang.text.Strings;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * the jdbc complex abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class AbstractJdbcComplexTypeHandler<T> extends BaseTypeHandler<T> {

    /**
     * convert java object to jdbc object
     *
     * @param javaObject java object
     * @return jdbc object
     * @apiNote the java object instance will never be null
     */
    protected abstract Object toJdbcObject(T javaObject);

    /**
     * convert column value to java object
     *
     * @param columnName  the column name
     * @param columnValue the column value
     * @return java object
     */
    protected abstract T toJavaObject(String columnName, String columnValue);

    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, T javaObject, JdbcType jdbcType) {
        preparedStatement.setObject(columnIndex, toJdbcObject(javaObject));
    }

    @SneakyThrows
    @Override
    public T getNullableResult(ResultSet resultSet, String columnName) {
        return toJavaObject(columnName, resultSet.getString(columnName));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int columnIndex) {
        throw new UnsupportedException(Strings.format("{}get result set through column index is not supported!", ModuleView.ORM_MYBATIS_SYSTEM));
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int columnIndex) {
        throw new UnsupportedException(Strings.format("{}get result set through store procedure is not supported!", ModuleView.ORM_MYBATIS_SYSTEM));
    }

}