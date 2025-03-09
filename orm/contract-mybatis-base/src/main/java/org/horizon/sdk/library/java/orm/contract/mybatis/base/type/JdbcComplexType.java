package org.horizon.sdk.library.java.orm.contract.mybatis.base.type;

import lombok.Getter;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.cache.ColumnMappingJavaTypeCache;
import org.horizon.sdk.library.java.tool.enums.EnumAutowired;

/**
 * the jdbc complex type
 *
 * @author wjm
 * @since 2023-11-08 17:07
 */
@Getter
@EnumAutowired(rootClasses = ColumnMappingJavaTypeCache.class, allowNull = true)
public enum JdbcComplexType {

    JSON,

    ;

    /**
     * the complex database column data type and java type mapping cache
     */
    private ColumnMappingJavaTypeCache columnMappingJavaTypeCache;

}