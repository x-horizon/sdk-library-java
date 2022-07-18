package cn.srd.itcp.sugar.mybatis.plus.dml;

import cn.srd.itcp.sugar.mybatis.plus.metadata.handler.PostgresqlTableColumnHandler;
import cn.srd.itcp.sugar.mybatis.plus.metadata.handler.PostgresqlTableHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@MapperScan({"cn.srd.itcp.sugar.mybatis.plus.metadata"})
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
