package com.test;

import cn.srd.library.java.contract.constant.core.ModuleView;
import cn.srd.library.java.tool.constant.core.StringPool;
import cn.srd.library.java.tool.convert.all.core.Converts;
import cn.srd.library.java.tool.lang.core.*;
import cn.srd.library.java.tool.lang.core.asserts.Assert;
import cn.srd.library.java.tool.lang.core.object.Objects;
import cn.srd.library.java.tool.spring.common.core.SpringsUtil;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.test.audit.AuditConfig;
import com.test.id.IdGenerateConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class MybatisFlexCapableCustomizer implements ConfigurationCustomizer, MyBatisFlexCustomizer {

    @Override
    public void customize(FlexConfiguration configuration) {

    }

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        log.debug("{}mybatis flex customizer enable, starting configuring...", ModuleView.ORM_MYBATIS_FLEX_SYSTEM);
        EnableMybatisFlexCapableCustomizer mybatisFlexCustomizer = getMybatisFlexCustomizer();

        IdGenerateConfig globalIdGenerateConfig = mybatisFlexCustomizer.globalIdGenerateConfig();
        globalConfig.setKeyConfig(globalIdGenerateConfig.type().getStrategy().buildConfig(globalIdGenerateConfig));

        String enableAuditMessageWrapper = mybatisFlexCustomizer.auditConfig().enableAuditMessage();
        Boolean enableAuditMessage = Converts.toBool(enableAuditMessageWrapper);
        if (Objects.isFalse(enableAuditMessage)) {
            enableAuditMessage = Converts.toBool(SpringsUtil.getProperty(StringsUtil.removeIfStartAndEndWith(enableAuditMessageWrapper, StringPool.DOLLAR_AND_DELIM_START, StringPool.DELIM_END)));
        }
        Assert.INSTANCE.set(StringsUtil.format("{}could not infer the value [{}] in [@{}]-[@{}] to a boolean value, please check!", ModuleView.ORM_MYBATIS_FLEX_SYSTEM, enableAuditMessageWrapper, EnableMybatisFlexCapableCustomizer.class.getSimpleName(), AuditConfig.class.getSimpleName())).throwsIfNull(enableAuditMessage);
        if (enableAuditMessage) {
            AuditManager.setAuditEnable(true);
            AuditManager.setMessageCollector(auditMessage -> log.debug("{},{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime()));
        }
    }

    private EnableMybatisFlexCapableCustomizer getMybatisFlexCustomizer() {
        // TODO wjm 抽象公共的 AnnotationScanner、以及扫描到后的注解
        Set<Class<?>> classesWithMybatisFlexCustomizerScanner = SpringsUtil.scanPackageByAnnotation(MybatisFlexCapableCustomizerScanner.class);
        Assert.INSTANCE.set(StringsUtil.format("{}found multiple [@{}] in {}, please specify one", MybatisFlexCapableCustomizerScanner.class.getSimpleName(), ModuleView.ORM_MYBATIS_FLEX_SYSTEM, CollectionsUtil.toList(classesWithMybatisFlexCustomizerScanner, Class::getName))).throwsIfTrue(classesWithMybatisFlexCustomizerScanner.size() > 1);

        String[] packageNamesToFindMybatisFlexCustomizer = new String[]{SpringsUtil.getRootPackagePath()};
        Set<Class<?>> classesWithMybatisFlexCustomizer;
        if (Objects.isNotEmpty(classesWithMybatisFlexCustomizerScanner)) {
            packageNamesToFindMybatisFlexCustomizer = ArraysUtil.append(packageNamesToFindMybatisFlexCustomizer, AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithMybatisFlexCustomizerScanner), MybatisFlexCapableCustomizerScanner.class));
            classesWithMybatisFlexCustomizer = ClassesUtil.scanPackageByAnnotation(packageNamesToFindMybatisFlexCustomizer, EnableMybatisFlexCapableCustomizer.class);
        } else {
            classesWithMybatisFlexCustomizer = SpringsUtil.scanPackageByAnnotation(EnableMybatisFlexCapableCustomizer.class);
        }
        Assert.INSTANCE.set(StringsUtil.format("found multiple [@{}] in {}, please specify one", EnableMybatisFlexCapableCustomizer.class.getSimpleName(), CollectionsUtil.toList(classesWithMybatisFlexCustomizer, Class::getName))).throwsIfTrue(classesWithMybatisFlexCustomizer.size() > 1);

        return AnnotationsUtil.getAnnotation(CollectionsUtil.getFirst(classesWithMybatisFlexCustomizer), EnableMybatisFlexCapableCustomizer.class);
    }

}
