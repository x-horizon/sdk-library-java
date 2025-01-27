package cn.library.java.tool.enums.strategy;

import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.library.java.tool.enums.EnumAutowired;
import cn.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import cn.library.java.tool.lang.collection.Collections;
import cn.library.java.tool.lang.compare.Comparators;
import cn.library.java.tool.lang.enums.Enums;
import cn.library.java.tool.lang.functional.Assert;
import cn.library.java.tool.lang.object.Nil;
import cn.library.java.tool.lang.reflect.Reflects;
import cn.library.java.tool.spring.contract.support.Annotations;
import cn.library.java.tool.spring.contract.support.Classes;
import cn.library.java.tool.spring.contract.support.Springs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * {@link EnumAutowired} collector
 *
 * @author wjm
 * @since 2021-09-08 16:07
 */
@Slf4j
@Order(1)
public class EnumAutowiredCollector<E extends Enum<E>> implements SmartInitializingSingleton {

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    public void afterSingletonsInstantiated() {
        log.debug("{}enum autowired system is enabled, starting initializing...", ModuleView.TOOL_ENUM_SYSTEM);

        Set<String> scanPackagePaths = Classes.optimizeAnnotationAntStylePackagePaths(EnableEnumAutowired.class, "scanPackagePaths");
        Set<BeanDefinition> enumAutowiredBeanDefinitions = Classes.scanByAnnotationTypeFilter(EnumAutowired.class, scanPackagePaths);
        if (Nil.isEmpty(enumAutowiredBeanDefinitions)) {
            log.debug("{}no class marked with [@{}].", ModuleView.TOOL_ENUM_SYSTEM, EnumAutowired.class.getName());
        }

        enumAutowiredBeanDefinitions.forEach(enumAutowiredBeanDefinition -> {
            Class<?> enumAutowiredAnnotatedClass = Classes.ofName(enumAutowiredBeanDefinition.getBeanClassName());
            Assert.of().setMessage("{}the class [{}] marked with [@{}] must be an enum class, please check!", ModuleView.TOOL_ENUM_SYSTEM, enumAutowiredAnnotatedClass.getName(), EnumAutowired.class.getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfFalse(enumAutowiredAnnotatedClass.isEnum());

            EnumAutowired enumAutowired = Annotations.getAnnotation(enumAutowiredAnnotatedClass, EnumAutowired.class);
            String enumAutowiredAnnotatedClassName = enumAutowiredAnnotatedClass.getName();
            Arrays.stream(enumAutowired.rootClasses()).forEach(enumAutowiredRootClass -> {
                String enumAutowiredRootClassName = enumAutowiredRootClass.getName();
                Set<BeanDefinition> enumAutowiredChildrenClassDefinitions = Classes.scanByAssignableTypeFilter(enumAutowiredRootClass, scanPackagePaths);
                Assert.of().setMessage("{}the class [{}] marked with [@{}] bound interface [{}] has no implementation class, please check!", ModuleView.TOOL_ENUM_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getName(), enumAutowiredRootClassName)
                        .setThrowable(LibraryJavaInternalException.class)
                        .throwsIfTrue(!enumAutowired.allowNull() && Nil.isEmpty(enumAutowiredChildrenClassDefinitions));

                String autowiredFiledName = enumAutowired.autowiredFiledName();
                if (Nil.isBlank(autowiredFiledName)) {
                    List<Field> matchFields = Classes.getFields(enumAutowiredAnnotatedClass)
                            .stream()
                            .filter(enumAutowiredAnnotatedField -> Comparators.equals(enumAutowiredAnnotatedField.getType(), enumAutowiredRootClass))
                            .toList();
                    Assert.of().setMessage("{}the class [{}] marked with [@{}] has no field to match [{}], cannot autowired, please specified one!", ModuleView.TOOL_ENUM_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getName(), enumAutowiredRootClassName)
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfEmpty(matchFields);
                    Assert.of().setMessage("{}the class [{}] marked with [@{}] has multi fields to match [{}], cannot autowired, please specified one!", ModuleView.TOOL_ENUM_SYSTEM, enumAutowiredAnnotatedClassName, EnumAutowired.class.getName(), enumAutowiredRootClassName)
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfTrue(matchFields.size() > 1);
                    autowiredFiledName = Collections.getFirst(matchFields).orElseThrow().getName();
                }

                Map<String, String> enumAutowiredSubclassSimpleNameMappingNameMap = enumAutowiredChildrenClassDefinitions.stream()
                        .map(enumAutowiredChildrenClassDefinition -> Classes.ofName(enumAutowiredChildrenClassDefinition.getBeanClassName()))
                        .map(enumAutowiredChildrenClass -> Collections.ofPair(enumAutowiredChildrenClass.getSimpleName(), enumAutowiredChildrenClass.getName()))
                        .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

                for (E enumField : Enums.getAllInstances((Class<E>) enumAutowiredAnnotatedClass)) {
                    EnumAutowiredFieldMatchRule enumAutowiredFieldMatchRule = Reflects.newInstance(enumAutowired.matchRule());
                    String theMostSuitableAutowiredClassSimpleName = enumAutowiredFieldMatchRule.getMostSuitableAutowiredClassSimpleName(enumField, Collections.getMapKeys(enumAutowiredSubclassSimpleNameMappingNameMap));
                    if (Nil.isBlank(theMostSuitableAutowiredClassSimpleName)) {
                        Assert.of().setMessage("{} could not find class to autowired into enum [{}]-[{}], if allow null value, you should set allowNull = true, please check!", ModuleView.TOOL_ENUM_SYSTEM, enumAutowiredAnnotatedClassName, enumField.name(), autowiredFiledName)
                                .setThrowable(LibraryJavaInternalException.class)
                                .throwsIfFalse(enumAutowired.allowNull());
                        Reflects.setFieldValue(enumField, autowiredFiledName, null);
                        log.debug("{}find class [null] and autowired it into enum [{}]-[{}] filed [{}]", ModuleView.TOOL_ENUM_SYSTEM, enumAutowiredAnnotatedClassName, enumField.name(), autowiredFiledName);
                        continue;
                    }
                    Class<?> theMostSuitableAutowiredClass = Classes.ofName(enumAutowiredSubclassSimpleNameMappingNameMap.get(theMostSuitableAutowiredClassSimpleName));
                    Object theMostSuitableAutowiredClassInstance = Springs.getBean(theMostSuitableAutowiredClass);
                    if (Nil.isNull(theMostSuitableAutowiredClassInstance)) {
                        Springs.registerBean(theMostSuitableAutowiredClass);
                        theMostSuitableAutowiredClassInstance = Springs.getBean(theMostSuitableAutowiredClass);
                    }
                    Assert.of().setMessage("{}find class [{}] and autowired it into enum [{}]-[{}] filed [{}], but failed to register to spring ioc, you need to add it into spring ioc by yourself.", ModuleView.TOOL_ENUM_SYSTEM, theMostSuitableAutowiredClassSimpleName, enumAutowiredAnnotatedClassName, enumField.name(), autowiredFiledName, theMostSuitableAutowiredClassSimpleName)
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfNull(theMostSuitableAutowiredClassInstance);
                    Reflects.setFieldValue(enumField, autowiredFiledName, theMostSuitableAutowiredClassInstance);
                    log.debug("{}find class [{}] and autowired it into enum [{}]-[{}] filed [{}]", ModuleView.TOOL_ENUM_SYSTEM, theMostSuitableAutowiredClassSimpleName, enumAutowiredAnnotatedClassName, enumField.name(), autowiredFiledName);
                }
            });
        });

        log.debug("{}enum autowired system initialized.", ModuleView.TOOL_ENUM_SYSTEM);
    }

}