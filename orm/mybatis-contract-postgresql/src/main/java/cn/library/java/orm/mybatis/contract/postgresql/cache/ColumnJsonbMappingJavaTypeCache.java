// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.cache;

import cn.library.java.orm.mybatis.contract.base.cache.ColumnMappingJavaTypeCache;
import cn.library.java.orm.mybatis.contract.postgresql.handler.AbstractJdbcJsonbTypeHandler;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
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
