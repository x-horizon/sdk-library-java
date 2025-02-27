package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.util.Map;

/**
 * @author wjm
 * @since 2024-07-24 14:54
 */
public class JdbcJsonbMappingJavaMapTypeHandler extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<Map<String, Object>> {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Map<String, Object> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.onJackson().toMap(columnValue, Object.class);
    }

}