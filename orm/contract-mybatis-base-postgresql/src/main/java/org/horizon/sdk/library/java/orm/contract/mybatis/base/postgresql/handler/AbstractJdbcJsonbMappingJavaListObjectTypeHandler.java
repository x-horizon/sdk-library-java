package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;

import java.util.List;

/**
 * the postgresql jdbc jsonb data type and java list object data type mapping relation abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class AbstractJdbcJsonbMappingJavaListObjectTypeHandler<T> extends AbstractJdbcJsonbTypeHandler<List<T>> {

    @Override
    protected boolean isEmptyJsonbColumnValue(String columnValue) {
        return Collections.isBlankOrEmptyArrayString(columnValue);
    }

    @Override
    protected List<T> toJavaObjectWhenEmptyJsonbColumnValue() {
        return Collections.newArrayList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<T> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.onJackson().toBeans(columnValue, javaType);
    }

}