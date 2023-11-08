// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.cache;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbAbstractTypeHandler;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.orm.mybatis.flex.base.cache.ColumnMappingRelationCache;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author wjm
 * @since 2023-11-07 15:57
 */
public class ColumnJsonbMappingRelationCache extends ColumnMappingRelationCache {

    private static final class SingleTonHolder {

        private static final ColumnJsonbMappingRelationCache INSTANCE = new ColumnJsonbMappingRelationCache();

    }

    public static ColumnJsonbMappingRelationCache getInstance() {
        return ColumnJsonbMappingRelationCache.SingleTonHolder.INSTANCE;
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Class<? extends TypeHandler> getJdbcAbstractTypeHandler() {
        return JdbcJsonbAbstractTypeHandler.class;
    }

}
