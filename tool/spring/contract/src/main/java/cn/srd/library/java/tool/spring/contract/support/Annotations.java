// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.contract.support;

import cn.srd.library.java.contract.constant.annotation.AnnotationConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
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
     * the one-to-one relationship for the annotation class and the annotation instance cache
     */
    private static final Map<String, Annotation> ANNOTATION_CLASS_MAPPING_ANNOTATED_INSTANCE_CACHE = Collections.newConcurrentHashMap();

    /**
     * get the annotation in the default package path.
     *
     * @param annotationClass the annotation class
     * @param <T>             the field type in annotation
     * @return the annotation in the default package path
     * @see #getAnnotation(Class, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return getAnnotation(annotationClass, Classes.getBasePackagePath());
    }

    /**
     * get the annotation in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation in the specified package path
     * @see #getAnnotation(Class, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationClass, String... scanPackagePaths) {
        return getAnnotation(annotationClass, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation in the specified package path
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.PREVIEW})
    public static <T extends Annotation> T getAnnotation(Class<T> annotationClass, Collection<String> scanPackagePaths) {
        return (T) ANNOTATION_CLASS_MAPPING_ANNOTATED_INSTANCE_CACHE.computeIfAbsent(STR."\{annotationClass.getName()} in [\{Strings.joinWithCommaAndSpace(scanPackagePaths)}]", ignore -> {
            Set<BeanDefinition> beanDefinitions = Classes.scanByAnnotationTypeFilter(annotationClass, scanPackagePaths);
            if (Collections.hasMoreThanOneElement(beanDefinitions)) {
                throw new LibraryJavaInternalException(Strings.format("{}found more than one annotations {} in package paths {}, please check!", ModuleView.TOOL_ANNOTATION_SYSTEM, beanDefinitions.stream().map(BeanDefinition::getBeanClassName).toList(), scanPackagePaths));
            }
            return beanDefinitions.stream()
                    .findFirst()
                    .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                    .map(annotatedClass -> getAnnotation(annotatedClass, annotationClass))
                    .orElse(null);
        });
    }

    /**
     * get the annotation default field value in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationClass, Class<T> fieldClass) {
        return getAnnotationValue(annotationClass, fieldClass, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param fieldName       the field name in annotation
     * @param <T>             the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName) {
        return getAnnotationValue(annotationClass, fieldClass, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, String... scanPackagePaths) {
        return getAnnotationValue(annotationClass, fieldClass, fieldName, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     */
    public static <T> T getAnnotationValue(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, Collection<String> scanPackagePaths) {
        Set<BeanDefinition> beanDefinitions = Classes.scanByAnnotationTypeFilter(annotationClass, scanPackagePaths);
        if (Collections.hasMoreThanOneElement(beanDefinitions)) {
            throw new LibraryJavaInternalException(Strings.format("{}found more than one annotations {} in package paths {}, please check!", ModuleView.TOOL_ANNOTATION_SYSTEM, beanDefinitions.stream().map(BeanDefinition::getBeanClassName).toList(), scanPackagePaths));
        }
        return beanDefinitions.stream()
                .findFirst()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .map(annotatedClass -> getAnnotationValue(annotatedClass, annotationClass, fieldClass, fieldName))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}the annotation value must not be null, please check the detail info: the package paths:{}, [annotation class:{}], [field class:{}], [field name:{}]", ModuleView.TOOL_ANNOTATION_SYSTEM, scanPackagePaths, annotationClass.getName(), fieldClass.getName(), fieldName)));
    }

    /**
     * get the annotation default field values in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationClass, Class<T> fieldClass) {
        return getAnnotationValues(annotationClass, fieldClass, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field values in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param fieldName       the field name in annotation
     * @param <T>             the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName) {
        return getAnnotationValues(annotationClass, fieldClass, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field values in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, String... scanPackagePaths) {
        return getAnnotationValues(annotationClass, fieldClass, fieldName, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     */
    public static <T> Set<T> getAnnotationValues(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotationTypeFilter(annotationClass, scanPackagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .map(annotatedClass -> getAnnotationValue(annotatedClass, annotationClass, fieldClass, fieldName))
                .collect(Collectors.toSet());
    }

    /**
     * get the annotation default field value in the default package path, the default field type is an array type.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass) {
        return getAnnotationNestValue(annotationClass, fieldClass, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass, String fieldName) {
        return getAnnotationNestValue(annotationClass, fieldClass, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationNestValue(Class, Class, String, Collection)
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass, String fieldName, String... scanPackagePaths) {
        return getAnnotationNestValue(annotationClass, fieldClass, fieldName, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValue(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValue(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass, String fieldName, Collection<String> scanPackagePaths) {
        return Converts.toSet(getAnnotationValue(annotationClass, fieldClass, fieldName, scanPackagePaths));
    }

    /**
     * get the annotation default field value in the default package path, the default field type is an array type.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation default field value in the default package path
     * @see #getAnnotationNestValues(Class, Class, String, Collection)
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass) {
        return getAnnotationNestValues(annotationClass, fieldClass, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation field value in the default package path
     * @see #getAnnotationNestValues(Class, Class, String, Collection)
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass, String fieldName) {
        return getAnnotationNestValues(annotationClass, fieldClass, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationNestValues(Class, Class, String, Collection)
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass, String fieldName, String... scanPackagePaths) {
        return getAnnotationNestValues(annotationClass, fieldClass, fieldName, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value in the specified package path
     * @see #getAnnotationValues(Class, Class, String, Collection)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Set<T> getAnnotationNestValues(Class<? extends Annotation> annotationClass, Class<T[]> fieldClass, String fieldName, Collection<String> scanPackagePaths) {
        return getAnnotationValues(annotationClass, fieldClass, fieldName, scanPackagePaths)
                .stream()
                .map(Collections::ofArrayList)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * get the annotated class mapping the annotation default field value in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotated class mapping the annotation default field value in the default package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationClass, fieldClass, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotated class mapping the annotation field value in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param fieldName       the field name in annotation
     * @param <T>             the field type in annotation
     * @return the annotated class mapping the annotation field value in the default package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationClass, fieldClass, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotated class mapping the annotation field value in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotated class mapping the annotation field value in the specified package path
     * @see #getAnnotatedClassMappingAnnotationValueMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, String... scanPackagePaths) {
        return getAnnotatedClassMappingAnnotationValueMap(annotationClass, fieldClass, fieldName, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotated class mapping the annotation field value in the specified package path
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<Class<?>, T> getAnnotatedClassMappingAnnotationValueMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotationTypeFilter(annotationClass, scanPackagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .collect(Collectors.toMap(annotatedClass -> annotatedClass, annotatedClass -> getAnnotationValue(annotatedClass, annotationClass, fieldClass, fieldName)));
    }

    /**
     * get the annotation default field value mapping the annotated class in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param <T>             the field type in annotation
     * @return the annotation default field value mapping the annotated class in the default package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationClass, fieldClass, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * get the annotation field value mapping the annotated class in the default package path.
     *
     * @param annotationClass the annotation class
     * @param fieldClass      the field class in annotation
     * @param fieldName       the field name in annotation
     * @param <T>             the field type in annotation
     * @return the annotation field value mapping the annotated class in the default package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     * @see Classes#getBasePackagePath()
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationClass, fieldClass, fieldName, Classes.getBasePackagePath());
    }

    /**
     * get the annotation field value mapping the annotated class in the specified package path.
     *
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value mapping the annotated class in the specified package path
     * @see #getAnnotationValueMappingAnnotatedClassMap(Class, Class, String, Collection)
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, String... scanPackagePaths) {
        return getAnnotationValueMappingAnnotatedClassMap(annotationClass, fieldClass, fieldName, Collections.ofHashSet(scanPackagePaths));
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
     * @param annotationClass  the annotation class
     * @param fieldClass       the field class in annotation
     * @param fieldName        the field name in annotation
     * @param scanPackagePaths the package path to be scanned
     * @param <T>              the field type in annotation
     * @return the annotation field value mapping the annotated class in the specified package path
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     * @see Classes#scanByAnnotationTypeFilter(Class, Collection)
     */
    public static <T> Map<T, Class<?>> getAnnotationValueMappingAnnotatedClassMap(Class<? extends Annotation> annotationClass, Class<T> fieldClass, String fieldName, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotationTypeFilter(annotationClass, scanPackagePaths)
                .stream()
                .map(beanDefinition -> Classes.ofName(beanDefinition.getBeanClassName()))
                .collect(Collectors.toMap(annotatedClass -> getAnnotationValue(annotatedClass, annotationClass, fieldClass, fieldName), annotatedClass -> annotatedClass));
    }

}