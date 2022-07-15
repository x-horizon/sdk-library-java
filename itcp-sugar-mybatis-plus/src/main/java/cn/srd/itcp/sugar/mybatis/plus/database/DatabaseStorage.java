package cn.srd.itcp.sugar.mybatis.plus.database;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;

/**
 * @author wjm
 * @date 2022-07-15
 */
@Getter
@Setter
public class DatabaseStorage {

    public static DatabaseStorage instance = null;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static DatabaseStorage getInstance() {
        return instance;
    }

    private DatabaseHandler databaseHandler;

}
