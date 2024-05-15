// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.mybatis.postgresql.handler;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;

/**
 * the postgresql jdbc jsonb data type and java object data type mapping relation abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class AbstractJdbcJsonbMappingJavaObjectTypeHandler<T> extends AbstractJdbcJsonbTypeHandler<T> {

    @Override
    protected boolean isEmptyJsonbColumnValue(String columnValue) {
        return Collections.isBlankOrEmptyMapString(columnValue);
    }

    @Override
    protected T toJavaObjectWhenEmptyJsonbColumnValue() {
        return null;
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected T doConvertToJavaObject(String columnValue, Class javaType) {
        return (T) Converts.withJackson().toBean(columnValue, javaType);
    }

}