package cn.library.java.message.engine.server.mqtt;

import cn.library.java.message.engine.server.mqtt.autoconfigure.EnableMessageMqttServer;
import cn.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import cn.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wjm
 * @since 2024-12-29 17:29
 */
@MapperScan("cn.library.java.message.engine.server.**.repository")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        // globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        // globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                nativeMybatisLog = NoLoggingImpl.class,
                xmlMapperClassPaths = {"classpath*:cn/library/java/message/engine/server/mqtt/repository/impl/*.xml"}
                // xmlMapperEntityPackageAliasPackagePaths = {"cn.library.java.message.engine.server.**.model.**"}
        )
)
@EnableMessageMqttServer
@SpringBootApplication
public class MessageMqttServerApplication {

    public static void main(String... args) {
        SpringApplication.run(MessageMqttServerApplication.class, args);
    }

}