package cn.srd.itcp.sugar.mybatis.plus.database;

import java.lang.annotation.*;

/**
 * @author wjm
 * @date 2022-07-15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableDefaultDatabase {

    String value();

}
