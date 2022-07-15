package cn.srd.itcp.sugar.mybatis.plus.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @date 2022-07-15
 */
public class DatabasePostgresqlHandler implements DatabaseHandler {

    protected static final DatabasePostgresqlHandler INSTANCE = new DatabasePostgresqlHandler();

    @Getter
    @AllArgsConstructor
    public enum ColumnTypeSerial {

        SMALL_SERIAL("smallserial"),
        SERIAL("serial"),
        BIG_SERIAL("bigserial");

        private final String description;

    }

    @Getter
    @AllArgsConstructor
    public enum ColumnTypeNumber {

        SMALLINT("int2", "smallint"),
        INTEGER("int4", "integer"),
        BIGINT("int8", "bigint");

        private final String description1;
        private final String description2;

    }

    @Getter
    @AllArgsConstructor
    public enum ColumnTypeString {

        CHAR("char"),
        VARCHAR("varchar"),
        TEXT("text");

        private final String description;

    }

    @Getter
    @AllArgsConstructor
    public enum ColumnTypeTime {

        TIME("time"),
        DATE("date"),
        TIMESTAMP("timestamp"),
        INTERVAL("interval");

        private final String description;

    }

    @Getter
    @AllArgsConstructor
    public enum ColumnTypeBoolean {

        BOOLEAN("bool");

        private final String description;

    }

}
