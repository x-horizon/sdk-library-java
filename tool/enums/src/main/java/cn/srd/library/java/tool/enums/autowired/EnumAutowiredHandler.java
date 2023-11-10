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
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.Classes;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;

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
public class EnumAutowiredHandler<E extends Enum<E>> implements SmartInitializingSingleton {

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    public void afterSingletonsInstantiated() {
        log.debug("{} enum autowired starting matching...", ModuleView.ENUM_SYSTEM);

        Set<String> scanPackagePaths = Classes.optimizeAnnotationAntStylePackagePath(EnableEnumAutowired.class, "scanPackagePaths");
        Set<BeanDefinition> enumAutowiredBeanDefinitions = Classes.scanByAnnotationTypeFilter(EnumAutowired.class, scanPackagePaths);
        if (Nil.isEmpty(enumAutowiredBeanDefinitions)) {
            log.debug("{}could not found the class marked with [@{}], exited.", ModuleView.ENUM_SYSTEM, EnumAutowired.class.getName());
        }

        enumAutowiredBeanDefinitions.forEach(enumAutowiredBeanDefinition -> {
            Class<?> enumAutowiredAnnotatedClass = Classes.ofName(enumAutowiredBeanDefinition.getBeanClassName());
            Assert.of().setMessage("{}the class [{}] marked with [@{}] must be an enum class, please check!", ModuleView.ENUM_SYSTEM, enumAutowiredAnnotatedClass.getName(), EnumAutowired.class.getName())
                    .throwsIfFalse(enumAutowiredAnnotatedClass.isEnum());

            EnumAutowired enumAutowired = Annotations.getAnnotation(enumAutowiredAnnotatedClass, EnumAutowired.class);
            String enumAutowiredAnnotatedClassName = enumAutowiredAnnotatedClass.getName();
            Class<?> enumAutowiredRootClass = enumAutowired.rootClass();
            String enumAutowiredRootClassName = enumAutowiredRootClass.getName();
            Set<BeanDefinition> enumAutowiredChildrenClassDefinitions = Classes.scanByAssignableTypeFilter(enumAutowiredRootClass, scanPackagePaths);
            Assert.of().setMessage("{}the class [{}] marked with [@{}] bound interface [{}] has no implementation class, please check!", ModuleView.ENUM_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getName(), enumAutowiredRootClassName)
                    .throwsIfEmpty(enumAutowiredChildrenClassDefinitions);
            List<String> enumAutowiredSubclassNames = enumAutowiredChildrenClassDefinitions.stream()
                    .map(enumAutowiredChildrenClassDefinition -> Classes.ofName(enumAutowiredChildrenClassDefinition.getBeanClassName()))
                    .map(Class::getName)
                    .toList();

            String autowiredFiledName = enumAutowired.autowiredFiledName();
            if (Nil.isBlank(autowiredFiledName)) {
                List<Field> matchFields = Classes.getFields(enumAutowiredAnnotatedClass)
                        .stream()
                        .filter(enumAutowiredAnnotatedField -> Comparators.equals(enumAutowiredAnnotatedField.getType(), enumAutowired.rootClass()))
                        .toList();
                Assert.of().setMessage("{}the class [{}] marked with [@{}] has no field to match [{}], cannot autowired, please specified one!", ModuleView.ENUM_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getName(), enumAutowiredRootClassName)
                        .throwsIfEmpty(matchFields);
                Assert.of().setMessage("{}the class [{}] marked with [@{}] has multi fields to match [{}], cannot autowired, please specified one!", ModuleView.ENUM_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getName(), enumAutowiredRootClassName)
                        .throwsIfTrue(matchFields.size() > 1);
                autowiredFiledName = Collections.getFirst(matchFields).get().getName();
            }

            for (E enumField : Enums.getAllInstances((Class<E>) enumAutowiredAnnotatedClass)) {
                EnumAutowiredFieldMatchRule enumAutowiredFieldMatchRule = Reflects.newInstance(enumAutowired.matchRule());
                String theMostSuitableAutowiredClassName = enumAutowiredFieldMatchRule.getMostSuitableAutowiredClassName(enumField, enumAutowiredSubclassNames);
                Object theMostSuitableAutowiredClass = Springs.getBean(Classes.ofName(theMostSuitableAutowiredClassName));
                Assert.of().setMessage("{}find class [{}] and autowired it into enum [{}]-[{}] filed [{}], but the [{}] instance is null, you need to consider adding it to Spring IOC", ModuleView.ENUM_SYSTEM, theMostSuitableAutowiredClassName, enumAutowiredAnnotatedClassName, enumField.name(), autowiredFiledName, theMostSuitableAutowiredClassName)
                        .throwsIfNull(theMostSuitableAutowiredClass);
                Reflects.setFieldValue(enumField, autowiredFiledName, theMostSuitableAutowiredClass);
                log.debug("{}find class [{}] and autowired it into enum [{}]-[{}] filed [{}]", ModuleView.ENUM_SYSTEM, theMostSuitableAutowiredClassName, enumAutowiredAnnotatedClassName, enumField.name(), autowiredFiledName);
            }
        });

        log.debug("{}enum autowired finish, exit.", ModuleView.ENUM_SYSTEM);
    }

}
