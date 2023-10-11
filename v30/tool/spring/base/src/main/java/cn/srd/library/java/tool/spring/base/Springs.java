// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.base;

import cn.hutool.extra.spring.SpringUtil;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.type.filter.TypeFilter;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

/**
 * toolkit for spring
 *
 * @author wjm
 * @since 2020-08-09 11:12
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Springs {

    /**
     * the package path of class marked {@link SpringBootApplication}
     */
    private static String springBootApplicationPackagePath = SymbolConstant.EMPTY;

    /**
     * the customer spring context, used to dynamically register beans at runtime, see {@link #registerBean(Class)}、{@link #getBean(Class)}
     */
    private static final AnnotationConfigApplicationContext ANNOTATION_CONFIG_APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(Springs.class);

    /**
     * get {@link ApplicationContext}
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        return SpringUtil.getApplicationContext();
    }

    /**
     * get bean from spring ioc.
     *
     * @param input the bean class
     * @param <T>   the bean class type
     * @return the bean
     */
    public static <T> T getBean(Class<T> input) {
        return Optional.ofNullable(Try.of(() -> SpringUtil.getBean(input)).getOrNull())
                .orElse(Try.of(() -> ANNOTATION_CONFIG_APPLICATION_CONTEXT.getBean(input)).getOrNull());
    }

    /**
     * get bean from spring ioc.
     *
     * @param input the bean name
     * @return the bean
     */
    public static Object getBean(String input) {
        String beanName = Strings.lowerFirst(input);
        return Optional.ofNullable(Try.of(() -> SpringUtil.getBean(beanName)).getOrNull())
                .orElse(Try.of(() -> ANNOTATION_CONFIG_APPLICATION_CONTEXT.getBean(beanName)).getOrNull());
    }

    /**
     * get bean from spring ioc.
     *
     * @param input     the bean name
     * @param beanClass the bean class
     * @param <T>       the bean class type
     * @return the bean
     */
    public static <T> T getBean(String input, Class<T> beanClass) {
        String beanName = Strings.lowerFirst(input);
        return Optional.ofNullable(Try.of(() -> SpringUtil.getBean(beanName, beanClass)).getOrNull())
                .orElse(Try.of(() -> ANNOTATION_CONFIG_APPLICATION_CONTEXT.getBean(beanName, beanClass)).getOrNull());
    }

    /**
     * return all beans which are annotated with the supplied annotation class.
     *
     * @param annotationClass the specified annotation class
     * @return bean name mapping bean instance
     * @see ListableBeanFactory#getBeansWithAnnotation(Class)
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationClass) {
        return getApplicationContext().getBeansWithAnnotation(annotationClass);
    }

    /**
     * <pre>
     * see {@link AopContext#currentProxy()}.
     * note: need enable @{@link EnableAspectJAutoProxy}(exposeProxy = true).
     * </pre>
     *
     * @param input the specified class to get AOP proxy
     * @param <T>   the specified class type
     * @return the current AOP proxy
     */
    public static <T> T getProxy(Class<T> input) {
        return input.cast(AopContext.currentProxy());
    }

    /**
     * return the package path of class marked {@link SpringBootApplication}
     *
     * @return the package path of class marked {@link SpringBootApplication}
     */
    public static String getSpringBootApplicationPackagePath() {
        if (Nil.isBlank(springBootApplicationPackagePath)) {
            springBootApplicationPackagePath = Classes.getPackagePath(Collections.getFirstValue(getBeansWithAnnotation(SpringBootApplication.class)).orElseThrow().getClass());
        }
        return springBootApplicationPackagePath;
    }

    /**
     * register bean(singleton) to spring ioc.
     *
     * @param input the class to register
     * @param <T>   the class type
     */
    public static <T> void registerBean(Class<T> input) {
        // register the bean only if it does not exist in spring ioc.
        if (Nil.isNull(getBean(input))) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(input);
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ANNOTATION_CONFIG_APPLICATION_CONTEXT.getBeanFactory();
            beanFactory.registerBeanDefinition(Strings.lowerFirst(input.getSimpleName()), beanDefinition);
        }
    }

    /**
     * <pre>
     * scan all classes containing the specified annotation in the package path of class marked {@link SpringBootApplication}.
     *
     * note:
     * this function scans every time and has poor performance.
     * recommended to use {@link #scanByTypeFilter(List, Set)} function.
     * </pre>
     *
     * @param annotationClass the specified annotation class
     * @return all classes containing the specified annotation in the package path of class marked {@link SpringBootApplication}
     * @see Classes#scanByAnnotation(Class, String...)
     */
    public static Set<Class<?>> scanByAnnotation(Class<? extends Annotation> annotationClass) {
        return Try.of(() -> Classes.scanByAnnotation(annotationClass, getSpringBootApplicationPackagePath())).getOrElse(Collections.newHashSet());
    }

    /**
     * <pre>
     * scan all subclasses or implementation classes of the specified class or interface in the package path of class marked {@link SpringBootApplication}.
     *
     * note:
     * this function scans every time and has poor performance.
     * recommended to use {@link #scanByTypeFilter(Collection, Collection)} function.
     * </pre>
     *
     * @param rootClass the specified class
     * @param <T>       the specified class type
     * @return all subclasses or implementation classes of the specified class or interface in the package path of class marked {@link SpringBootApplication}
     * @see Classes#scanBySuper(Class, String...)
     */
    public static <T> Set<Class<? extends T>> scanBySuper(Class<T> rootClass) {
        return Try.of(() -> Classes.scanBySuper(rootClass, getSpringBootApplicationPackagePath())).getOrElse(Collections.newHashSet());
    }

    /**
     * see {@link #scanByTypeFilter(Collection, Collection)}
     *
     * @param includeFilter    the specified {@link TypeFilter}
     * @param scanPackagePaths the specified package paths
     * @return see {@link #scanByTypeFilter(Collection, Collection)}
     */
    public static Set<BeanDefinition> scanByTypeFilter(TypeFilter includeFilter, String... scanPackagePaths) {
        return scanByTypeFilter(includeFilter, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * see {@link #scanByTypeFilter(Collection, Collection)}
     *
     * @param includeFilter    the specified {@link TypeFilter}
     * @param scanPackagePaths the specified package paths
     * @return see {@link #scanByTypeFilter(Collection, Collection)}
     */
    public static Set<BeanDefinition> scanByTypeFilter(TypeFilter includeFilter, Collection<String> scanPackagePaths) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(includeFilter);
        return scanPackagePaths.stream().map(scanner::findCandidateComponents).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * see {@link #scanByTypeFilter(Collection, Collection)}
     *
     * @param includeFilters   the specified {@link TypeFilter}s
     * @param scanPackagePaths the specified package paths
     * @return see {@link #scanByTypeFilter(Collection, Collection)}
     */
    public static Set<BeanDefinition> scanByTypeFilter(Collection<TypeFilter> includeFilters, String... scanPackagePaths) {
        return scanByTypeFilter(includeFilters, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * scan all {@link BeanDefinition} by the specified {@link TypeFilter}s in the specified packages paths.
     *
     * for example:
     * {@code
     *
     *     // define an interface
     *     public interface GenderStrategy {
     *
     *     }
     *
     *     // define the man implementation
     *     public class GenderManStrategy implements GenderStrategy {
     *
     *     }
     *
     *     // define the woman implementation
     *     public class GenderWomanStrategy implements GenderStrategy {
     *
     *     }
     *
     *     // define the unknown implementation
     *     public class GenderUnknownStrategy implements GenderStrategy {
     *
     *     }
     *
     *     // define enum with a customer annotation such as @EnumAutowired
     *     @Getter
     *     @EnumAutowired(autowiredBeanClass = GenderStrategy.class)
     *     public enum GenderType {
     *
     *         WOMAN(1, "woman"),
     *         MAN(2, "man"),
     *         UNKNOWN(3, "unknown"),
     *
     *         ;
     *
     *         GenderType(int code, String description) {
     *             this.code = code;
     *             this.description = description;
     *         }
     *
     *         private final int code;
     *
     *         private final String description;
     *
     *         private GenderStrategy strategy;
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *         public static void main(String[] args) {
     *             // the output is a set {@link BeanDefinition} contains [GenderWomanStrategy, GenderManStrategy, GenderUnknownStrategy, GenderType]
     *             Springs.scanByTypeFilter(
     *                     Collections.ofArrayList(
     *                             // the annotation type filter, it will scan all objects containing the specified annotation in the specified package paths.
     *                             new AnnotationTypeFilter(EnumAutowired.class),
     *                             // the annotation type filter, it will scan all subclasses or implementation classes in the specified package paths.(not contains parent class)
     *                             new AssignableTypeFilter(GenderStrategy.class)
     *                     ),
     *                     // the specified package paths
     *                     "cn.xx1", "cn.xx2", "cn.xx3"
     *             );
     *         }
     *     }
     * }
     * </pre>
     *
     * @param includeFilters   the specified {@link TypeFilter}s
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition} by the specified {@link TypeFilter}s in the specified packages paths.
     */
    public static Set<BeanDefinition> scanByTypeFilter(Collection<TypeFilter> includeFilters, Collection<String> scanPackagePaths) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        includeFilters.forEach(scanner::addIncludeFilter);
        return scanPackagePaths.stream().map(scanner::findCandidateComponents).flatMap(Collection::stream).collect(Collectors.toSet());
    }

}
