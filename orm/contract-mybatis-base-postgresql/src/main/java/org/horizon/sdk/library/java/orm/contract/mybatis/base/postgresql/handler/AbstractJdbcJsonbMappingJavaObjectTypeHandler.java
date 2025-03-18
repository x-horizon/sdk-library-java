package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;

/**
 * the postgresql jdbc jsonb data type and java object data type mapping relation abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class AbstractJdbcJsonbMappingJavaObjectTypeHandler<T> extends AbstractJdbcJsonbTypeHandler<T> {

    @Override
    protected boolean isEmptyJsonbColumnValue(String columnValue) {
        return Collections.isBlankOrEmptyMapString(columnValue);
    }

    @Override
    protected T toJavaObjectWhenEmptyJsonbColumnValue() {
        return null;
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected T doConvertToJavaObject(String columnValue, Class javaType) {
        return (T) Converts.onJackson().toBean(columnValue, javaType);
    }

}