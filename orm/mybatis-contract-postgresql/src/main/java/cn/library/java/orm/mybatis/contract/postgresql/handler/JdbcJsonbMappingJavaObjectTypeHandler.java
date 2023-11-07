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
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * the abstract definition of postgresql jdbc jsonb data type and java object data type mapping relation
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public abstract class JdbcJsonbMappingJavaObjectTypeHandler<T> extends JdbcJsonbTypeHandler<T> {

    @Override
    protected Object toJdbcObject(T javaObject) {
        return toPGobject(Nil.isNull(javaObject) ? Map.of() : javaObject);
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @SneakyThrows
    @Override
    protected T toJavaObject(ResultSet resultSet, String columnName) {
        String jsonbString = resultSet.getString(columnName);
        if (Collections.isBlankOrEmptyMapString(jsonbString)) {
            return null;
        }
        Set<Class> javaClasses = getMappingJavaClass(columnName);
        return javaClasses.stream()
                .map(javaType -> Optional.ofNullable((T) Try.of(() -> Converts.withJackson().toBean(jsonbString, javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> whenFailed(columnName, javaClasses));
    }

}
