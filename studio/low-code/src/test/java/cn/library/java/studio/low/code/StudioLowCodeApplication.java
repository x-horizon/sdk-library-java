package cn.library.java.studio.low.code;

import cn.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import cn.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.library.java.studio.low.code.manager.orm.MybatisInsertListener;
import cn.library.java.studio.low.code.manager.orm.MybatisUpdateListener;
import cn.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import cn.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import cn.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("cn.library.java.studio.low.code.**.repository")
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableEnumAutowired
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        globalListenerConfig = @ListenerConfig(whenInsert = MybatisInsertListener.class, whenUpdate = MybatisUpdateListener.class),
        globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                xmlMapperClassPaths = {"classpath*:cn/library/java/studio/low/code/**/repository/**/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"cn.library.java.studio.low.code.**.model.**"}
        )
)
@SpringBootApplication
public class StudioLowCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudioLowCodeApplication.class, args);
    }

}