// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.handler;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingRelationCache;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import io.vavr.control.Try;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author wjm
 * @since 2023-11-06 19:40
 */
public class JdbcJsonbMappingJavaListObjectTypeHandler<T extends NullableObject> extends JdbcJsonbTypeHandler<List<T>> {

    @Override
    protected Object toJdbcObject(List<T> javaObject) {
        return toPGobject(Nil.isNull(javaObject) ? Collections.newImmutableList() : javaObject.stream().filter(NullableObject::isNotNull).toList());
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @SneakyThrows
    @Override
    protected List<T> toJavaObject(ResultSet resultSet, String columnName) {
        String jsonbString = resultSet.getString(columnName);
        if (Collections.isBlankOrEmptyArrayString(jsonbString)) {
            return Collections.newArrayList();
        }
        Set<Class> javaClasses = ColumnJsonbMappingRelationCache.getInstance().getMappingJavaClass(columnName);
        return javaClasses.stream()
                .map(javaType -> Optional.ofNullable(Try.of(() -> Converts.withJackson().toBeans(jsonbString, javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> whenFailed(columnName, javaClasses));
    }

}
