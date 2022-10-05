package cn.srd.itcp.sugar.tools.core;

import cn.hutool.extra.spring.SpringUtil;
import cn.srd.itcp.sugar.tools.constant.StringPool;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * Spring 工具
 *
 * @author wjm
 * @since 2020/8/9 11:12
 */
public class SpringsUtil extends SpringUtil {

    private SpringsUtil() {
    }

    /**
     * {@link SpringBootApplication} 启动类所在的包路径
     */
    private static String rootPackagePath = StringPool.EMPTY;

    /**
     * 通过 beanName 获取 Bean（支持大写字母开头、支持不符合驼峰规范命名的 beanName）
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return Option.of(Try.of(() -> SpringUtil.getBean(StringsUtil.lowerFirst(beanName))).getOrNull())
                .orElse(() -> Option.of(Try.of(() -> SpringUtil.getConfigurableBeanFactory().getBean(beanName)).getOrNull()))
                .getOrNull();
    }

    /**
     * 通过 beanName，以及 beanClass 获取指定的 Bean（支持大写字母开头）
     *
     * @param beanName
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        try {
            return SpringUtil.getBean(StringsUtil.lowerFirst(beanName), beanClass);
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 通过 beanClass 获取 Bean
     *
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> beanClass) {
        try {
            return SpringUtil.getBean(beanClass);
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 获取加入 IOC 容器中被【指定注解】注解了的Bean
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
     * 扫描被 {@link SpringBootApplication} 注解了的启动类包下所有指定类或接口的子类或实现类
     *
     * @param superClass 父类或接口
     * @return 类集合
     */
    public static Set<Class<?>> scanPackagesBySuper(final Class<?> superClass) {
        try {
            return ClassesUtil.scanPackagesBySuper(new String[]{getRootPackagePath()}, superClass);
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
            rootPackagePath = ClassesUtil.getPackage(Objects.requireNotNull(() -> "不存在 @SpringBootApplication，请检查！", CollectionsUtil.getFirstValue(
                    SpringsUtil.getBeansMapWithAnnotation(SpringBootApplication.class)
            )).getClass());
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
    public static <T> T wrapProxy(Class<T> clazz) {
        return clazz.cast(AopContext.currentProxy());
    }

    /**
     * 向 IOC 注册 bean，使用类名首字母小写作为 bean name
     *
     * @param clazz 要注册的 bean
     * @param <T>   要注册的 bean 类型
     */
    public static <T> void registerBean(Class<T> clazz) {
        registerBean(StringsUtil.lowerFirst(clazz.getSimpleName()), clazz);
    }

    /**
     * 向 IOC 注册 bean，并返回注册后的 bean，使用类名首字母小写作为 bean name
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
