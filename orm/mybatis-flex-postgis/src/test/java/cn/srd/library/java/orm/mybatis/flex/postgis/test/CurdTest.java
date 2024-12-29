package cn.srd.library.java.orm.mybatis.flex.postgis.test;

import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.srd.library.java.orm.mybatis.flex.postgis.config.TestInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgis.config.TestUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgis.model.po.HomePO;
import cn.srd.library.java.orm.mybatis.flex.postgis.repository.HomeRepository;
import cn.srd.library.java.tool.geometry.Geometries;
import cn.srd.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@MapperScan("cn.srd.library.java.orm.mybatis.flex.postgis.**")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                nativeMybatisLog = NoLoggingImpl.class,
                xmlMapperClassPaths = {"classpath*:cn/srd/library/java/orm/mybatis/flex/postgis/repository/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"cn.srd.library.java.orm.mybatis.**.po"}
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