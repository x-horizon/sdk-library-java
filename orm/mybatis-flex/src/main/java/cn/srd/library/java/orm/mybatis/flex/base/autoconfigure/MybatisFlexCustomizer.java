package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.spring.contract.Annotations;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisFlexCustomizer implements ConfigurationCustomizer, MyBatisFlexCustomizer {

    @Override
    public void customize(FlexConfiguration configuration) {

    }

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        log.debug("{}mybatis flex customizer is enabled, starting initializing...", ModuleView.ORM_MYBATIS_SYSTEM);

        EnableMybatisFlexCustomizer mybatisFlexCustomizer = Annotations.getAnnotation(EnableMybatisFlexCustomizer.class);
        globalConfig.setKeyConfig(mybatisFlexCustomizer.globalIdGenerateConfig().type().getStrategy().build(mybatisFlexCustomizer.globalIdGenerateConfig()));

        // handleAuditConfig(mybatisFlexCustomizer.auditConfig());

        log.debug("{}mybatis flex customizer initialized.", ModuleView.ORM_MYBATIS_SYSTEM);
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
