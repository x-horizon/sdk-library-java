package cn.srd.itcp.sugar.mybatis.plus.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @date 2022-07-15
 */
@Getter
@AllArgsConstructor
public enum DatabaseTypeEnum {

    MYSQL(DatabaseMysqlHandler.INSTANCE),
    POSTGRESQL(DatabasePostgresqlHandler.INSTANCE);

    private final DatabaseHandler databaseHandler;

}
