// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import io.vavr.control.Try;

import java.util.Set;

/**
 * the jdbc jsonb object type handler
 *
 * @author wjm
 * @since 2023-11-06 19:18
 */
public abstract class JdbcJsonbObjectTypeHandler<T> extends JdbcJsonbTypeHandler<T> {

    protected Set<Class<T>> getJavaType(String columnName) {
        return Collections.newHashSet();
    }

    protected T convertJsonbStringToObject(String jsonbString, Set<Class<T>> javaClasses) {
        if (Collections.isBlankOrEmptyMapString(jsonbString)) {
            return null;
        }
        T output;
        for (Class<T> javaClass : javaClasses) {
            output = Try.of(() -> Converts.withJackson().toBean(jsonbString, javaClass)).getOrNull();
            if (Nil.isNotNull(output)) {
                return output;
            }
        }
        throw new RunningException("cdcdf");
    }

}
