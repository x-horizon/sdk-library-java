package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSnowflakeDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSnowflakePO;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdEnvironment;
import cn.srd.library.java.tool.lang.collection.Collections;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.dao")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE_STRING_LOWER_CASE, deletedValue = BooleanConstant.TRUE_STRING_LOWER_CASE),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                nativeMybatisLog = NoLoggingImpl.class,
                xmlMapperClassPaths = {"classpath*:cn/srd/library/java/orm/mybatis/base/customer/dao/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"cn.srd.library.java.orm.mybatis.**.po"}
        )
)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class IdGenerateBySnowflakeTest {

    @Autowired private StudentTestIdSnowflakeDao studentTestIdSnowflakeDao;

    @Test
    void testIt() {
        // StudentTestIdSnowflakePO studentTestIdSnowflakePO1 = studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().build());
        // Assertions.assertThrowsExactly(MyBatisSystemException.class, () -> studentTestIdSnowflakeDao.saveWithPK(StudentTestIdSnowflakePO.builder().build()));
        // StudentTestIdSnowflakePO studentTestIdSnowflakePO3 = studentTestIdSnowflakeDao.saveWithPK(StudentTestIdSnowflakePO.builder().id(9999L).build());
        //
        // Map<Integer, StudentTestIdSnowflakePO> smallStudentTestIdSnowflakePOs = Collections.newHashMap();
        // for (int i = 1; i < 60; i++) {
        //     smallStudentTestIdSnowflakePOs.put(i, StudentTestIdSnowflakePO.builder().build());
        // }
        // List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs1 = studentTestIdSnowflakeDao.saveBatch(smallStudentTestIdSnowflakePOs.values());
        //
        // Map<Integer, StudentTestIdSnowflakePO> bigStudentTestIdSnowflakePOs = Collections.newHashMap();
        // for (int i = 1; i < 300; i++) {
        //     bigStudentTestIdSnowflakePOs.put(i, StudentTestIdSnowflakePO.builder().build());
        // }
        // List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs2 = studentTestIdSnowflakeDao.saveBatch(bigStudentTestIdSnowflakePOs.values());

        Map<Integer, StudentTestIdSnowflakePO> smallStudentTestIdSnowflakeWithIdPOs = Collections.newHashMap();
        for (int i = 1; i < 60; i++) {
            Long id = (long) (10000 + i);
            smallStudentTestIdSnowflakeWithIdPOs.put(i, StudentTestIdSnowflakePO.builder().id(id).build());
        }
        List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs3 = studentTestIdSnowflakeDao.saveBatch(smallStudentTestIdSnowflakeWithIdPOs.values());

        Map<Integer, StudentTestIdSnowflakePO> bigStudentTestIdSnowflakeWithIdPOs = Collections.newHashMap();
        for (int i = 1; i < 300; i++) {
            Long id = (long) (20000 + i);
            bigStudentTestIdSnowflakeWithIdPOs.put(i, StudentTestIdSnowflakePO.builder().id(id).build());
        }
        List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs4 = studentTestIdSnowflakeDao.saveBatch(bigStudentTestIdSnowflakeWithIdPOs.values());

        studentTestIdSnowflakeDao.listAll();
        studentTestIdSnowflakeDao.listAll();
        studentTestIdSnowflakeDao.listAll();
        // studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().build());
        // studentTestIdSnowflakeDao.insertSelective(StudentTestIdSnowflakePO.builder().build());

        System.out.println();
    }

}
