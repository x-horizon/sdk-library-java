package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;

import java.util.List;

/**
 * <p>provide a standard specification to explain the mapping relationship between jdbc json type and java list object type.</p>
 * <p>you can refer to the implementations of this class.</p>
 *
 * @param <T> the java object data type
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class AbstractJdbcJsonMappingJavaListObjectTypeHandler<T, J> extends AbstractJdbcJsonTypeHandler<List<T>, J> {

    @Override
    protected boolean isEmptyJsonColumnValue(String columnValue) {
        return Collections.isBlankOrEmptyArrayString(columnValue);
    }

    @Override
    protected List<T> toJavaObjectWhenEmptyJsonColumnValue() {
        return Collections.newArrayList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<T> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.onJackson().toBeans(columnValue, javaType);
    }

}