package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdAutoIncrementDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdAutoIncrementPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.dao")
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.AUTO_INCREMENT),
        globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE, deletedValue = BooleanConstant.TRUE),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        auditConfig = @AuditLogConfig(enable = true)
)
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGenerateByAutoIncrementTest {

    @Autowired private StudentTestIdAutoIncrementDao studentTestIdAutoIncrementDao;

    @Test
    public void testIt() {
        studentTestIdAutoIncrementDao.insert(StudentTestIdAutoIncrementPO.builder().name("test1").build());
        studentTestIdAutoIncrementDao.insertSelective(StudentTestIdAutoIncrementPO.builder().build());

        System.out.println();
    }

}
