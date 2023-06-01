package cn.srd.itcp.sugar.framework.spring.tool.common.core;

import cn.hutool.extra.spring.SpringUtil;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
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

    /**
     * private block constructor
     */
    private SpringsUtil() {
    }

    /**
     * {@link SpringBootApplication} 启动类所在的包路径
     */
    private static String rootPackagePath = StringPool.EMPTY;

    /**
     * 通过 beanName 获取 Bean（支持大写字母开头、支持不符合驼峰规范命名的 beanName）
     *
     * @param beanName bean 名称
     * @return bean 对象
     */
    public static Object getBean(String beanName) {
        return Option.of(Try.of(() -> SpringUtil.getBean(StringsUtil.lowerFirst(beanName))).getOrNull())
                .orElse(() -> Option.of(Try.of(() -> SpringUtil.getConfigurableBeanFactory().getBean(beanName)).getOrNull()))
                .getOrNull();
    }

    /**
     * 通过 beanName，以及 beanClass 获取指定的 Bean（支持大写字母开头）
     *
     * @param beanName  bean 名称
     * @param beanClass bean 类
     * @param <T>       bean 类类型
     * @return bean 对象
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return Try.of(() -> SpringUtil.getBean(StringsUtil.lowerFirst(beanName), beanClass)).getOrNull();
    }

    /**
     * 通过 beanClass 获取 Bean
     *
     * @param beanClass bean 类
     * @param <T>       bean 类类型
     * @return bean 对象
     */
    public static <T> T getBean(Class<T> beanClass) {
        return Try.of(() -> SpringUtil.getBean(beanClass)).getOrNull();
    }

    /**
     * 获取加入 IOC 容器中被[指定注解]注解了的Bean
     *
     * @param annotationClass 指定注解类
     * @return bean name mapping bean map
     */
    public static Map<String, Object> getBeansMapWithAnnotation(Class<? extends Annotation> annotationClass) {
        return getApplicationContext().getBeansWithAnnotation(annotationClass);
    }

    /**
     * 扫描被 {@link SpringBootApplication} 注解了的启动类包下被指定注解 注解了的类
     *
     * @param annotationClass 指定注解类
     * @return bean collection
     */
    public static Set<Class<?>> scanPackageByAnnotation(Class<? extends Annotation> annotationClass) {
        return Try.of(() -> ClassesUtil.scanPackageByAnnotation(getRootPackagePath(), annotationClass)).getOrElse(CollectionsUtil.newHashSet());
    }

    /**
     * 扫描被 {@link SpringBootApplication} 注解了的启动类包下所有指定类或接口的子类或实现类
     *
     * @param superClass 父类或接口
     * @param <Parent>   父类类型
     * @return 类集合
     */

    public static <Parent> Set<Class<? extends Parent>> scanPackagesBySuper(final Class<Parent> superClass) {
        return Try.of(() -> ClassesUtil.scanPackagesBySuper(new String[]{getRootPackagePath()}, superClass)).getOrElse(CollectionsUtil.newHashSet());
    }

    /**
     * 获取 @SpringBootApplication 启动类所在的包路径
     *
     * @return 启动类所在的包路径
     */
    public static String getRootPackagePath() {
        if (Objects.isBlank(rootPackagePath)) {
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
        if (Objects.isNotNull(getBean(clazz))) {
            registerBean(StringsUtil.lowerFirst(clazz.getSimpleName()), clazz);
        }
    }

    /**
     * 向 IOC 注册 bean，并返回注册后的 bean，使用类名首字母小写作为 bean name
     *
     * @param clazz 要注册的 bean
     * @param <T>   要注册的 bean 类型
     * @return 注册后的 bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T registerCapableBean(Class<T> clazz) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(clazz);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        String lowerFirstClassSimpleName = StringsUtil.lowerFirst(clazz.getSimpleName());
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(clazz);
        beanFactory.registerBeanDefinition(lowerFirstClassSimpleName, beanDefinition);
        return (T) applicationContext.getBean(lowerFirstClassSimpleName);
    }

}
