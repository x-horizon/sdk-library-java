package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.contract.constant.database.PostgresqlDataType;
import org.postgresql.util.PGobject;

/**
 * the {@link PGobject} jsonb converter.
 *
 * @author wjm
 * @since 2023-11-06 18:29
 */
public interface PgObjectJsonbConverter extends PgObjectConverter {

    @Override
    default String getPgObjectType() {
        return PostgresqlDataType.JSONB.getValue();
    }

}