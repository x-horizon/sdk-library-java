// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import io.vavr.control.Try;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * the abstract definition of postgresql jdbc jsonb data type and java list object data type mapping relation
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-07 20:58
 */
public abstract class JdbcJsonbMappingJavaListObjectTypeHandler<T> extends JdbcJsonbTypeHandler<List<T>> {

    @Override
    protected Object toJdbcObject(List<T> javaObject) {
        return toPGobject(Nil.isNull(javaObject) ? Collections.newImmutableList() : javaObject);
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @SneakyThrows
    @Override
    protected List<T> toJavaObject(ResultSet resultSet, String columnName) {
        String jsonbString = resultSet.getString(columnName);
        if (Collections.isBlankOrEmptyArrayString(jsonbString)) {
            return Collections.newArrayList();
        }
        Set<Class> javaClasses = getMappingJavaClass(columnName);
        return javaClasses.stream()
                .map(javaType -> Optional.ofNullable(Try.of(() -> Converts.withJackson().toBeans(jsonbString, javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> whenFailed(columnName, javaClasses));
    }

}
