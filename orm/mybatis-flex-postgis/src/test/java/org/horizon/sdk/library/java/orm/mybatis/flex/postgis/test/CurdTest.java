package org.horizon.sdk.library.java.orm.mybatis.flex.postgis.test;

import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.horizon.sdk.library.java.contract.model.throwable.DataNotFoundException;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.id.IdConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgis.config.TestInsertListener;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgis.config.TestUpdateListener;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgis.model.po.HomePO;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgis.repository.HomeRepository;
import org.horizon.sdk.library.java.tool.geometry.Geometries;
import org.horizon.sdk.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.sdk.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@MapperScan("org.horizon.sdk.library.java.orm.mybatis.flex.postgis.**")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                nativeMybatisLog = NoLoggingImpl.class,
                xmlMapperClassPaths = {"classpath*:org/horizon/sdk/library/java/orm/mybatis/flex/postgis/repository/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"org.horizon.sdk.library.java.orm.mybatis.**.po"}
        )
)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableAspectJAutoProxy(exposeProxy = true)
class CurdTest {

    @Autowired private HomeRepository homeRepository;

    @Test
    void testQuery() {
        Long homeId = 1L;
        homeRepository.save(HomePO.builder().id(homeId).name("test").location(Geometries.toGeometry("POINT(1 1)")).build());
        HomePO homePO = homeRepository.getById(homeId).orElseThrow(DataNotFoundException::new);
        homeRepository.openDelete().where(HomePO::getId).equalsTo(homeId).ignoreLogicDelete().delete();
    }

}