package org.horizon.library.java.orm.contract.mybatis.postgresql.cache;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.orm.contract.mybatis.base.cache.ColumnMappingJavaTypeCache;
import org.horizon.library.java.orm.contract.mybatis.postgresql.handler.AbstractJdbcJsonbTypeHandler;
import org.apache.ibatis.type.TypeHandler;

/**
 * the postgresql jdbc jsonb type and java type mapping cache
 *
 * @author wjm
 * @since 2023-11-07 15:57
 */
public abstract class ColumnJsonbMappingJavaTypeCache extends ColumnMappingJavaTypeCache {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Class<? extends TypeHandler> getAbstractTypeHandler() {
        return AbstractJdbcJsonbTypeHandler.class;
    }

}