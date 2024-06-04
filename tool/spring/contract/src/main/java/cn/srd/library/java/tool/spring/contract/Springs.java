// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.contract;

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
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;

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
     * get bean name by bean class from spring ioc.
     *
     * @param beanClass bean class
     * @return bean name
     */
    public static String[] getBeanName(Class<?> beanClass) {
        return SpringUtil.getBeanNamesForType(beanClass);
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
     * see {@link AopContext#currentProxy()}.
     *
     * @param input the specified class to get AOP proxy
     * @param <T>   the specified class type
     * @return the current AOP proxy
     * @apiNote need enable @{@link EnableAspectJAutoProxy}(exposeProxy = true).
     */
    public static <T> T getProxy(Class<T> input) {
        return input.cast(AopContext.currentProxy());
    }

    /**
     * get config item value from project config file, like application.yaml, application.properties
     *
     * @param key config item key
     * @return config item value
     * @see SpringUtil#getProperty(String)
     */
    public static String getProperty(String key) {
        return SpringUtil.getProperty(key);
    }

    /**
     * get config item value from project config file, like application.yaml, application.properties
     *
     * @param key       config item key
     * @param valueType value class
     * @param <T>       value class type
     * @return config item value
     */
    public static <T> T getProperty(String key, Class<T> valueType) {
        return getApplicationContext().getEnvironment().getProperty(key, valueType);
    }

    /**
     * return the package path of class marked {@link SpringBootApplication}
     *
     * @return the package path of class marked {@link SpringBootApplication}
     */
    public static String getSpringBootApplicationPackagePath() {
        if (Nil.isBlank(springBootApplicationPackagePath)) {
            springBootApplicationPackagePath = Classes.getPackagePath(Collections.getMapFirstValue(getBeansWithAnnotation(SpringBootApplication.class)).orElseThrow().getClass());
        }
        return springBootApplicationPackagePath;
    }

    /**
     * return true if the bean from spring ioc is existed.
     *
     * @param input the bean class
     * @param <T>   the bean class type
     * @return return true if the bean from spring ioc is existed
     */
    public static <T> boolean existBean(Class<T> input) {
        return Nil.isNotNull(getBean(input));
    }

    /**
     * return true if the bean from spring ioc is existed.
     *
     * @param input the bean name
     * @return return true if the bean from spring ioc is existed
     */
    public static boolean existBean(String input) {
        return Nil.isNotNull(getBean(input));
    }

    /**
     * return true if the bean from spring ioc is existed.
     *
     * @param input     the bean name
     * @param beanClass the bean class
     * @param <T>       the bean class type
     * @return return true if the bean from spring ioc is existed
     */
    public static <T> boolean existBean(String input, Class<T> beanClass) {
        return Nil.isNotNull(getBean(input, beanClass));
    }

    /**
     * return true if the bean from spring ioc is not existed.
     *
     * @param input the bean class
     * @param <T>   the bean class type
     * @return return true if the bean from spring ioc is not existed
     */
    public static <T> boolean notExistBean(Class<T> input) {
        return !existBean(input);
    }

    /**
     * return true if the bean from spring ioc is not existed.
     *
     * @param input the bean name
     * @return return true if the bean from spring ioc is not existed
     */
    public static boolean notExistBean(String input) {
        return !existBean(input);
    }

    /**
     * return true if the bean from spring ioc is not existed.
     *
     * @param input     the bean name
     * @param beanClass the bean class
     * @param <T>       the bean class type
     * @return return true if the bean from spring ioc is not existed
     */
    public static <T> boolean notExistBean(String input, Class<T> beanClass) {
        return !existBean(input, beanClass);
    }

    /**
     * register bean(singleton) to spring ioc by bean class.
     *
     * @param input the class to register
     * @param <T>   the class type
     */
    public static <T> void registerBean(Class<T> input) {
        registerBean(Strings.lowerFirst(input.getSimpleName()), input);
    }

    /**
     * register bean(singleton) to spring ioc by bean name and bean class.
     *
     * @param beanName the beanName to register
     * @param input    the class to register
     * @param <T>      the class type
     */
    public static <T> void registerBean(String beanName, Class<T> input) {
        // register the bean only if it does not exist in spring ioc.
        if (Nil.isNull(getBean(input))) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(input);
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ANNOTATION_CONFIG_APPLICATION_CONTEXT.getBeanFactory();
            beanFactory.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * register bean(singleton) to spring ioc by bean name and bean instance.
     *
     * @param beanName the beanName to register
     * @param input    the class to register
     * @param <T>      the class type
     */
    public static <T> void registerBean(String beanName, T input) {
        SpringUtil.registerBean(beanName, input);
    }

}