// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import cn.hutool.core.util.ClassUtil;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Action;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * toolkit for class
 *
 * @author wjm
 * @since 2021-05-10 17:46
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classes {

    /**
     * the annotation class and the annotated class cache
     */
    private static final Map<String, Set<Class<?>>> ANNOTATION_CLASS_MAPPING_ANNOTATED_CLASSES_CACHE = Collections.newConcurrentHashMap();

    /**
     * the class and all its subclasses or implementation classes cache
     */
    private static final Map<String, Set<Class<?>>> CLASS_MAPPING_SUBCLASSES_OR_IMPLEMENTATION_CLASSES_CACHE = Collections.newConcurrentHashMap();

    /**
     * check the target class is assignable from the source class
     *
     * @param sourceClass the source class
     * @param targetClass the target class
     * @return return true if the target class is assignable from the source class
     */
    public static boolean isAssignable(Class<?> sourceClass, Class<?> targetClass) {
        return ClassUtil.isAssignable(targetClass, sourceClass);
    }

    /**
     * reverse {@link #isAssignable(Class, Class)}
     *
     * @param sourceClass the source class
     * @param targetClass the target class
     * @return return true if the target class is not assignable from the source class
     */
    public static boolean isNotAssignable(Class<?> sourceClass, Class<?> targetClass) {
        return !isAssignable(sourceClass, targetClass);
    }

    /**
     * <pre>
     * get the simple class name.
     *
     * example code:
     * {@code
     *     public static void main(String[] args) {
     *         // the output is "Classes"
     *         Classes.getClassSimpleName(Classes.class);
     *     }
     * }
     * </pre>
     *
     * @param input the specified class
     * @return the simple class name
     * @see ClassUtil#getClassName(Object, boolean)
     */
    public static String getClassSimpleName(Class<?> input) {
        return ClassUtil.getClassName(input, true);
    }

    /**
     * <pre>
     * get the full class name.
     *
     * example code:
     * {@code
     *     public static void main(String[] args) {
     *         // the output is "cn.library.java.tool.lang.object.Classes"
     *         Classes.getClassSimpleName(Classes.class);
     *     }
     * }
     * </pre>
     *
     * @param input the specified class
     * @return the full class name
     * @see ClassUtil#getClassName(Object, boolean)
     */
    public static String getClassFullName(Class<?> input) {
        return ClassUtil.getClassName(input, false);
    }

    /**
     * see {@link ClassUtil#getPackage(Class)}
     *
     * @param input the specified class
     * @return the package path of the class
     */
    public static String getPackagePath(Class<?> input) {
        return ClassUtil.getPackage(input);
    }

    /**
     * <pre>
     * get the largest range package paths.
     *
     * example code:
     * {@code
     * // "cn.test"    is contain "cn.test.lang1", "cn.test.lang1",     so keep only "cn.test".
     * // "cn.library" is contain "cn.library.xxx", "cn.library.ss.xx", so keep only "cn.library".
     * // "cn.core"    not contain the other package path, so keep it.
     * // the output is ["cn.test", "cn.library", "cn.core"]
     * Classes.getTheMostLargerRangePackagePath(List.of("cn.test.lang1", "cn.test.lang1", "cn.test", "cn.library", "cn.library.xxx", "cn.library.ss.xx", "cn.core"));
     *
     * // warning: this following is not the expected result:
     * // all package paths start with "cn.library", and although the second package name is inconsistent, it will also be filtered out.
     * // the output is ["cn.library"]
     * Classes.getTheMostLargerRangePackagePath(List.of("cn.library.lang1", "cn.library.lang1", "cn.library", "cn.library2", "cn.library2.xxx", "cn.library2.ss.xx", "cn.library3"));
     * }
     * </pre>
     *
     * @param packagePaths the package paths
     * @return the largest range package paths
     */
    public static Set<String> getTheLargestRangePackagePath(Collection<String> packagePaths) {
        Set<String> theMostLargerRangePackagePaths = Collections.ofHashSet(packagePaths);
        packagePaths.stream()
                .flatMap(previousIndex -> packagePaths.stream().map(nextIndex -> new String[]{previousIndex, nextIndex}))
                // exclude itself
                .filter(pair -> Comparators.notEquals(pair[0], pair[1]))
                .filter(pair -> pair[0].startsWith(pair[1]))
                .forEach(pair -> Collections.remove(theMostLargerRangePackagePaths, pair[0]));
        return theMostLargerRangePackagePaths;
    }

    /**
     * get class of the method define
     *
     * @param input the method
     * @return the class of the method
     */
    public static Class<?> getClass(Method input) {
        return Action.<Class<?>>ifNull(input).then(() -> null).otherwise(input::getDeclaringClass).get();
    }

    /**
     * search field by name in the specified class
     *
     * @param input     the specified class
     * @param fieldName the field name
     * @return the field
     * @see ClassUtil#getDeclaredField(Class, String)
     */
    public static Field getField(Class<?> input, String fieldName) {
        return ClassUtil.getDeclaredField(input, fieldName);
    }

    /**
     * search field by name in the specified class and its parent class excluding {@link Object}
     *
     * @param input     the specified class
     * @param fieldName the field name
     * @return the field
     * @see #getField(Class, String)
     */
    public static Field getFieldDeep(Class<?> input, String fieldName) {
        if (Nil.isNull(input) || Nil.isBlank(fieldName)) {
            return null;
        }
        Field field = getField(input, fieldName);
        if (Nil.isNull(field)) {
            Class<?> superClass = input.getSuperclass();
            if (Nil.isNotNull(superClass)) {
                return getFieldDeep(superClass, fieldName);
            }
        }
        return field;
    }

    /**
     * search field fuzzily by name in the specified class and its parent class excluding {@link Object}
     *
     * @param input     the specified class
     * @param fieldName the field name
     * @return the field
     * @see #getFieldDeep(Class, String)
     * @see Strings#getMostSimilar(String, Collection)
     */
    public static Field getFieldFuzzy(Class<?> input, String fieldName) {
        List<Field> fields = getFieldsDeep(input);
        List<String> fieldNames = Collections.newArrayList(fields.size());
        Map<String, Field> fieldNameMappingFieldMap = Collections.newHashMap();
        fields.forEach(field -> {
            fieldNames.add(field.getName());
            fieldNameMappingFieldMap.put(field.getName(), field);
        });
        return fieldNameMappingFieldMap.get(Strings.getMostSimilar(fieldName, fieldNames));
    }

    /**
     * get all fields in the specified class
     *
     * @param input the specified class
     * @return all fields in the specified class
     */
    public static List<Field> getFields(Class<?> input) {
        return Action.<List<Field>>ifNull(input)
                .then(Collections::newArrayList)
                .otherwise(() -> Arrays.stream(input.getDeclaredFields()).toList())
                .get();
    }

    /**
     * get all field names in the specified class
     *
     * @param input the specified class
     * @return all field names in the specified class
     */
    public static List<String> getFieldNames(Class<?> input) {
        return Action.<List<String>>ifNull(input)
                .then(Collections::newArrayList)
                .otherwise(() -> Arrays.stream(input.getDeclaredFields()).map(Field::getName).toList())
                .get();
    }

    /**
     * get all fields in the specified class and its parent class excluding {@link Object}
     *
     * @param input the specified class
     * @return all fields in the specified class and its parent class excluding {@link Object}
     */
    public static List<Field> getFieldsDeep(Class<?> input) {
        if (Nil.isNull(input)) {
            return Collections.newArrayList();
        }
        Class<?> findClass = input;
        List<Field> fields = Collections.newArrayList();
        while (Nil.isNotNull(findClass)) {
            fields.addAll(getFields(findClass));
            findClass = findClass.getSuperclass();
        }
        return fields;
    }

    /**
     * get all field names in the specified class and its parent class excluding {@link Object}
     *
     * @param input the specified class
     * @return all field names in the specified class and its parent class excluding {@link Object}
     */
    public static List<String> getFieldNamesDeep(Class<?> input) {
        if (Nil.isNull(input)) {
            return Collections.newArrayList();
        }
        Class<?> findClass = input;
        List<String> fieldNames = Collections.newArrayList();
        while (Nil.isNotNull(findClass)) {
            fieldNames.addAll(getFieldNames(findClass));
            findClass = findClass.getSuperclass();
        }
        return fieldNames;
    }

    /**
     * see {@link #scanByAnnotation(Class, Collection)}
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return all classes containing the specified annotation in the specified package paths
     */
    public static Set<Class<?>> scanByAnnotation(Class<? extends Annotation> annotationClass, String... scanPackagePaths) {
        return scanByAnnotation(annotationClass, Collections.ofImmutableSet(scanPackagePaths));
    }

    /**
     * scan all classes containing the specified annotation in the specified package paths
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return all classes containing the specified annotation in the specified package paths
     * @see ClassUtil#scanPackageByAnnotation(String, Class)
     */
    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static Set<Class<?>> scanByAnnotation(Class<? extends Annotation> annotationClass, Collection<String> scanPackagePaths) {
        return ANNOTATION_CLASS_MAPPING_ANNOTATED_CLASSES_CACHE.computeIfAbsent(STR."\{annotationClass.getName()} in [\{Strings.joinWithCommaAndSpace(scanPackagePaths)}]", ignore ->
                Collections.emptyIfNull(scanPackagePaths)
                        .stream()
                        .map(path -> ClassUtil.scanPackageByAnnotation(path, annotationClass))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet())
        );
    }

    /**
     * see {@link #scanBySuper(Class, Collection)}
     *
     * @param rootClass        the specified class
     * @param scanPackagePaths the specified package paths
     * @param <T>              the specified class type
     * @return all subclasses or implementation classes of the specified class or interface in the specified package paths
     */
    public static <T> Set<Class<? extends T>> scanBySuper(Class<T> rootClass, String... scanPackagePaths) {
        return scanBySuper(rootClass, Collections.ofArrayList(scanPackagePaths));
    }

    /**
     * scan all subclasses or implementation classes of the specified class or interface in the specified package paths
     *
     * @param rootClass        the specified class
     * @param scanPackagePaths the specified package paths
     * @param <T>              the specified class type
     * @return all subclasses or implementation classes of the specified class or interface in the specified package paths
     */
    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.PREVIEW})
    public static <T> Set<Class<? extends T>> scanBySuper(Class<T> rootClass, Collection<String> scanPackagePaths) {
        return (Set<Class<? extends T>>) (Set<?>) CLASS_MAPPING_SUBCLASSES_OR_IMPLEMENTATION_CLASSES_CACHE.computeIfAbsent(STR."\{rootClass.getName()} in [\{Strings.joinWithCommaAndSpace(scanPackagePaths)}]", ignore -> {
            Set<Class<?>> subClasses = Collections.newHashSet();
            Collections.emptyIfNull(scanPackagePaths).forEach(packageName -> Collections.add(subClasses, ClassUtil.scanPackageBySuper(packageName, rootClass)));
            return subClasses;
        });
    }

    /**
     * see {@link #scanByPackagePath(Collection)}
     *
     * @param scanPackagePaths the specified package paths
     * @return all classes in the specified package paths
     */
    public static Set<Class<?>> scanByPackagePath(String... scanPackagePaths) {
        return scanByPackagePath(Arrays.stream(scanPackagePaths).collect(Collectors.toSet()));
    }

    /**
     * scan all classes in the specified package paths
     *
     * @param scanPackagePaths the specified package paths
     * @return all classes in the specified package paths
     */
    public static Set<Class<?>> scanByPackagePath(Collection<String> scanPackagePaths) {
        return Nil.isEmpty(scanPackagePaths) ? scanAll() : scanPackagePaths
                .stream()
                .map(ClassUtil::scanPackage)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * scan all classes
     *
     * @return all classes
     */
    public static Set<Class<?>> scanAll() {
        return ClassUtil.scanPackage();
    }

}