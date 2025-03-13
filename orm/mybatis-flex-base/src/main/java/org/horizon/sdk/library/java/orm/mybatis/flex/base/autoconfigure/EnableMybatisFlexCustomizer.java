package org.horizon.sdk.library.java.orm.mybatis.flex.base.autoconfigure;

import com.mybatisflex.spring.boot.MybatisFlexProperties;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.id.IdConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable the global config of orm mybatis flex system
 *
 * @author wjm
 * @see MybatisFlexCustomizerRegistrar
 * @see MybatisFlexCustomizer
 * @see MybatisFlexAutoConfigurer#mybatisFlexCapableCustomizer()
 * @see IdConfig
 * @see DeleteLogicConfig
 * @see ListenerConfig
 * @see OptimisticLockConfig
 * @see AuditLogConfig
 * @since 2023-11-12 21:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisFlexCustomizerRegistrar.class)
public @interface EnableMybatisFlexCustomizer {

    /**
     * the global id config
     *
     * @return the global id config
     * @see IdConfig
     */
    IdConfig globalIdGenerateConfig() default @IdConfig;

    /**
     * the global delete logic config
     *
     * @return the global delete logic config
     * @see DeleteLogicConfig
     */
    DeleteLogicConfig globalDeleteLogicConfig() default @DeleteLogicConfig;

    /**
     * the global listener config
     *
     * @return the global listener config
     * @see ListenerConfig
     */
    ListenerConfig globalListenerConfig() default @ListenerConfig;

    /**
     * the global optimistic lock config
     *
     * @return the global optimistic lock config
     * @see OptimisticLockConfig
     **/
    OptimisticLockConfig globalOptimisticLockConfig() default @OptimisticLockConfig;

    /**
     * the global audit log config
     *
     * @return the audit log config
     * @see AuditLogConfig
     */
    AuditLogConfig globalAuditConfig() default @AuditLogConfig;

    /**
     * the global property config for {@link MybatisFlexProperties}
     *
     * @return the global property config for {@link MybatisFlexProperties}
     * @see PropertyConfig
     */
    PropertyConfig globalPropertyConfig() default @PropertyConfig;

}