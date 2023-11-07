// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.contract;

import cn.srd.library.java.contract.constant.annotation.AnnotationConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * toolkit for annotation
 *
 * @author wjm
 * @since 2023-10-17 12:52
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Annotations extends cn.srd.library.java.tool.lang.annotation.Annotations {

    /**
     * get the annotation default field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType) {
        return getAnnotationValue(annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param <T>            the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotationValue(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     */
    public static <T> Set<T> getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... packagePaths) {
        return getAnnotationValue(annotationType, fieldType, fieldName, Collections.ofHashSet(packagePaths));
    }

    /**
     * <pre>
     * get the annotation field value in the specified package path.
     *
     * for example:
     * {@code
     *
     *     // define an annotation
     *     @Retention(RetentionPolicy.RUNTIME)
     *     @Target(ElementType.TYPE)
     *     @Documented
     *     public @interface CustomerAnnotation {
     *
     *        String fieldNames() default "";
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldNames = "test1")
     *     public class Config {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output is "test1".
     *             Annotations.getAnnotationValue(CustomerAnnotation.class, String.class, "fieldNames", "cn.library.test");
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotation field value in the specified package path
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     */
    public static <T> Set<T> getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> packagePaths) {
        return Classes.scanByTypeFilter(new AnnotationTypeFilter(annotationType), packagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .map(annotatedClass -> getAnnotationValue(annotatedClass, annotationType, fieldType, fieldName))
                .collect(Collectors.toSet());
    }

    /**
     * get the annotation default field value in the default package path, the default field type is an array type.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType) {
        return getAnnotationNestValue(annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName) {
        return getAnnotationNestValue(annotationType, fieldType, fieldName, BasePackagePath.get(Springs.getSpringBootApplicationPackagePath()));
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName, String... packagePaths) {
        return getAnnotationNestValue(annotationType, fieldType, fieldName, Collections.ofHashSet(packagePaths));
    }

    /**
     * <pre>
     * get the annotation field value in the specified package path.
     *
     * for example:
     * {@code
     *
     *     // define an annotation
     *     @Retention(RetentionPolicy.RUNTIME)
     *     @Target(ElementType.TYPE)
     *     @Documented
     *     public @interface CustomerAnnotation {
     *
     *        String[] fieldNames() default {};
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldNames = {"test1", "test2"})
     *     public class Config {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output is ["test1", "test2"]
     *             Annotations.getAnnotationValue(CustomerAnnotation.class, String[].class, "fieldNames", "cn.library.test");
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName, Collection<String> packagePaths) {
        return getAnnotationValue(annotationType, fieldType, fieldName, packagePaths)
                .stream()
                .map(Collections::ofArrayList)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * get the annotated class mapping the annotation default field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotated class mapping the annotation default field value in the default package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotated class mapping the annotation field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param <T>            the field type in annotation
     * @return the annotated class mapping the annotation field value in the default package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationType, fieldType, fieldName, BasePackagePath.get(Springs.getSpringBootApplicationPackagePath()));
    }

    /**
     * get the annotated class mapping the annotation field value in the specified package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotated class mapping the annotation field value in the specified package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... packagePaths) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationType, fieldType, fieldName, Collections.ofHashSet(packagePaths));
    }

    /**
     * <pre>
     * get the annotated class mapping the annotation field value in the specified package path.
     *
     * for example:
     * {@code
     *
     *     // define an annotation
     *     @Retention(RetentionPolicy.RUNTIME)
     *     @Target(ElementType.TYPE)
     *     @Documented
     *     public @interface CustomerAnnotation {
     *
     *        String[] fieldNames() default {};
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldNames = {"test1", "test2"})
     *     public class Config {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output map is: {Class<CustomerAnnotation>:["test1", "test2"]}
     *             Annotations.getAnnotatedClassMappingAnnotationValueMap(CustomerAnnotation.class, String[].class, "fieldNames", "cn.library.test");
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotated class mapping the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> packagePaths) {
        return Classes.scanByTypeFilter(new AnnotationTypeFilter(annotationType), packagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .collect(Collectors.toMap(annotatedClass -> annotatedClass, annotatedClass -> getAnnotationValue(annotatedClass, annotationType, fieldType, fieldName)));
    }

    /**
     * get the annotation default field value mapping the annotated class in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation default field value mapping the annotated class in the default package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value mapping the annotated class in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param <T>            the field type in annotation
     * @return the annotation field value mapping the annotated class in the default package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationType, fieldType, fieldName, BasePackagePath.get(Springs.getSpringBootApplicationPackagePath()));
    }

    /**
     * get the annotation field value mapping the annotated class in the specified package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotation field value mapping the annotated class in the specified package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByTypeFilter(TypeFilter, Collection)
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... packagePaths) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationType, fieldType, fieldName, Collections.ofHashSet(packagePaths));
    }

    /**
     * <pre>
     * get the annotation field value mapping the annotated class in the specified package path.
     *
     * for example:
     * {@code
     *
     *     // define an annotation
     *     @Retention(RetentionPolicy.RUNTIME)
     *     @Target(ElementType.TYPE)
     *     @Documented
     *     public @interface CustomerAnnotation {
     *
     *        String[] fieldNames() default {};
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldNames = {"test1", "test2"})
     *     public class Config {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output map is: {["test1", "test2"]:Class<CustomerAnnotation>}
     *             Annotations.getAnnotationValueMappingAnnotatedClassMap(CustomerAnnotation.class, String[].class, "fieldNames", "cn.library.test");
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param packagePaths   the package path to be scanned
     * @param <T>            the field type in annotation
     * @return the annotation field value mapping the annotated class in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> packagePaths) {
        return Classes.scanByTypeFilter(new AnnotationTypeFilter(annotationType), packagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .collect(Collectors.toMap(annotatedClass -> getAnnotationValue(annotatedClass, annotationType, fieldType, fieldName), annotatedClass -> annotatedClass));
    }

}
