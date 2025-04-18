package org.horizon.sdk.library.java.orm.contract.mybatis.base.cache;

import org.apache.ibatis.type.TypeHandler;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonTypeHandler;

/**
 * the postgresql jdbc json type and java type mapping cache.
 *
 * @author wjm
 * @since 2023-11-07 15:57
 */
public abstract class ColumnJsonMappingJavaTypeCache extends ColumnMappingJavaTypeCache {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Class<? extends TypeHandler> getAbstractTypeHandler() {
        return AbstractJdbcJsonTypeHandler.class;
    }

}