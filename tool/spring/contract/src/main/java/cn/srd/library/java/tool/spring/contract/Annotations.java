// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.contract;

import cn.srd.library.java.contract.constant.annotation.AnnotationConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
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
     * get the annotation in the default package path.
     *
     * @param annotationType the annotation class
     * @param <T>            the field type in annotation
     * @return the annotation in the default package path
     * @see #getAnnotation(Class, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return getAnnotation(annotationType, Classes.getBasePackagePath());
    }

    /**
     * get the annotation in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation in the specified package path
     * @see #getAnnotation(Class, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationType, String... scanPackagePaths) {
        return getAnnotation(annotationType, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotation in the specified package path.
     *
     * for example:
     *
     * {@code
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
     *     @CustomerAnnotation(fieldName = "test1")
     *     public class Config {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             CustomerAnnotation customerAnnotation = Annotations.getAnnotation(CustomerAnnotation.class, "cn.library.test");
     *             // the output is "test1"
     *             String fieldName = customerAnnotation.fieldName;
     *         }
     *
     *     }
     * }
     *
     * note: throw if there are more than one specified annotation in the specified package paths
     * </pre>
     *
     * @param annotationType   the annotation class
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation in the specified package path
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationType, Collection<String> scanPackagePaths) {
        Set<BeanDefinition> beanDefinitions = Classes.scanByAnnotationTypeFilter(annotationType, scanPackagePaths);
        if (Collections.hasMoreThanOneElement(beanDefinitions)) {
            throw new LibraryJavaInternalException(Strings.format("{}found more than one annotations {} in package paths {}, please check!", ModuleView.TOOL_ANNOTATION_SYSTEM, beanDefinitions.stream().map(BeanDefinition::getBeanClassName).toList(), scanPackagePaths));
        }
        return beanDefinitions.stream()
                .findFirst()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .map(annotatedClass -> getAnnotation(annotatedClass, annotationType))
                .orElse(null);
    }

    /**
     * get the annotation default field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType) {
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
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotationValue(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... scanPackagePaths) {
        return getAnnotationValue(annotationType, fieldType, fieldName, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotation field value in the specified package path.
     *
     * for example:
     *
     * {@code
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
     *     @CustomerAnnotation(fieldName = "test1")
     *     public class Config {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output is "test1".
     *             Annotations.getAnnotationValue(CustomerAnnotation.class, String.class, "fieldName", "cn.library.test");
     *         }
     *
     *     }
     * }
     *
     * note: throw if there are more than one specified annotation field value in the specified package paths
     * </pre>
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> scanPackagePaths) {
        Set<BeanDefinition> beanDefinitions = Classes.scanByAnnotationTypeFilter(annotationType, scanPackagePaths);
        if (Collections.hasMoreThanOneElement(beanDefinitions)) {
            throw new LibraryJavaInternalException(Strings.format("{}found more than one annotations {} in package paths {}, please check!", ModuleView.TOOL_ANNOTATION_SYSTEM, beanDefinitions.stream().map(BeanDefinition::getBeanClassName).toList(), scanPackagePaths));
        }
        return beanDefinitions.stream()
                .findFirst()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .map(annotatedClass -> getAnnotationValue(annotatedClass, annotationType, fieldType, fieldName))
                .orElse(null);
    }

    /**
     * get the annotation default field values in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationType, Class<T> fieldType) {
        return getAnnotationValues(annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field values in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param fieldName      the field name in annotation
     * @param <T>            the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotationValues(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field values in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... scanPackagePaths) {
        return getAnnotationValues(annotationType, fieldType, fieldName, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotation field values in the specified package path.
     *
     * for example:
     *
     * {@code
     *     // define an annotation
     *     @Retention(RetentionPolicy.RUNTIME)
     *     @Target(ElementType.TYPE)
     *     @Documented
     *     public @interface CustomerAnnotation {
     *
     *        String fieldName() default "";
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldName = "test1")
     *     public class Config1 {
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldName = "test2")
     *     public class Config2 {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output is ["test1", "test2"].
     *             Annotations.getAnnotationValue(CustomerAnnotation.class, String.class, "fieldName", "cn.library.test");
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotationTypeFilter(annotationType, scanPackagePaths)
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
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
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
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName) {
        return getAnnotationNestValue(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName, String... scanPackagePaths) {
        return getAnnotationNestValue(annotationType, fieldType, fieldName, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotation field value in the specified package path.
     *
     * for example:
     *
     * {@code
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
     *     public class Config1 {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output is ["test1", "test2", "test3", "test4"]
     *             Annotations.getAnnotationValue(CustomerAnnotation.class, String[].class, "fieldNames", "cn.library.test");
     *         }
     *
     *     }
     * }
     *
     * note: throw if there are more than one specified annotation field value in the specified package paths
     * </pre>
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName, Collection<String> scanPackagePaths) {
        return Converts.toSet(getAnnotationValue(annotationType, fieldType, fieldName, scanPackagePaths));
    }

    /**
     * get the annotation default field value in the default package path, the default field type is an array type.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationNestValues(Class, Class, String, Collection)
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationType, Class<T[]> fieldType) {
        return getAnnotationNestValues(annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value in the default package path.
     *
     * @param annotationType the annotation class
     * @param fieldType      the field class in annotation
     * @param <T>            the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationNestValues(Class, Class, String, Collection)
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName) {
        return getAnnotationNestValues(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationNestValues(Class, Class, String, Collection)
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName, String... scanPackagePaths) {
        return getAnnotationNestValues(annotationType, fieldType, fieldName, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotation field value in the specified package path.
     *
     * for example:
     *
     * {@code
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
     *     public class Config1 {
     *
     *     }
     *
     *     // mark this annotation and specified the annotation field value
     *     @CustomerAnnotation(fieldNames = {"test3", "test4"})
     *     public class Config2 {
     *
     *     }
     *
     *     // the unit test
     *     public class Test {
     *
     *         public static void main(String[] args) {
     *             // the output is ["test1", "test2", "test3", "test4"]
     *             Annotations.getAnnotationValue(CustomerAnnotation.class, String[].class, "fieldNames", "cn.library.test");
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationType, Class<T[]> fieldType, String fieldName, Collection<String> scanPackagePaths) {
        return getAnnotationValues(annotationType, fieldType, fieldName, scanPackagePaths)
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
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
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
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotated class mapping the annotation field value in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotated class mapping the annotation field value in the specified package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... scanPackagePaths) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationType, fieldType, fieldName, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotated class mapping the annotation field value in the specified package path.
     *
     * for example:
     *
     * {@code
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
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotated class mapping the annotation field value in the specified package path
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotationTypeFilter(annotationType, scanPackagePaths)
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
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
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
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationType, fieldType, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value mapping the annotated class in the specified package path.
     *
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value mapping the annotated class in the specified package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, String... scanPackagePaths) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationType, fieldType, fieldName, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get the annotation field value mapping the annotated class in the specified package path.
     *
     * for example:
     *
     * {@code
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
     * @param annotationType   the annotation class
     * @param fieldType        the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value mapping the annotated class in the specified package path
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotationTypeFilter(annotationType, scanPackagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .collect(Collectors.toMap(annotatedClass -> getAnnotationValue(annotatedClass, annotationType, fieldType, fieldName), annotatedClass -> annotatedClass));
    }

}