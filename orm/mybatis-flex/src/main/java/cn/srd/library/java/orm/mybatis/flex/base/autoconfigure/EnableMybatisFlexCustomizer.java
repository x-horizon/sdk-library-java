// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable the global config of orm mybatis flex system
 *
 * @author wjm
 * @see MybatisFlexCustomizerSwitcher
 * @see MybatisFlexCustomizer
 * @see MybatisFlexAutoConfigurer
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
@Import(MybatisFlexCustomizerSwitcher.class)
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
     * the audit log config
     *
     * @return the audit log config
     * @see AuditLogConfig
     */
    AuditLogConfig auditConfig() default @AuditLogConfig;

}
