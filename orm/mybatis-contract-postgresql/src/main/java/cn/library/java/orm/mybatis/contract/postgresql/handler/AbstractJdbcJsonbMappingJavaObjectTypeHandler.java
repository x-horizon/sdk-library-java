// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;

import java.util.Map;

/**
 * the abstract definition of postgresql jdbc jsonb data type and java object data type mapping relation
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public abstract class AbstractJdbcJsonbMappingJavaObjectTypeHandler<T> extends AbstractJdbcJsonbTypeHandler<T> {

    @Override
    protected boolean isEmptyJsonbContent(String content) {
        return Collections.isBlankOrEmptyMapString(content);
    }

    @Override
    protected T toJavaObjectWhenEmptyJsonbContent() {
        return null;
    }

    @Override
    protected Object doConvertToJdbcObject(T javaObject) {
        return Nil.isNull(javaObject) ? Map.of() : javaObject;
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected T doConvertToJavaObject(String content, Class javaType) {
        return (T) Converts.withJackson().toBean(content, javaType);
    }

}
