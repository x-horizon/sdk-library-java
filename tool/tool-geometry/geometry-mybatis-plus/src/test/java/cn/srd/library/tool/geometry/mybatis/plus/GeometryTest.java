package cn.srd.library.tool.geometry.mybatis.plus;

import cn.srd.library.orm.mybatis.plus.core.EnableMybatisPlusPageInterceptor;
import cn.srd.library.orm.mybatis.plus.core.MpWrappers;
import cn.srd.library.tool.convert.all.core.Converts;
import cn.srd.library.tool.geometry.mybatis.plus.database.postgresql.core.EnableMybatisPlusPostgresqlGeometryInterceptor;
import cn.srd.library.tool.geometry.mybatis.plus.model.po.GeometryTestPO;
import cn.srd.library.tool.geometry.mybatis.plus.service.GeometryTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@MapperScan({"cn.srd.library.tool.geometry.mybatis.plus.dao"})
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableMybatisPlusPageInterceptor
@EnableMybatisPlusPostgresqlGeometryInterceptor
public class GeometryTest {

    @Autowired
    private GeometryTestService geometryTestService;

    @Test
    public void testGeometryJdbcConvert() {
        List<GeometryTestPO> geometryTestPOs = geometryTestService.list();
    }

    @Test
    public void testGeometryJacksonConvert() {
        GeometryTestPO geometryTestPO = geometryTestService.getOne(MpWrappers.<GeometryTestPO>withLambdaQuery().eq(GeometryTestPO::getId, 1));
        String value1 = Converts.withJackson().toString(geometryTestPO);
        GeometryTestPO value2 = Converts.withJackson().toBean(value1, GeometryTestPO.class);
    }

}
