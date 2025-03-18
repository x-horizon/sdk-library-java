package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.util.Map;

/**
 * <p>provide a standard specification to explain the mapping relationship between jdbc json type and java map type.</p>
 * <p>you can refer to the implementations of this class.</p>
 *
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2024-07-24 14:54
 */
public abstract class AbstractJdbcJsonMappingJavaMapTypeHandler<J> extends AbstractJdbcJsonMappingJavaObjectTypeHandler<Map<String, Object>, J> {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Map<String, Object> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.onJackson().toMap(columnValue, Object.class);
    }

}