// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.mybatis.postgresql.cache;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.mybatis.base.cache.ColumnMappingJavaTypeCache;
import cn.srd.library.java.orm.contract.mybatis.postgresql.handler.AbstractJdbcJsonbTypeHandler;
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