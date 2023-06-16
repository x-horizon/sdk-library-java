package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.ClassUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类工具
 *
 * @author wjm
 * @since 2021/5/10 17:46
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassesUtil extends ClassUtil {

    /**
     * 根据全限定类名获取类
     *
     * @param classFullName 全限定类名
     * @param <T>           类类型
     * @return 类
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(String classFullName) {
        return (Class<T>) Class.forName(classFullName);
    }

    /**
     * 获取简单类名（不带包名）
     *
     * @param clazz 要获取类名的类
     * @return 简单类名（不带包名）
     */
    public static String getClassSimpleName(Class<?> clazz) {
        return getClassName(clazz, true);
    }

    /**
     * 获取全限定类名
     *
     * @param clazz 要获取类名的类
     * @return 全限定类名
     */
    public static String getClassFullName(Class<?> clazz) {
        return getClassName(clazz, false);
    }

    /**
     * 获取函数所在的类
     *
     * @param method 指定的函数
     * @return 指定函数所在的类
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
     * @param sourceType  原类型
     * @param targetTypes 目标类型
     * @return 是否存在同类型
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
     * 扫描指定包路径集合下所有包含指定注解的类
     *
     * @param packageNames    包路径集合
     * @param annotationClass 注解类
     * @return 类集合
     */
    public static Set<Class<?>> scanPackageByAnnotation(String[] packageNames, Class<? extends Annotation> annotationClass) {
        return Stream.of(Objects.setIfEmpty(packageNames, new String[]{}))
                .map(path -> scanPackageByAnnotation(path, annotationClass))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * 扫描指定包路径集合下所有指定类或接口的子类或实现类
     *
     * @param packageNames 包路径集合
     * @param superClass   父类或接口
     * @param <PARENT>     父类类型
     * @return 类集合
     */
    public static <PARENT> Set<Class<? extends PARENT>> scanPackagesBySuper(String[] packageNames, final Class<PARENT> superClass) {
        Set<Class<? extends PARENT>> subClasses = new HashSet<>();
        Arrays.stream(packageNames).forEach(packageName -> CollectionsUtil.addAll(subClasses, scanPackageBySuper(packageName, superClass)));
        return subClasses;
    }

    /**
     * 查找指定类中的字段（包括非public字段）
     *
     * @param clazz     被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field findField(Class<?> clazz, String fieldName) {
        return ClassUtil.getDeclaredField(clazz, fieldName);
    }

    /**
     * 查找指定类及其父类（不包括 {@link Object}）中的字段（包括非public字段）
     *
     * @param clazz     被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field findFieldContainSuper(Class<?> clazz, String fieldName) {
        if (Objects.isNull(clazz) || Objects.isBlank(fieldName)) {
            return null;
        }
        Field field = findField(clazz, fieldName);
        if (Objects.isNull(field)) {
            Class<?> superClass = clazz.getSuperclass();
            if (Objects.isNotNull(superClass)) {
                return findFieldContainSuper(superClass, fieldName);
            }
        }
        return field;
    }

    /**
     * 获取指定类的所有字段（包括非public字段）
     *
     * @param clazz 指定类
     * @return 字段
     */
    public static Field[] getAllFields(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return new Field[0];
        }
        return clazz.getDeclaredFields();
    }

    /**
     * 获取指定类及其父类（不包括 {@link Object}）中的所有字段（包括非public字段）
     *
     * @param clazz 指定类
     * @return 字段
     */
    public static Field[] getAllFieldsContainSuper(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return new Field[0];
        }
        Class<?> findClass = clazz;
        List<Field> fields = new ArrayList<>();
        while (Objects.isNotNull(findClass)) {
            fields.addAll(Arrays.stream(findClass.getDeclaredFields()).toList());
            findClass = findClass.getSuperclass();
        }
        return fields.toArray(Field[]::new);
    }

}