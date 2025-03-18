package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaMapTypeHandler;

/**
 * @author wjm
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaMapTypeHandler extends AbstractJdbcJsonMappingJavaMapTypeHandler<String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}