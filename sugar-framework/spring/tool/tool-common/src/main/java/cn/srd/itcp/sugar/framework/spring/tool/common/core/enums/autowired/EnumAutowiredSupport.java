package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception.*;
import cn.srd.itcp.sugar.tool.core.*;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * 对 {@link EnumAutowired} 的实现
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
@Slf4j
public class EnumAutowiredSupport {

    /**
     * <pre>
     * 1、获取标记了 {@link SpringBootApplication} 所在路径下的类中标记了 {@link EnumAutowired} 的所有枚举；
     * 2、获取 {@link EnumAutowired#autowiredBeanClass()} 所有实现类类名；
     * 3、根据是否显式指定了 {@link EnumAutowired#autowiredFiledName()} 获取到要注入的枚举字段名；
     * 4、根据 {@link EnumAutowired#findBeanNamesMayAutowiredRule()} 获取到可能要注入的实现类；
     * 5、根据 {@link EnumAutowired#findBeanNamesMayAutowiredRule()} 获取到最终要注入的实现类；
     * 6、注入；
     * </pre>
     *
     * @param <E> 枚举类类型
     */
    @PostConstruct
    @SuppressWarnings("unchecked")
    public <E extends Enum<E>> void autowired() {
        log.debug("Enum Autowired enable, starting matching...");
        String[] packageNamesToFindEnumAutowired = new String[]{SpringsUtil.getRootPackagePath()};
        Set<Class<?>> classesWithEnumAutowiredScan = SpringsUtil.scanPackageByAnnotation(EnumAutowiredScan.class);
        Set<Class<?>> classesWithEnumAutowired;
        Assert.INSTANCE.set(StringsUtil.format("Find multi [@{}] in [{}], please specify one", EnumAutowiredScan.class.getSimpleName(), CollectionsUtil.toList(classesWithEnumAutowiredScan, Class::getName))).throwsIfTrue(classesWithEnumAutowiredScan.size() > 1);
        if (Objects.isNotEmpty(classesWithEnumAutowiredScan)) {
            packageNamesToFindEnumAutowired = ArraysUtil.append(packageNamesToFindEnumAutowired, AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithEnumAutowiredScan), EnumAutowiredScan.class));
            classesWithEnumAutowired = ClassesUtil.scanPackageByAnnotation(packageNamesToFindEnumAutowired, EnumAutowired.class);
        } else {
            classesWithEnumAutowired = SpringsUtil.scanPackageByAnnotation(EnumAutowired.class);
        }

        if (Objects.isEmpty(classesWithEnumAutowired)) {
            log.debug("No classes with [@{}] found to autowired.", EnumAutowired.class.getSimpleName());
        }

        for (Class<?> classWithEnumAutowired : classesWithEnumAutowired) {
            Objects.requireTrue(new EnumAutowiredUnsupportedException(StringsUtil.format("The class [{}] marked with [@{}] must be an enum class, please check!", classWithEnumAutowired.getSimpleName(), EnumAutowired.class.getSimpleName())), classWithEnumAutowired.isEnum());
            EnumAutowired enumAutowired = AnnotationsUtil.getAnnotation(classWithEnumAutowired, EnumAutowired.class);
            Class<?> autowiredBeanClass = enumAutowired.autowiredBeanClass();
            String classSimpleNameWithEnumAutowired = classWithEnumAutowired.getSimpleName();
            String autowiredBeanClassSimpleName = autowiredBeanClass.getSimpleName();
            Set<String> autowiredBeanChildrenClassSimpleNames = CollectionsUtil.toSet(ClassesUtil.scanPackagesBySuper(packageNamesToFindEnumAutowired, autowiredBeanClass), Class::getSimpleName);
            Assert.INSTANCE.set(new EnumAutowiredBeanImplNotFoundException(StringsUtil.format("The class [{}] marked with [@{}] bound interface [{}] has no implementation class, please check!", classSimpleNameWithEnumAutowired, EnumAutowired.class.getSimpleName(), autowiredBeanClassSimpleName)));

            FindBeanNamesMayAutowiredRule findBeanNamesMayAutowiredRule = ReflectsUtil.newInstance(enumAutowired.findBeanNamesMayAutowiredRule());
            FindBeanNameToAutowiredRule findBeanNameToAutowiredRule = ReflectsUtil.newInstance(enumAutowired.findBeanNameToAutowiredRule());
            EnumsUtil.getEnumValues((Class<E>) classWithEnumAutowired).forEach(
                    // 被注入的枚举
                    internalEnumWithEnumAutowired -> {
                        // 要注入的字段名
                        String fieldNameToAutowired = enumAutowired.autowiredFiledName();
                        if (Objects.isBlank(fieldNameToAutowired)) {
                            List<Field> matchFields = CollectionsUtil.filtersToList(ClassesUtil.getDeclaredFields(internalEnumWithEnumAutowired.getClass()), enumField -> Objects.equals(enumField.getType(), enumAutowired.autowiredBeanClass()));
                            Assert.INSTANCE.set(new EnumAutowiredUnmatchedFieldException(StringsUtil.format("The class [{}] marked with [@{}] has no field match [{}], cannot autowired, please specify one!", classSimpleNameWithEnumAutowired, EnumAutowired.class.getSimpleName(), autowiredBeanClassSimpleName))).throwsIfEmpty(matchFields);
                            Assert.INSTANCE.set(new EnumAutowiredMultiMatchedFieldException(StringsUtil.format("The class [{}] marked with [@{}] has multi fields match [{}], cannot autowired, please specify one!", classSimpleNameWithEnumAutowired, EnumAutowired.class.getSimpleName(), autowiredBeanClassSimpleName))).throwsIfTrue(matchFields.size() > 1);
                            fieldNameToAutowired = CollectionsUtil.getFirst(matchFields).getName();
                        }

                        // 可能要注入的实现类
                        List<String> beanNamesMayAutowired = findBeanNamesMayAutowiredRule.getBeanNamesMayAutowired(internalEnumWithEnumAutowired, autowiredBeanChildrenClassSimpleNames);
                        Assert.INSTANCE.set(new EnumAutowiredMayAutowiredBeansNotFoundException(StringsUtil.format("The class [{}] marked with [@{}] bound interface [{}] has no implementation class that allow matching, please check!", classSimpleNameWithEnumAutowired, EnumAutowired.class.getSimpleName(), autowiredBeanClassSimpleName))).throwsIfEmpty(beanNamesMayAutowired);
                        // 最终要注入的实现类
                        String beanNameToAutowired = findBeanNameToAutowiredRule.getBeanNameToAutowired(beanNamesMayAutowired);
                        Assert.INSTANCE.set(new EnumAutowiredFinallyAutowiredBeanNotFoundException(StringsUtil.format("The class [{}] marked with [@{}] bound interface [{}] has no implementation class that allow autowired, please check!", classSimpleNameWithEnumAutowired, EnumAutowired.class.getSimpleName(), autowiredBeanClassSimpleName))).throwsIfBlank(beanNameToAutowired);

                        Object beanToAutowired = SpringsUtil.getBean(beanNameToAutowired);
                        ReflectsUtil.setFieldValue(internalEnumWithEnumAutowired, fieldNameToAutowired, beanToAutowired);
                        if (Objects.isNotNull(beanToAutowired)) {
                            log.debug("Find class [{}] and autowired it into class [{}] filed [{}]", beanNameToAutowired, classSimpleNameWithEnumAutowired, fieldNameToAutowired);
                        } else {
                            log.warn("Find class [{}] and autowired it into class [{}] filed [{}], but [{}] instance is null, you need to consider adding it to Spring IOC", beanNameToAutowired, classSimpleNameWithEnumAutowired, fieldNameToAutowired, beanNameToAutowired);
                        }
                    }
            );
        }
        log.debug("Enum Autowired finish, exit.");
    }

}
