package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.database.PostgresqlDataType;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonTypeHandler;
import org.postgresql.util.PGobject;

/**
 * the postgresql jdbc jsonb abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class AbstractJdbcJsonbTypeHandler<T> extends AbstractJdbcJsonTypeHandler<T, PGobject> {

    @SneakyThrows
    protected PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        PGobject pgObject = new PGobject();
        pgObject.setType(PostgresqlDataType.JSONB.getValue());
        pgObject.setValue(javaObjectContent);
        return pgObject;
    }

}