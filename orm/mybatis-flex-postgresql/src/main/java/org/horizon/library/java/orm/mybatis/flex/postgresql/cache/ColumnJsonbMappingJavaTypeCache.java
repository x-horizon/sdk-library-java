package org.horizon.library.java.orm.mybatis.flex.postgresql.cache;

import com.mybatisflex.annotation.Column;

import java.lang.annotation.Annotation;

/**
 * the mybatis flex postgresql jdbc jsonb type and java type mapping cache
 *
 * @author wjm
 * @since 2023-11-07 15:57
 */
public class ColumnJsonbMappingJavaTypeCache extends org.horizon.library.java.orm.contract.mybatis.postgresql.cache.ColumnJsonbMappingJavaTypeCache {

    @Override
    protected Class<? extends Annotation> getTypeHandlerLocatedAnnotation() {
        return Column.class;
    }

}