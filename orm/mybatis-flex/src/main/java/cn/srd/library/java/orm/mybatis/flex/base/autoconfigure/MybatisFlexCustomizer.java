package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateByUncontrolledStrategy;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateConfig;
import cn.srd.library.java.tool.spring.contract.Classes;
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

        // EnableMybatisFlexCustomizer mybatisFlexCustomizer = getCustomizer();
        // handleGlobalIdGenerateConfig(mybatisFlexCustomizer.globalIdGenerateConfig(), globalConfig);
        // handleAuditConfig(mybatisFlexCustomizer.auditConfig());

        log.debug("{}initialized.", ModuleView.ORM_MYBATIS_SYSTEM);
    }

    private void handleGlobalIdGenerateConfig(IdGenerateConfig globalIdGenerateConfig, FlexGlobalConfig globalConfig) {
        if (Classes.isNotAssignable(globalIdGenerateConfig.type().getStrategy().getClass(), IdGenerateByUncontrolledStrategy.class)) {
            globalConfig.setKeyConfig(globalIdGenerateConfig.type().getStrategy().buildConfig(globalIdGenerateConfig));
        }
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

    // private EnableMybatisFlexCustomizer getCustomizer() {
    //     // TODO wjm 抽象公共的 AnnotationScanner、以及扫描到后的注解
    //     Set<Class<?>> classesWithMybatisFlexCustomizerScanner = Classes.scanByAnnotation(MybatisFlexCustomizerScanner.class);
    //     Assert.of().setMessage("{}found multiple [@{}] in {}, please specify one", MybatisFlexCustomizerScanner.class.getSimpleName(), ModuleView.ORM_MYBATIS_SYSTEM, Collections.toList(classesWithMybatisFlexCustomizerScanner, Class::getName))
    //             .throwsIfTrue(classesWithMybatisFlexCustomizerScanner.size() > 1);
    //
    //     String[] packageNamesToFindMybatisFlexCustomizer = new String[]{Classes.getBasePackagePath()};
    //     Set<Class<?>> classesWithMybatisFlexCustomizer;
    //     if (Nil.isNotEmpty(classesWithMybatisFlexCustomizerScanner)) {
    //         packageNamesToFindMybatisFlexCustomizer = ArraysUtil.append(packageNamesToFindMybatisFlexCustomizer, AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithMybatisFlexCustomizerScanner), MybatisFlexCustomizerScanner.class));
    //         classesWithMybatisFlexCustomizer = ClassesUtil.scanPackageByAnnotation(packageNamesToFindMybatisFlexCustomizer, EnableMybatisFlexCustomizer.class);
    //     } else {
    //         classesWithMybatisFlexCustomizer = SpringsUtil.scanPackageByAnnotation(EnableMybatisFlexCustomizer.class);
    //     }
    //     Assert.INSTANCE.set(StringsUtil.format("found multiple [@{}] in {}, please specify one", EnableMybatisFlexCustomizer.class.getSimpleName(), CollectionsUtil.toList(classesWithMybatisFlexCustomizer, Class::getName))).throwsIfTrue(classesWithMybatisFlexCustomizer.size() > 1);
    //
    //     return AnnotationsUtil.getAnnotation(CollectionsUtil.getFirst(classesWithMybatisFlexCustomizer), EnableMybatisFlexCustomizer.class);
    // }

}
