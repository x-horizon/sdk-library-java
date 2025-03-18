package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.test;

import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.id.IdConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.config.TestInsertListener;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.config.TestUpdateListener;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.po.StudentPO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.repository.StudentRepository;
import org.horizon.sdk.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.sdk.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@MapperScan("org.horizon.sdk.library.java.orm.mybatis.flex.mysql.**")
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                nativeMybatisLog = NoLoggingImpl.class,
                xmlMapperClassPaths = {"classpath*:org/horizon/sdk/library/java/orm/mybatis/flex/mysql/repository/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"org.horizon.sdk.library.java.orm.mybatis.**.po"}
        )
)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CurdTest {

    @Autowired private StudentRepository studentRepository;

    @Test
    void testQuery() {
        List<StudentPO> studentPOs = studentRepository.listAll();
        System.out.println();
    }

}