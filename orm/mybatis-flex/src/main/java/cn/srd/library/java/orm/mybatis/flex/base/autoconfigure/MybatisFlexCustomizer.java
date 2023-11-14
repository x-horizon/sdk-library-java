// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.listener.*;
import cn.srd.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.object.Objects;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.Annotations;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import lombok.extern.slf4j.Slf4j;

/**
 * the global mybatis flex customizer
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@Slf4j
public class MybatisFlexCustomizer implements ConfigurationCustomizer, MyBatisFlexCustomizer {

    @Override
    public void customize(FlexConfiguration configuration) {
    }

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        log.debug("{}mybatis flex customizer is enabled, starting initializing...", ModuleView.ORM_MYBATIS_SYSTEM);

        EnableMybatisFlexCustomizer mybatisFlexCustomizer = Annotations.getAnnotation(EnableMybatisFlexCustomizer.class);
        setIdGenerateConfig(globalConfig, mybatisFlexCustomizer.globalIdGenerateConfig());
        setDeleteLogicConfig(globalConfig, mybatisFlexCustomizer.globalDeleteLogicConfig());
        setListenerConfig(globalConfig, mybatisFlexCustomizer.globalListenerConfig());
        setOptimisticLockConfig(globalConfig, mybatisFlexCustomizer.globalOptimisticLockConfig());
        // handleAuditConfig(mybatisFlexCustomizer.auditConfig());

        log.debug("{}mybatis flex customizer initialized.", ModuleView.ORM_MYBATIS_SYSTEM);
    }

    /**
     * handle the global id config
     *
     * @param globalConfig the mybatis flex global config
     * @param idConfig     the global id config
     */
    private void setIdGenerateConfig(FlexGlobalConfig globalConfig, IdConfig idConfig) {
        globalConfig.setKeyConfig(idConfig.generateType().getStrategy().build(idConfig));
    }

    /**
     * handle the global delete logic config
     *
     * @param globalConfig      the mybatis flex global config
     * @param deleteLogicConfig the global delete logic config
     */
    private void setDeleteLogicConfig(FlexGlobalConfig globalConfig, DeleteLogicConfig deleteLogicConfig) {
        String normalValue = deleteLogicConfig.normalValue();
        String deletedValue = deleteLogicConfig.deletedValue();

        Assert.of().setMessage("")
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfAnyBlank(normalValue, deletedValue);

        // note:
        // must convert to number first and then convert to boolean,
        // because the value "0" can convert to boolean false, but the actual value should be integer 0.
        Object actualNormalValue = Converts.toInteger(normalValue);
        if (Nil.isNull(actualNormalValue)) {
            actualNormalValue = Converts.toBoolean(normalValue);
            if (Nil.isNull(actualNormalValue)) {
                actualNormalValue = Converts.toString(normalValue);
            }
        }

        Object actualDeletedValue = Converts.toInteger(deletedValue);
        if (Nil.isNull(actualDeletedValue)) {
            actualDeletedValue = Converts.toBoolean(deletedValue);
            if (Nil.isNull(actualDeletedValue)) {
                actualDeletedValue = Converts.toString(deletedValue);
            }
        }

        globalConfig.setNormalValueOfLogicDelete(actualNormalValue);
        globalConfig.setDeletedValueOfLogicDelete(actualDeletedValue);
    }

    /**
     * handle the global listener config
     *
     * @param globalConfig   the mybatis flex global config
     * @param listenerConfig the global listener config
     */
    private void setListenerConfig(FlexGlobalConfig globalConfig, ListenerConfig listenerConfig) {
        BaseInsertListener<?> insertListener = Reflects.newInstance(listenerConfig.whenInsert());
        BaseUpdateListener<?> updateListener = Reflects.newInstance(listenerConfig.whenUpdate());
        if (Comparators.notEquals(NoneInsertListener.class, listenerConfig.whenInsert())) {
            globalConfig.registerInsertListener(insertListener, insertListener.getEntityType());
        }
        if (Comparators.notEquals(NoneUpdateListener.class, listenerConfig.whenUpdate())) {
            globalConfig.registerUpdateListener(updateListener, updateListener.getEntityType());
        }
    }

    /**
     * handle the global optimistic lock config
     *
     * @param globalConfig         the mybatis flex global config
     * @param optimisticLockConfig the global id config
     */
    private void setOptimisticLockConfig(FlexGlobalConfig globalConfig, OptimisticLockConfig optimisticLockConfig) {
        Objects.setIfNotBlank(optimisticLockConfig.columnName(), globalConfig::setVersionColumn);
    }

    // private void handleAuditConfig(AuditConfig auditConfig) {
    //     handleAuditLogSQLConfig(auditConfig);
    // }

    // private void handleAuditLogSQLConfig(AuditConfig auditConfig) {
    //     String enableLogSQLWrapper = auditConfig.enableLogSQL();
    //     Boolean enableLogSQL = Converts.toBool(enableLogSQLWrapper);
    //     if (Objects.isFalse(enableLogSQL)) {
    //         enableLogSQL = Converts.toBool(SpringsUtil.getProperty(StringsUtil.removeIfStartAndEndWith(enableLogSQLWrapper, StringPool.DOLLAR_AND_DELIM_START, StringPool.DELIM_END)));
    //     }
    //     Assert.INSTANCE.set(StringsUtil.format("{}could not infer the value [{}] in [@{}]-[@{}] to a boolean value, please check!", ModuleView.ORM_MYBATIS_SYSTEM, enableLogSQLWrapper, EnableMybatisFlexCustomizer.class.getSimpleName(), AuditConfig.class.getSimpleName())).throwsIfNull(enableLogSQL);
    //     if (enableLogSQL) {
    //         AuditManager.setAuditEnable(true);
    //         AuditManager.setMessageCollector(auditMessage -> log.debug("{},{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime()));
    //     }
    // }

}
