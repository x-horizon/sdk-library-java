// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.autowired;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Classes;
import cn.srd.library.java.tool.spring.contract.Springs;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * {@link EnumAutowired} core
 *
 * @author wjm
 * @since 2021-09-08 16:07
 */
@Slf4j
public class EnumAutowiredSupport {

    /**
     * the {@link EnumAutowired} core
     *
     * @param <E> the enum marked with {@link EnumAutowired} type
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @PostConstruct
    public <E extends Enum<E>> void support() {
        log.debug("{}starting matching...", ModuleView.ENUM_AUTOWIRED_SYSTEM);

        Set<String> scanPackagePaths = Classes.parseAnnotationAntStylePackagePathToPackagePath(EnableEnumAutowired.class, "scanPackagePaths");
        if (Nil.isEmpty(scanPackagePaths)) {
            log.warn("{}could not found the class marked with [@{}] on these paths {}, exited.", ModuleView.ENUM_AUTOWIRED_SYSTEM, EnableEnumAutowired.class.getSimpleName(), BasePackagePath.get(Springs.getSpringBootApplicationPackagePath()));
            return;
        }

        Set<String> allScanPackagePaths = Classes.getTheLargestRangePackagePath(Collections.add(scanPackagePaths, Springs.getSpringBootApplicationPackagePath()));
        Set<BeanDefinition> enumAutowiredBeanDefinitions = Classes.scanByTypeFilter(new AnnotationTypeFilter(EnumAutowired.class), allScanPackagePaths);
        if (Nil.isEmpty(enumAutowiredBeanDefinitions)) {
            log.debug("{}could not found the class marked with [@{}], exited.", ModuleView.ENUM_AUTOWIRED_SYSTEM, EnumAutowired.class.getSimpleName());
        }

        enumAutowiredBeanDefinitions.forEach(enumAutowiredBeanDefinition -> {
            Class<?> enumAutowiredAnnotatedClass = Classes.ofName(enumAutowiredBeanDefinition.getBeanClassName());
            Assert.of().setMessage(Strings.format("{}the class [{}] marked with [@{}] must be an enum class, please check!", ModuleView.ENUM_AUTOWIRED_SYSTEM, enumAutowiredAnnotatedClass.getSimpleName(), EnumAutowired.class.getSimpleName()))
                    .throwsIfFalse(enumAutowiredAnnotatedClass.isEnum());

            EnumAutowired enumAutowired = Annotations.getAnnotation(enumAutowiredAnnotatedClass, EnumAutowired.class);
            String enumAutowiredAnnotatedClassName = enumAutowiredAnnotatedClass.getSimpleName();
            Class<?> enumAutowiredRootClass = enumAutowired.rootClass();
            String enumAutowiredRootClassName = enumAutowiredRootClass.getSimpleName();
            Set<BeanDefinition> enumAutowiredChildrenClassDefinitions = Classes.scanByTypeFilter(new AssignableTypeFilter(enumAutowiredRootClass), allScanPackagePaths);
            Assert.of().setMessage(Strings.format("{}the class [{}] marked with [@{}] bound interface [{}] has no implementation class, please check!", ModuleView.ENUM_AUTOWIRED_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getSimpleName(), enumAutowiredRootClassName))
                    .throwsIfEmpty(enumAutowiredChildrenClassDefinitions);
            List<String> enumAutowiredSubclassNames = enumAutowiredChildrenClassDefinitions.stream()
                    .map(enumAutowiredChildrenClassDefinition -> Classes.ofName(enumAutowiredChildrenClassDefinition.getBeanClassName()))
                    .map(Class::getSimpleName)
                    .toList();

            String autowiredFiledName = enumAutowired.autowiredFiledName();
            if (Nil.isBlank(autowiredFiledName)) {
                List<Field> matchFields = Classes.getFields(enumAutowiredAnnotatedClass)
                        .stream()
                        .filter(enumAutowiredAnnotatedField -> Comparators.equals(enumAutowiredAnnotatedField.getType(), enumAutowired.rootClass()))
                        .toList();
                Assert.of().setMessage(Strings.format("{}the class [{}] marked with [@{}] has no field to match [{}], cannot autowired, please specified one!", ModuleView.ENUM_AUTOWIRED_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getSimpleName(), enumAutowiredRootClassName))
                        .throwsIfEmpty(matchFields);
                Assert.of().setMessage(Strings.format("{}the class [{}] marked with [@{}] has multi fields to match [{}], cannot autowired, please specified one!", ModuleView.ENUM_AUTOWIRED_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getSimpleName(), enumAutowiredRootClassName))
                        .throwsIfTrue(matchFields.size() > 1);
                autowiredFiledName = Collections.getFirst(matchFields).get().getName();
            }

            for (E enumField : Enums.getAllInstances((Class<E>) enumAutowiredAnnotatedClass)) {
                EnumAutowiredFieldMatchRule enumAutowiredFieldMatchRule = Reflects.newInstance(enumAutowired.matchRule());
                String theMostSuitableAutowiredClassName = enumAutowiredFieldMatchRule.getMostSuitableAutowiredClassName(enumField, enumAutowiredSubclassNames);
                Object theMostSuitableAutowiredClass = Springs.getBean(theMostSuitableAutowiredClassName);
                Assert.of().setMessage(Strings.format("{}find class [{}] and autowired it into class [{}] filed [{}], but [{}] instance is null, you need to consider adding it to Spring IOC", ModuleView.ENUM_AUTOWIRED_SYSTEM, theMostSuitableAutowiredClassName, enumAutowiredAnnotatedClassName, autowiredFiledName, theMostSuitableAutowiredClassName))
                        .throwsIfNull(theMostSuitableAutowiredClass);
                Reflects.setFieldValue(enumField, autowiredFiledName, theMostSuitableAutowiredClass);
                log.debug("{}Find class [{}] and autowired it into class [{}] filed [{}]", ModuleView.ENUM_AUTOWIRED_SYSTEM, theMostSuitableAutowiredClassName, enumAutowiredAnnotatedClassName, autowiredFiledName);
            }
        });

        log.debug("{}autowired finish, exit.", ModuleView.ENUM_AUTOWIRED_SYSTEM);
    }

}
