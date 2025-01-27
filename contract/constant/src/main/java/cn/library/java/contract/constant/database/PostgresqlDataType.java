package cn.library.java.contract.constant.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * the data type in database postgresql
 *
 * @author wjm
 * @since 2023-11-06 19:28
 */
@Getter
@AllArgsConstructor
public enum PostgresqlDataType {

    BOOLEAN("BOOLEAN"),

    SMALLINT("SMALLINT"),
    INTEGER("INTEGER"),
    BIGINT("BIGINT"),

    DECIMAL("DECIMAL"),
    NUMERIC("NUMERIC"),
    REAL("REAL"),

    CHAR("CHAR"),
    VARCHAR("VARCHAR"),
    TEXT("TEXT"),

    TIMESTAMP("TIMESTAMP"),

    JSON("JSON"),
    JSONB("JSONB"),

    ;

    private final String value;

}