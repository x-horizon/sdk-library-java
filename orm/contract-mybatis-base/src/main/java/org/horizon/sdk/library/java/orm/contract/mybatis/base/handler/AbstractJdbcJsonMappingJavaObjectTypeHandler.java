package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;

/**
 * <p>provide a standard specification to explain the mapping relationship between jdbc json type and java object type.</p>
 * <p>you can refer to the implementations of this class.</p>
 *
 * @param <T> the java object data type
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class AbstractJdbcJsonMappingJavaObjectTypeHandler<T, J> extends AbstractJdbcJsonTypeHandler<T, J> {

    @Override
    protected boolean isEmptyJsonColumnValue(String columnValue) {
        return Collections.isBlankOrEmptyMapString(columnValue);
    }

    @Override
    protected T toJavaObjectWhenEmptyJsonColumnValue() {
        return null;
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected T doConvertToJavaObject(String columnValue, Class javaType) {
        return (T) Converts.onJackson().toBean(columnValue, javaType);
    }

}