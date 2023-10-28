// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
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
        registerBean(Strings.lowerFirst(input.getSimpleName()), input);
    }

    /**
     * register bean(singleton) to spring ioc.
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

}
