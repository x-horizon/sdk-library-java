package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;

/**
 * <p>provide a standard specification to explain the mapping relationship between jdbc json type and java string type.</p>
 * <p>you can refer to the implementations of this class.</p>
 *
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2023-11-10 14:35
 */
public abstract class AbstractJdbcJsonMappingJavaStringTypeHandler<J> extends AbstractJdbcJsonMappingJavaObjectTypeHandler<String, J> {

    @Override
    protected String toJdbcObjectContent(String javaObject) {
        return javaObject;
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected String doConvertToJavaObject(String columnValue, Class javaType) {
        return columnValue;
    }

}