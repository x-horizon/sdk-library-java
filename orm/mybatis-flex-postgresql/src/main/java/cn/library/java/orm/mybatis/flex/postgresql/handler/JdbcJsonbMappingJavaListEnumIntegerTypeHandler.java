// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.handler;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaListObjectTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingRelationCache;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.control.Try;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author xiongjing
 * @since 2023-05-09 10:35
 */
public class JdbcJsonbMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>> extends JdbcJsonbMappingJavaListObjectTypeHandler<E> {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Set<Class> getMappingJavaClass(String columnName) {
        return ColumnJsonbMappingRelationCache.getInstance().getMappingJavaClass(columnName);
    }

    @Override
    protected Object toJdbcObject(List<E> javaObject) {
        return toPGobject(Nil.isNull(javaObject) ?
                Collections.newImmutableList() :
                javaObject.stream().map(item -> Enums.getFieldValue(item, Integer.class)).toList());
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @SneakyThrows
    @Override
    protected List<E> toJavaObject(ResultSet resultSet, String columnName) {
        String jsonbString = resultSet.getString(columnName);
        if (Collections.isBlankOrEmptyArrayString(jsonbString)) {
            return Collections.newArrayList();
        }
        Set<Class> javaClasses = getMappingJavaClass(columnName);
        return javaClasses.stream()
                .map(javaType -> Optional.ofNullable(Try.of(() -> Strings.splitToEnums(Strings.removeHeadTailBracket(jsonbString), javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> whenFailed(columnName, javaClasses));
    }

}
