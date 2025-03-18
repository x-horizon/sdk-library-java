package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaMapTypeHandler;
import org.postgresql.util.PGobject;

/**
 * @author wjm
 * @since 2024-07-24 14:54
 */
public class JdbcJsonMappingJavaMapTypeHandler extends AbstractJdbcJsonMappingJavaMapTypeHandler<PGobject> implements PgObjectJsonConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PgObjectJsonConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}