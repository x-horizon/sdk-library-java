// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.library.java.orm.mybatis.contract.base.handler.JdbcComplexTypeHandler;
import cn.srd.library.java.contract.constant.database.PostgreSQLDataType;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

import java.util.Set;

/**
 * the jdbc jsonb type handler
 *
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class JdbcJsonbTypeHandler<T> extends JdbcComplexTypeHandler<T> {

    @SneakyThrows
    protected PGobject toPGobject(Object input) {
        PGobject pgObject = new PGobject();
        pgObject.setType(PostgreSQLDataType.JSONB.getValue());
        pgObject.setValue(Converts.withJackson().toString(input));
        return pgObject;
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    protected RunningException whenFailed(String columnName, Set<Class> javaClasses) {
        return new RunningException(Strings.format(
                "{}could not convert the value of column name [{}] to following classes {}, you need to consider add the specified class to replace those automatic classes.",
                ModuleView.ORM_MYBATIS_SYSTEM,
                columnName,
                javaClasses.stream().map(Class::getName).toList()
        ));
    }

}
