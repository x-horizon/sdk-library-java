package cn.srd.sugar.orm.mybatis.plus.dml;

import cn.srd.sugar.orm.mybatis.plus.database.postgresql.metadata.handler.PostgresqlTableColumnHandler;
import cn.srd.sugar.orm.mybatis.plus.database.postgresql.metadata.handler.PostgresqlTableHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@MapperScan({"cn.srd.sugar.orm.mybatis.plus.database.postgresql.metadata.dao"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDmlTest {

    @Autowired
    private PostgresqlTableHandler postgresqlTableHandler;
    @Autowired
    private PostgresqlTableColumnHandler postgresqlTableColumnHandler;

    @Test
    public void testMybatisPlusDml() {
        Assert.assertNotNull(postgresqlTableHandler.getByTableName("sys_menu"));
        Assert.assertTrue(postgresqlTableColumnHandler.listByTableName("sys_menu").size() > 0);
    }

}
