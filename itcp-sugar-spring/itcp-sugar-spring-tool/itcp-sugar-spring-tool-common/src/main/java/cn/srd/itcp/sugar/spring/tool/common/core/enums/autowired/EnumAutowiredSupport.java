package cn.srd.itcp.sugar.spring.tool.common.core.enums.autowired;

import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.spring.tool.common.core.enums.autowired.exception.*;
import cn.srd.itcp.sugar.tool.core.*;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import jakarta.annotation.PostConstruct;
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
        String[] packageNamesToFindEnumAutowired = new String[]{SpringsUtil.getRootPackagePath()};
        Set<Class<?>> classesWithEnumAutowiredScan = SpringsUtil.scanPackageByAnnotation(EnumAutowiredScan.class);
        Set<Class<?>> classesWithEnumAutowired;
        Assert.INSTANCE.set(StringsUtil.format("found multi @{} in {}, please just specifies one", EnumAutowiredScan.class.getSimpleName(), CollectionsUtil.toList(classesWithEnumAutowiredScan, Class::getName))).throwsIfTrue(classesWithEnumAutowiredScan.size() > 1);
        if (Objects.isNotEmpty(classesWithEnumAutowiredScan)) {
            packageNamesToFindEnumAutowired = ArraysUtil.append(packageNamesToFindEnumAutowired, AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithEnumAutowiredScan), EnumAutowiredScan.class));
            classesWithEnumAutowired = ClassesUtil.scanPackageByAnnotation(packageNamesToFindEnumAutowired, EnumAutowired.class);
        } else {
            classesWithEnumAutowired = SpringsUtil.scanPackageByAnnotation(EnumAutowired.class);
        }

        for (Class<?> classWithEnumAutowired : classesWithEnumAutowired) {
            Objects.requireTrue(new EnumAutowiredUnsupportedException(StringsUtil.format("标记了 @EnumAutowired 的 “{}” 必须为 Enum 类型", classWithEnumAutowired.getSimpleName())), classWithEnumAutowired.isEnum());
            EnumAutowired enumAutowired = AnnotationsUtil.getAnnotation(classWithEnumAutowired, EnumAutowired.class);
            Class<?> autowiredBeanClass = enumAutowired.autowiredBeanClass();
            String classSimpleNameWithEnumAutowired = classWithEnumAutowired.getSimpleName();
            String autowiredBeanClassSimpleName = autowiredBeanClass.getSimpleName();
            Set<String> autowiredBeanChildrenClassSimpleNames = CollectionsUtil.toSet(ClassesUtil.scanPackagesBySuper(packageNamesToFindEnumAutowired, autowiredBeanClass), Class::getSimpleName);
            Assert.INSTANCE.set(new EnumAutowiredBeanImplNotFoundException(StringsUtil.format("在 “{}” 中绑定的接口 “{}” 没有实现类，请检查！", classSimpleNameWithEnumAutowired, autowiredBeanClassSimpleName))).throwsIfEmpty(autowiredBeanChildrenClassSimpleNames);

            FindBeanNamesMayAutowiredRule findBeanNamesMayAutowiredRule = ReflectsUtil.newInstance(enumAutowired.findBeanNamesMayAutowiredRule());
            FindBeanNameToAutowiredRule findBeanNameToAutowiredRule = ReflectsUtil.newInstance(enumAutowired.findBeanNameToAutowiredRule());
            EnumsUtil.getEnumValues((Class<E>) classWithEnumAutowired).forEach(
                    // 被注入的枚举
                    internalEnumWithEnumAutowired -> {
                        // 要注入的字段名
                        String fieldNameToAutowired = enumAutowired.autowiredFiledName();
                        if (Objects.isBlank(fieldNameToAutowired)) {
                            List<Field> matchFields = CollectionsUtil.filtersToList(ClassesUtil.getDeclaredFields(internalEnumWithEnumAutowired.getClass()), enumField -> Objects.equals(enumField.getType(), enumAutowired.autowiredBeanClass()));
                            Assert.INSTANCE.set(new EnumAutowiredUnmatchedFieldException(StringsUtil.format("在 “{}” 中无法找到与 “{}” 相匹配的字段，无法注入，请检查！", classSimpleNameWithEnumAutowired, autowiredBeanClassSimpleName))).throwsIfEmpty(matchFields);
                            Assert.INSTANCE.set(new EnumAutowiredMultiMatchedFieldException(StringsUtil.format("在 “{}” 中找到多个与 “{}” 相匹配的字段，无法注入，请检查！", classSimpleNameWithEnumAutowired, autowiredBeanClassSimpleName))).throwsIfTrue(matchFields.size() > 1);
                            fieldNameToAutowired = CollectionsUtil.getFirst(matchFields).getName();
                        }

                        // 可能要注入的实现类
                        List<String> beanNamesMayAutowired = findBeanNamesMayAutowiredRule.getBeanNamesMayAutowired(internalEnumWithEnumAutowired, autowiredBeanChildrenClassSimpleNames);
                        Assert.INSTANCE.set(new EnumAutowiredMayAutowiredBeansNotFoundException(StringsUtil.format("在 “{}” 中无法找到实现了 “{}” 并且允许匹配的实现类，无法注入，请检查！", classSimpleNameWithEnumAutowired, autowiredBeanClassSimpleName))).throwsIfEmpty(beanNamesMayAutowired);
                        // 最终要注入的实现类
                        String beanNameToAutowired = findBeanNameToAutowiredRule.getBeanNameToAutowired(beanNamesMayAutowired);
                        Assert.INSTANCE.set(new EnumAutowiredAutowiredBeanNotFoundException(StringsUtil.format("在 “{}” 中无法找到实现了 “{}” 并且允许注入的实现类，无法注入，请检查！", classSimpleNameWithEnumAutowired, autowiredBeanClassSimpleName))).throwsIfBlank(beanNameToAutowired);

                        ReflectsUtil.setFieldValue(internalEnumWithEnumAutowired, fieldNameToAutowired, SpringsUtil.getBean(beanNameToAutowired));
                    }
            );
        }
    }

}
