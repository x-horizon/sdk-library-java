// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;

import java.util.List;

/**
 * the postgresql jdbc jsonb data type and java list object data type mapping relation abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class AbstractJdbcJsonbMappingJavaListObjectTypeHandler<T> extends AbstractJdbcJsonbTypeHandler<List<T>> {

    @Override
    protected boolean isEmptyJsonbColumnValue(String columnValue) {
        return Collections.isBlankOrEmptyArrayString(columnValue);
    }

    @Override
    protected List<T> toJavaObjectWhenEmptyJsonbColumnValue() {
        return Collections.newArrayList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<T> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.withJackson().toBeans(columnValue, javaType);
    }

}
