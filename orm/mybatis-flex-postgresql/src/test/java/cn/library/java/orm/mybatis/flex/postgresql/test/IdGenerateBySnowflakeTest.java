package cn.library.java.orm.mybatis.flex.postgresql.test;

import cn.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
import cn.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSnowflakeDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSnowflakePO;
import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE, deletedValue = BooleanConstant.TRUE),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        auditConfig = @AuditLogConfig(enable = true),
        propertyConfig = @PropertyConfig(
                xmlMapperClassPaths = {"classpath*:cn/library/java/orm/mybatis/base/customer/dao/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"cn.library.java.**.po"}
        )
)
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGenerateBySnowflakeTest {

    @Autowired private StudentTestIdSnowflakeDao studentTestIdSnowflakeDao;

    @Test
    public void testIt() {
        studentTestIdSnowflakeDao.insert(StudentTestIdSnowflakePO.builder().build());
        // studentTestIdSnowflakeDao.insertSelective(StudentTestIdSnowflakePO.builder().build());

        System.out.println();
    }

}
