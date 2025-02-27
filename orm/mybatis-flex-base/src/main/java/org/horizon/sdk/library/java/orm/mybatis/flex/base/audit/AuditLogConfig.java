package org.horizon.sdk.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditMessage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the audit log config TODO wjm implement @AuditLogConfigs to wrap multiple @AuditLogConfig to support multiple enviroment different config
 *
 * @author wjm
 * @since 2023-11-14 14:53
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLogConfig {

    /**
     * enable audit log or not
     *
     * @return enable audit log or not
     */
    boolean enable() default false;

    /**
     * enable audit log or not from the spring config yaml file.
     *
     * @return enable audit log or not
     */
    String enableFrom() default "";

    /**
     * provide a class implement by {@link AuditLogConstructor} to expand extra audit log model.
     *
     * <p>the default info in audit log as following:
     * <ul>
     *   <li>{@link AuditMessage#getQuery()}</li>
     *   <li>{@link AuditMessage#getQueryParams()}</li>
     *   <li>{@link AuditMessage#getQueryCount()}</li>
     *   <li>{@link AuditMessage#getQueryTime()}</li>
     *   <li>{@link AuditMessage#getQuery()}</li>
     *   <li>{@link AuditMessage#getElapsedTime()}</li>
     * </ul>
     *
     * <pre>
     * you can build a {@link AuditLogDTO} by implement {@link AuditLogConstructor} to expand more audit log, example code:
     *
     * {@code
     *   public class TestAuditLogConstructor implements AuditLogConstructor {
     *
     *       @Override
     *       public AuditLogDTO build() {
     *           return AuditLogDTO.builder()
     *                   .platformName("myPlatform")
     *                   .build();
     *       }
     *
     *   }
     * }
     * </pre>
     *
     * @return the class implement by {@link AuditLogConstructor} to expand extra audit log model
     * @apiNote the audit log model can be used to {@link #printer()}, {@link #telemeter()}.
     * @see AuditLogConstructor#create()
     * @see SimpleAuditLogConstructor
     * @see AuditMessage
     * @see AuditLogDTO
     */
    Class<? extends AuditLogConstructor> constructor() default SimpleAuditLogConstructor.class;

    /**
     * <pre>
     * provide a class implement by {@link AuditLogPrinter} to print {@link AuditMessage audit log}, example code:
     *
     * {@code
     *   @Slf4j
     *   public class TestAuditLogPrinter implements AuditLogPrinter {
     *
     *       @Override
     *       public void print(AuditMessage auditMessage) {
     *           log.debug("exec sql took {} ms >>> {}", auditMessage.getElapsedTime(), auditMessage.getFullSql());
     *       }
     *
     *   }
     * }
     * </pre>
     *
     * @return the class implement by {@link AuditLogPrinter} to print {@link AuditMessage audit log}
     * @see AuditLogPrinter
     * @see SimpleAuditLogPrinter
     * @see AuditLogConstructor#create()
     * @see AuditMessage
     * @see AuditLogDTO
     */
    Class<? extends AuditLogPrinter> printer() default SimpleAuditLogPrinter.class;

    /**
     * provide a class implement by {@link AuditLogTelemeter} to telemetry the audit log.
     *
     * @return the class implement by {@link AuditLogTelemeter} to telemetry the audit log
     * @see AuditLogTelemeter
     * @see AuditLogConstructor#create()
     * @see AuditMessage
     * @see AuditLogDTO
     */
    Class<? extends AuditLogTelemeter> telemeter() default UnsupportedAuditLogTelemeter.class;

}