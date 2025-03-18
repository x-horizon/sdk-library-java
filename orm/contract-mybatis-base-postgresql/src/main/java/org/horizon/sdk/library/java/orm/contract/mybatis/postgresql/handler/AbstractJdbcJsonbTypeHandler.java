package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.database.PostgresqlDataType;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonTypeHandler;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
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
    @Override
    protected PGobject toJdbcObject(T javaObject) {
        return toPostgresqlObject(Converts.onJackson().toString(doConvertToJdbcObject(javaObject)));
    }

    /**
     * convert to postgresql object
     *
     * @param content the content
     * @return postgresql object
     */
    @SneakyThrows
    protected PGobject toPostgresqlObject(String content) {
        PGobject pgObject = new PGobject();
        pgObject.setType(PostgresqlDataType.JSONB.getValue());
        pgObject.setValue(content);
        return pgObject;
    }

}