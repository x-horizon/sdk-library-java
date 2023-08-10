package cn.srd.sugar.tool.lang.core;

import cn.hutool.core.util.ClassUtil;
import cn.srd.sugar.tool.lang.core.object.Objects;
import cn.srd.sugar.tool.lang.core.validation.Nullable;
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
        return ClassUtil.getClassName(clazz, true);
    }

    /**
     * 获取全限定类名
     *
     * @param clazz 要获取类名的类
     * @return 全限定类名
     */
    public static String getClassFullName(Class<?> clazz) {
        return ClassUtil.getClassName(clazz, false);
    }

    /**
     * 获取函数所在的类
     *
     * @param method 指定的函数
     * @return 指定函数所在的类
     */
    @Nullable
    public static Class<?> getDeclaringClass(@Nullable Method method) {
        if (cn.srd.sugar.tool.lang.core.object.Objects.isNull(method)) {
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
        if (cn.srd.sugar.tool.lang.core.object.Objects.isNull((Object) targetTypes)) {
            return false;
        }
        for (Class<?> targetType : targetTypes) {
            if (ClassUtil.isAssignable(targetType, sourceType)) {
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
        return Stream.of(cn.srd.sugar.tool.lang.core.object.Objects.setIfEmpty(packageNames, new String[]{}))
                .map(path -> ClassUtil.scanPackageByAnnotation(path, annotationClass))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * 扫描指定包路径集合下所有指定类或接口的子类或实现类
     *
     * @param packageNames 包路径集合
     * @param superClass   父类或接口
     * @param <S>          父类类型
     * @return 类集合
     */
    public static <S> Set<Class<? extends S>> scanPackagesBySuper(String[] packageNames, final Class<S> superClass) {
        Set<Class<? extends S>> subClasses = new HashSet<>();
        Arrays.stream(packageNames).forEach(packageName -> CollectionsUtil.addAll(subClasses, ClassUtil.scanPackageBySuper(packageName, superClass)));
        return subClasses;
    }

    /**
     * 获取指定类的所有字段
     *
     * @param clazz 指定类
     * @return 字段
     */
    public static List<Field> getFields(Class<?> clazz) {
        if (cn.srd.sugar.tool.lang.core.object.Objects.isNull(clazz)) {
            return new ArrayList<>();
        }
        return Arrays.stream(clazz.getDeclaredFields()).toList();
    }

    /**
     * 获取指定类及其父类（不包括 {@link Object}）中的所有字段
     *
     * @param clazz 指定类
     * @return 字段
     */
    public static List<Field> getFieldsDeep(Class<?> clazz) {
        if (cn.srd.sugar.tool.lang.core.object.Objects.isNull(clazz)) {
            return new ArrayList<>();
        }
        Class<?> findClass = clazz;
        List<Field> fields = new ArrayList<>();
        while (cn.srd.sugar.tool.lang.core.object.Objects.isNotNull(findClass)) {
            fields.addAll(getFields(findClass));
            findClass = findClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 查找指定类中的字段
     *
     * @param clazz     被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        return ClassUtil.getDeclaredField(clazz, fieldName);
    }

    /**
     * 查找指定类及其父类（不包括 {@link Object}）中的字段
     *
     * @param clazz     被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getFieldDeep(Class<?> clazz, String fieldName) {
        if (cn.srd.sugar.tool.lang.core.object.Objects.isNull(clazz) || cn.srd.sugar.tool.lang.core.object.Objects.isBlank(fieldName)) {
            return null;
        }
        Field field = getField(clazz, fieldName);
        if (cn.srd.sugar.tool.lang.core.object.Objects.isNull(field)) {
            Class<?> superClass = clazz.getSuperclass();
            if (Objects.isNotNull(superClass)) {
                return getFieldDeep(superClass, fieldName);
            }
        }
        return field;
    }

    /**
     * <pre>
     * 模糊查找指定类及其父类（不包括 {@link Object}）中的字段
     * 模糊查找定义：根据字段名与类中的所有字段名进行比较，返回相似度最高的字段
     * 相似度：see {@link StringsUtil#getMostSimilar(String, List)}
     * </pre>
     *
     * @param clazz     被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getFieldFuzzy(Class<?> clazz, String fieldName) {
        List<Field> fields = getFieldsDeep(clazz);
        List<String> fieldNames = new ArrayList<>();
        Map<String, Field> fieldNameMappingFieldMap = new HashMap<>();
        fields.forEach(field -> {
            fieldNames.add(field.getName());
            fieldNameMappingFieldMap.put(field.getName(), field);
        });
        return fieldNameMappingFieldMap.get(StringsUtil.getMostSimilar(fieldName, fieldNames));
    }

}