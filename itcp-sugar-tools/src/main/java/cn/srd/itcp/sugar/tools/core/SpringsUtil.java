package cn.srd.itcp.sugar.tools.core;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * Spring工具
 *
 * @author wjm
 * @date 2020/8/9 11:12
 */
public class SpringsUtil extends SpringUtil {

    private SpringsUtil() {
    }

    /**
     * {@link SpringBootApplication} 启动类所在的包路径
     */
    private static String rootPackagePath = null;

    /**
     * 通过 name 获取 Bean（支持大写字母开头）
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        return SpringUtil.getBean(StringsUtil.lowerFirst(name));
    }

    /**
     * 通过 name，以及 Class 获取指定的 Bean（支持大写字母开头）
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return SpringUtil.getBean(StringsUtil.lowerFirst(name), clazz);
    }

    /**
     * 获取加入IOC容器中被指定注解 注解了的Bean
     *
     * @param annotationClass
     * @return
     */
    public static Map<String, Object> getBeansMapWithAnnotation(Class<? extends Annotation> annotationClass) {
        return getApplicationContext().getBeansWithAnnotation(annotationClass);
    }

    /**
     * 扫描被 {@link SpringBootApplication} 注解了的启动类包下被指定注解 注解了的类
     *
     * @param annotationClass
     * @return
     */
    public static Set<Class<?>> scanPackageByAnnotation(Class<? extends Annotation> annotationClass) {
        try {
            return ClassesUtil.scanPackageByAnnotation(getRootPackagePath(), annotationClass);
        } catch (Exception ignore) {
            return CollectionsUtil.newHashSet();
        }
    }

    /**
     * 获取 @SpringBootApplication 启动类所在的包路径
     *
     * @return
     */
    public static String getRootPackagePath() {
        if (Objects.isNull(rootPackagePath)) {
            rootPackagePath = ClassesUtil.getPackage(Objects.requireNotNull(
                    () -> "不存在 @SpringBootApplication，请检查！",
                    CollectionsUtil.getFirstValue(SpringsUtil.getBeansMapWithAnnotation(SpringBootApplication.class))
            ).getClass());
        }
        return rootPackagePath;
    }

    /**
     * 获取当前代理的对象，启动类须开启 @EnableAspectJAutoProxy(exposeProxy = true)
     *
     * @param clazz 要获取的对象类型
     * @param <T>   对象类型
     * @return 当前代理的对象
     */
    public static <T> T getCurrentProxy(Class<T> clazz) {
        return clazz.cast(AopContext.currentProxy());
    }

    /**
     * 向 IOC 注册 bean，并返回注册后的 bean
     * <pre>
     * 该方法支持注册带有特定注解功能的 bean，例如如下 bean 未被 spring 扫描到，使用该方法注册后该 bean 将具备 {@link org.springframework.web.bind.annotation.RestControllerAdvice RestControllerAdvice} 的功能
     * &#064;{@link org.springframework.web.bind.annotation.RestControllerAdvice RestControllerAdvice}
     * public class WebExceptionHandler {
     *
     * }
     * </pre>
     *
     * @param clazz 要注册的 bean
     * @param <T>   要注册的 bean 类型
     * @return 注册后的 bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T registerCapableBean(Class<T> clazz) {
        String lowerFirstClassSimpleName = StringsUtil.lowerFirst(clazz.getSimpleName());
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(clazz);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(clazz);
        beanFactory.registerBeanDefinition(lowerFirstClassSimpleName, beanDefinition);
        return (T) applicationContext.getBean(lowerFirstClassSimpleName);
    }

}
