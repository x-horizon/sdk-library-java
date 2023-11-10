package cn.library.java.orm.mybatis.flex.postgresql.test;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdSnowflakeGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @EnableSnowflakeId
@MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
@EnableMybatisFlexCustomizer(
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.AUTO_INCREMENT, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.UUID, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.SNOWFLAKE, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.CUSTOMER, generator = IdInvalidGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.SQL, generator = IdSnowflakeGenerator.class, generateSQL = "")
        globalIdGenerateConfig = @IdConfig(type = IdGenerateType.UNCONTROLLED, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
)
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGenerateTest {

    @Autowired private StudentDao studentDao;

    @Test
    public void testl() {
        StudentPO studentPO = StudentPO.builder()
                // .type(StudentType.A)
                .build();
        studentDao.insertSelective(studentPO);
    }

}
