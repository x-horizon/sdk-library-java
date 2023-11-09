// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.base.handler;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.text.Strings;
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
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, T parameter, JdbcType jdbcType) {
        preparedStatement.setObject(columnIndex, toJdbcObject(parameter));
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
