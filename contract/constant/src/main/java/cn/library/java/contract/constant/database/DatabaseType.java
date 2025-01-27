package cn.library.java.contract.constant.database;

import lombok.Getter;

/**
 * database type
 *
 * @author wjm
 * @since 2023-02-11 11:36
 */
@Getter
public enum DatabaseType {

    MYSQL("mysql", "Mysql", "MySQL"),
    POSTGRESQL("postgres", "postgresql", "postgreSQL", "Postgresql", "PostgreSQL"),

    ;

    DatabaseType(String... names) {
        this.names = names;
    }

    private final String[] names;

}