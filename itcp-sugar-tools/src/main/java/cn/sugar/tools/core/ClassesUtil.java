package cn.sugar.tools.core;

import cn.hutool.core.util.ClassUtil;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类工具
 *
 * @author wjm
 * @date 2021/5/10 17:46
 */
public class ClassesUtil extends ClassUtil {

    private ClassesUtil() {
    }

    /**
     * 获取方法所在的类
     *
     * @param method
     * @return
     */
    @Nullable
    public static Class<?> getDeclaringClass(@Nullable Method method) {
        if (Objects.isNull(method)) {
            return null;
        }
        return method.getDeclaringClass();
    }

    /**
     * 如果 sourceType 有一个{@link #isAssignable(Class, Class)}，则返回true
     *
     * @param sourceType
     * @param targetTypes
     * @return
     */
    public static boolean isAssignable(@Nullable Class<?> sourceType, @Nullable Class<?>... targetTypes) {
        if (Objects.isNull((Object) targetTypes)) {
            return false;
        }
        for (Class<?> targetType : targetTypes) {
            if (isAssignable(targetType, sourceType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 扫描指定包路径集下所有包含指定注解的类
     *
     * @param packageNames    包路径集
     * @param annotationClass 注解类
     * @return
     */
    public static Set<Class<?>> scanPackageByAnnotation(String[] packageNames, Class<? extends Annotation> annotationClass) {
        return Stream.of(Objects.setIfEmpty(packageNames, new String[]{}))
                .map(path -> scanPackageByAnnotation(path, annotationClass))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

}