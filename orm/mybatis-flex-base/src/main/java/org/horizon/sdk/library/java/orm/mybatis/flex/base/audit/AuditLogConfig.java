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
     * <p>provides a class implementing {@link AuditLogConstructor} to extend audit log models.</p>
     *
     * <p>default audit log contains:</p>
     * <ul>
     *   <li>{@link AuditMessage#getQuery()}</li>
     *   <li>{@link AuditMessage#getQueryParams()}</li>
     *   <li>{@link AuditMessage#getQueryCount()}</li>
     *   <li>{@link AuditMessage#getQueryTime()}</li>
     *   <li>{@link AuditMessage#getElapsedTime()}</li>
     * </ul>
     *
     * <p>you can build a {@link AuditLogDTO} by implement {@link AuditLogConstructor} to expand more audit log, example code:</p>
     * <pre>{@code
     * public class TestAuditLogConstructor implements AuditLogConstructor {
     *     @Override
     *     public AuditLogDTO build() {
     *         return AuditLogDTO.builder()
     *             .platformName("myPlatform")
     *             .build();
     *     }
     * }
     * }</pre>
     *
     * @return implementation class for audit log model extension
     * @apiNote extended models can be consumed by {@link #printer()} and {@link #telemeter()}
     * @see AuditLogConstructor#create()
     * @see SimpleAuditLogConstructor
     * @see AuditMessage
     * @see AuditLogDTO
     */
    Class<? extends AuditLogConstructor> constructor() default SimpleAuditLogConstructor.class;

    /**
     * <p>provides a class implementing {@link AuditLogPrinter} to handle audit log output.</p>
     *
     * <p>implementation example:</p>
     * <pre>{@code
     * @Slf4j
     * public class CustomAuditPrinter implements AuditLogPrinter {
     *     @Override
     *     public void print(AuditMessage auditMessage) {
     *         log.debug("executed SQL in {} ms >>> {}",
     *             auditMessage.getElapsedTime(),
     *             auditMessage.getFullSql());
     *     }
     * }
     * }</pre>
     *
     * @return audit log printer implementation class
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