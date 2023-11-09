package cn.srd.library.java.orm.mybatis.flex.base.test;

import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.dao.StudentDao;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.model.po.StudentPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @EnableSnowflakeId
@MapperScan("cn.srd.library.java.orm.mybatis.flex.base.dao")
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdGenerateConfig(type = IdGenerateType.AUTO_INCREMENT)
        // auditConfig = @AuditConfig(enableLogSQL = "${library.java.orm.enable-audit-message}")
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
