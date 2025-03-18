package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.cache;

import com.mybatisflex.annotation.Column;

import java.lang.annotation.Annotation;

/**
 * the mybatis flex postgresql jdbc json type and java type mapping cache.
 *
 * @author wjm
 * @since 2023-11-07 15:57
 */
public class ColumnJsonMappingJavaTypeCache extends org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.cache.ColumnJsonMappingJavaTypeCache {

    @Override
    protected Class<? extends Annotation> getTypeHandlerLocatedAnnotation() {
        return Column.class;
    }

}