// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * @author xiongjing
 * @since 2023-05-09 10:35
 */
public class JdbcJsonbMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<E> {

    @Override
    protected Object doConvertToJdbcObject(List<E> javaObjects) {
        return Nil.isNull(javaObjects) ?
                Collections.newImmutableList() :
                javaObjects.stream().map(javaObject -> Enums.getFieldValue(javaObject, Integer.class)).toList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<E> doConvertToJavaObject(String jsonbString, Class javaType) {
        return Strings.splitToEnums(Strings.removeHeadTailBracket(jsonbString), javaType);
    }

}
