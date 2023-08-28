package com.test;

import cn.srd.library.java.contract.constant.core.ModuleView;
import cn.srd.library.java.tool.lang.core.*;
import cn.srd.library.java.tool.lang.core.asserts.Assert;
import cn.srd.library.java.tool.lang.core.object.Objects;
import cn.srd.library.java.tool.spring.common.core.SpringsUtil;
import com.mybatisflex.core.FlexGlobalConfig;
import com.test.id.IdGenerateConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class MybatisFlexGlobalConfig {

    @PostConstruct
    public void init() {
        EnableMybatisFlexCustomizer mybatisFlexCustomizer = getEnableMybatisFlexCustomizer();
        IdGenerateConfig globalIdGenerateConfig = mybatisFlexCustomizer.globalIdGenerateConfig();
        FlexGlobalConfig.KeyConfig keyConfig = globalIdGenerateConfig.type().getStrategy().buildConfig(globalIdGenerateConfig);
        FlexGlobalConfig.getDefaultConfig().setKeyConfig(keyConfig);
    }

    private EnableMybatisFlexCustomizer getEnableMybatisFlexCustomizer() {
        // TODO wjm 抽象公共的 AnnotationScanner、以及扫描到后的注解
        log.debug("{}mybatis flex customizer enable, starting configuring...", ModuleView.ORM_MYBATIS_FLEX_SYSTEM);
        Set<Class<?>> classesWithMybatisFlexCustomizerScanner = SpringsUtil.scanPackageByAnnotation(MybatisFlexCustomizerScanner.class);
        Assert.INSTANCE.set(StringsUtil.format("found multiple [@{}] in {}, please specify one", MybatisFlexCustomizerScanner.class.getSimpleName(), CollectionsUtil.toList(classesWithMybatisFlexCustomizerScanner, Class::getName))).throwsIfTrue(classesWithMybatisFlexCustomizerScanner.size() > 1);

        String[] packageNamesToFindMybatisFlexCustomizer = new String[]{SpringsUtil.getRootPackagePath()};
        Set<Class<?>> classesWithMybatisFlexCustomizer;
        if (Objects.isNotEmpty(classesWithMybatisFlexCustomizerScanner)) {
            packageNamesToFindMybatisFlexCustomizer = ArraysUtil.append(packageNamesToFindMybatisFlexCustomizer, AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithMybatisFlexCustomizerScanner), MybatisFlexCustomizerScanner.class));
            classesWithMybatisFlexCustomizer = ClassesUtil.scanPackageByAnnotation(packageNamesToFindMybatisFlexCustomizer, EnableMybatisFlexCustomizer.class);
        } else {
            classesWithMybatisFlexCustomizer = SpringsUtil.scanPackageByAnnotation(EnableMybatisFlexCustomizer.class);
        }
        Assert.INSTANCE.set(StringsUtil.format("found multiple [@{}] in {}, please specify one", EnableMybatisFlexCustomizer.class.getSimpleName(), CollectionsUtil.toList(classesWithMybatisFlexCustomizer, Class::getName))).throwsIfTrue(classesWithMybatisFlexCustomizer.size() > 1);

        return AnnotationsUtil.getAnnotation(CollectionsUtil.getFirst(classesWithMybatisFlexCustomizer), EnableMybatisFlexCustomizer.class);
    }

}
