package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

/**
 * the {@link PGobject} converter.
 *
 * @author wjm
 * @since 2023-11-06 18:29
 */
public interface PostgresqlObjectConverter {

    String getPgObjectType();

    @SneakyThrows
    default PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        PGobject pgObject = new PGobject();
        pgObject.setType(getPgObjectType());
        pgObject.setValue(javaObjectContent);
        return pgObject;
    }

}