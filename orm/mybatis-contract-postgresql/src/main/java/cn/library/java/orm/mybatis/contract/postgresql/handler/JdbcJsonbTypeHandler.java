// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.library.java.orm.mybatis.contract.handler.JdbcComplexTypeHandler;
import cn.srd.library.java.contract.constant.database.PostgreSQLDataType;
import cn.srd.library.java.tool.convert.all.Converts;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

/**
 * the jdbc jsonb type handler
 *
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class JdbcJsonbTypeHandler<T> extends JdbcComplexTypeHandler<T> {

    @SneakyThrows
    protected PGobject convertObjectToJsonb(Object input) {
        PGobject pgObject = new PGobject();
        pgObject.setType(PostgreSQLDataType.JSONB.getValue());
        pgObject.setValue(Converts.withJackson().toString(input));
        return pgObject;
    }

}
