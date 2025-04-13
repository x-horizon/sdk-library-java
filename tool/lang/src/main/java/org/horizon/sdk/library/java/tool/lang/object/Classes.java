package org.horizon.sdk.library.java.tool.lang.object;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.reflect.ClassUtil;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.functional.Action;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
     * check the target classes is any assignable from the source class
     *
     * @param sourceClass   the source class
     * @param targetClasses the target classes
     * @return return true if the target classes is any assignable from the source class
     */
    public static boolean isAnyAssignable(Class<?> sourceClass, Class<?>... targetClasses) {
        return Arrays.stream(targetClasses).anyMatch(targetClass -> isAssignable(sourceClass, targetClass));
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
     * <p>Gets the simple class name of the specified class.</p>
     *
     * <p>Example:</p>
     * <pre>{@code
     * public static void main(String[] args) {
     *     // Outputs "Classes"
     *     String simpleName = Classes.getClassSimpleName(Classes.class);
     * }
     * }</pre>
     *
     * @param input the class to retrieve the simple name for
     * @return the class name without package prefix
     * @see ClassUtil#getClassName(Object, boolean)
     */
    public static String getClassSimpleName(Class<?> input) {
        return ClassUtil.getClassName(input, true);
    }

    /**
     * <p>Gets the fully qualified class name of the specified class.</p>
     *
     * <p>Example:</p>
     * <pre>{@code
     * public static void main(String[] args) {
     *     // Outputs "org.horizon.sdk.library.java.tool.lang.object.Classes"
     *     String fullName = Classes.getClassFullName(Classes.class);
     * }
     * }</pre>
     *
     * @param input the class to retrieve the fully qualified name for
     * @return the complete class name including package
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
     * <p>Finds the most inclusive package paths from a collection by eliminating nested paths.</p>
     *
     * <p>Behavior characteristics:</p>
     * <ul>
     * <li>Retains only root packages that contain other sub-packages</li>
     * <li>Preserves unique packages without sub-packages</li>
     * </ul>
     *
     * <p>Usage examples:</p>
     * <ol>
     * <li><p>Basic deduplication:</p>
     * <pre>{@code
     * // Input:  ["cn.test.lang1", "cn.test.lang1", "cn.test",
     * //         "org.horizon.sdk.library", "org.horizon.sdk.library.xxx",
     * //         "org.horizon.sdk.library.ss.xx", "cn.core"]
     * // Output: ["cn.test", "org.horizon.sdk.library", "cn.core"]
     * List<String> result = Classes.getTheMostLargerRangePackagePath(inputs);
     * }</pre></li>
     *
     * <li><p>Handling similar root packages:</p>
     * <pre>{@code
     * // Input:  ["org.horizon.sdk.library.lang1", "org.horizon.sdk.library",
     * //         "org.horizon.sdk.library2", "org.horizon.sdk.library2.xxx",
     * //         "org.horizon.sdk.library3"]
     * // Output: ["org.horizon.sdk.library"]
     * // Note: library2/library3 are treated as distinct roots
     * }</pre></li>
     * </ol>
     *
     * @param packagePaths collection of package names (dot-separated format)
     * @return the set of unique root packages in original order
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
     * search field by name in the specified class
     *
     * @param input     the specified class
     * @param fieldName the field name
     * @return the field
     * @see FieldUtil#getDeclaredField(Class, String)
     */
    public static Field getField(Class<?> input, String fieldName) {
        return FieldUtil.getDeclaredField(input, fieldName);
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