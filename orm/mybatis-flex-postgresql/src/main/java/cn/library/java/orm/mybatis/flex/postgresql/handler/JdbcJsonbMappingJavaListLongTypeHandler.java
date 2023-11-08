// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.handler;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaListObjectAbstractTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingRelationCache;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;

import java.util.Set;

/**
 * @author wjm
 * @since 2023-06-14 09:20
 */
public class JdbcJsonbMappingJavaListLongTypeHandler extends JdbcJsonbMappingJavaListObjectAbstractTypeHandler<Long> {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Set<Class> getMappingJavaTypes(String columnName) {
        return ColumnJsonbMappingRelationCache.getInstance().getMappingJavaTypes(columnName);
    }

}
