// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.annotation;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.srd.library.java.contract.constant.annotation.AnnotationConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2021-03-21 19:22
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Annotations {

    /**
     * the annotation class and the annotated methods cache
     */
    private static final Map<String, Set<Method>> ANNOTATION_CLASS_MAPPING_ANNOTATED_CLASSES_CACHE = Collections.newConcurrentHashMap();

    /**
     * see {@link AnnotationUtil#hasAnnotation(AnnotatedElement, Class)}
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation type
     * @return has the specified annotation
     */
    public static boolean hasAnnotation(AnnotatedElement annotatedElement, Class<? extends Annotation> annotationType) {
        return AnnotationUtil.hasAnnotation(annotatedElement, annotationType);
    }

    /**
     * <pre>
     * get class containing the specified annotation in the default package paths,
     * throw if the class count more than one.
     * </pre>
     *
     * @param annotationClass the specified annotation class
     * @return the one-to-one relationship annotated class in the specified package paths
     * @see BasePackagePath#get()
     * @see Classes#scanByAnnotation(Class, Collection)
     * @see #getAnnotatedClasses(Class, Collection)
     */
    public static Class<?> getAnnotatedClass(Class<? extends Annotation> annotationClass) {
        return getAnnotatedClass(annotationClass, BasePackagePath.get());
    }

    /**
     * <pre>
     * get class containing the specified annotation in the specified package paths,
     * throw if the class count more than one.
     * </pre>
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return the one-to-one relationship annotated class in the specified package paths
     * @see Classes#scanByAnnotation(Class, Collection)
     * @see #getAnnotatedClasses(Class, Collection)
     */
    public static Class<?> getAnnotatedClass(Class<? extends Annotation> annotationClass, String... scanPackagePaths) {
        return getAnnotatedClass(annotationClass, Collections.ofImmutableSet(scanPackagePaths));
    }

    /**
     * <pre>
     * get class containing the specified annotation in the specified package paths,
     * throw if the class count more than one.
     * </pre>
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return the one-to-one relationship annotated class in the specified package paths
     * @see Classes#scanByAnnotation(Class, Collection)
     * @see #getAnnotatedClasses(Class, Collection)
     */
    public static Class<?> getAnnotatedClass(Class<? extends Annotation> annotationClass, Collection<String> scanPackagePaths) {
        Set<Class<?>> annotatedClasses = getAnnotatedClasses(annotationClass, scanPackagePaths);
        if (Collections.hasMoreThanOneElement(annotatedClasses)) {
            throw new LibraryJavaInternalException(Strings.format("{}found more than one annotated class {} in package paths {}, please check!", ModuleView.TOOL_ANNOTATION_SYSTEM, annotatedClasses.stream().map(Class::getName).toList(), scanPackagePaths));
        }
        return annotatedClasses.stream().findFirst().orElse(null);
    }

    /**
     * get all classes containing the specified annotation in the default package path
     *
     * @param annotationClass the specified annotation class
     * @return all classes containing the specified annotation in the default package path
     * @see BasePackagePath#get()
     * @see Classes#scanByAnnotation(Class, Collection)
     */
    public static Set<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotationClass) {
        return getAnnotatedClasses(annotationClass, BasePackagePath.get());
    }

    /**
     * get all classes containing the specified annotation in the specified package paths
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return all classes containing the specified annotation in the specified package paths
     * @see Classes#scanByAnnotation(Class, Collection)
     */
    public static Set<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotationClass, String... scanPackagePaths) {
        return getAnnotatedClasses(annotationClass, Collections.ofImmutableSet(scanPackagePaths));
    }

    /**
     * get all classes containing the specified annotation in the specified package paths
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return all classes containing the specified annotation in the specified package paths
     * @see Classes#scanByAnnotation(Class, Collection)
     */
    public static Set<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotationClass, Collection<String> scanPackagePaths) {
        return Classes.scanByAnnotation(annotationClass, scanPackagePaths);
    }

    /**
     * get all methods containing the specified annotation in the default package path
     *
     * @param annotationClass the specified annotation class
     * @return all methods containing the specified annotation in the default package path
     * @see BasePackagePath#get()
     * @see Annotations#getAnnotatedMethods(Class, Collection)
     */
    public static Set<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass) {
        return getAnnotatedMethods(annotationClass, BasePackagePath.get());
    }

    /**
     * get all methods containing the specified annotation in the specified package paths
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return all methods containing the specified annotation in the specified package paths
     * @see Annotations#getAnnotatedMethods(Class, Collection)
     */
    public static Set<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass, String... scanPackagePaths) {
        return getAnnotatedMethods(annotationClass, Collections.ofImmutableSet(scanPackagePaths));
    }

    /**
     * get all methods containing the specified annotation in the specified package paths
     *
     * @param annotationClass  the specified annotation class
     * @param scanPackagePaths the specified packages path
     * @return all methods containing the specified annotation in the specified package paths
     */
    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static Set<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass, Collection<String> scanPackagePaths) {
        return ANNOTATION_CLASS_MAPPING_ANNOTATED_CLASSES_CACHE.computeIfAbsent(STR."\{annotationClass.getName()} in [\{Strings.joinWithCommaAndSpace(scanPackagePaths)}]", ignore ->
                scanPackagePaths.stream()
                        .map(scanPackagePath -> new Reflections(new ConfigurationBuilder()
                                .setUrls(ClasspathHelper.forPackage(scanPackagePath))
                                .setScanners(Scanners.MethodsAnnotated, Scanners.ConstructorsAnnotated))
                                .getMethodsAnnotatedWith(annotationClass)
                        )
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet())
        );
    }

    /**
     * get annotation in the default package path
     *
     * @param annotationType the annotation type
     * @param <T>            the annotation type
     * @return the annotation
     * @see BasePackagePath#get()
     * @see #getAnnotatedClasses(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return getAnnotation(annotationType, BasePackagePath.get());
    }

    /**
     * get annotation in the specified package paths
     *
     * @param annotationType the annotation type
     * @param <T>            the annotation type
     * @return the annotation
     * @see #getAnnotatedClasses(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationType, String... scanPackagePaths) {
        return getAnnotation(annotationType, Collections.ofImmutableSet(scanPackagePaths));
    }

    /**
     * get annotation in the specified package paths
     *
     * @param annotationType the annotation type
     * @param <T>            the annotation type
     * @return the annotation
     * @see #getAnnotatedClasses(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationType, Collection<String> scanPackagePaths) {
        return getAnnotation(getAnnotatedClass(annotationType, scanPackagePaths), annotationType);
    }

    /**
     * get annotation
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation class
     * @param <T>              the annotation type
     * @return the annotation
     */
    public static <T extends Annotation> T getAnnotation(AnnotatedElement annotatedElement, Class<T> annotationType) {
        return AnnotationUtil.getAnnotation(annotatedElement, annotationType);
    }

    /**
     * get annotations in the default package path
     *
     * @param annotationType the annotation type
     * @param <T>            the annotation type
     * @return the annotation
     * @see BasePackagePath#get()
     * @see #getAnnotatedClasses(Class, Collection)
     * @see #getAnnotations(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType) {
        return getAnnotations(annotationType, BasePackagePath.get());
    }

    /**
     * get annotations in the specified package paths
     *
     * @param annotationType the annotation type
     * @param <T>            the annotation type
     * @return the annotation
     * @see #getAnnotatedClasses(Class, Collection)
     * @see #getAnnotations(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType, String... scanPackagePaths) {
        return getAnnotations(annotationType, Collections.ofImmutableSet(scanPackagePaths));
    }

    /**
     * get annotations in the specified package paths
     *
     * @param annotationType the annotation type
     * @param <T>            the annotation type
     * @return the annotation
     * @see #getAnnotatedClasses(Class, Collection)
     * @see #getAnnotation(AnnotatedElement, Class)
     */
    public static <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType, Collection<String> scanPackagePaths) {
        return getAnnotatedClasses(annotationType, scanPackagePaths).stream()
                .map(annotatedClasses -> getAnnotation(annotatedClasses, annotationType))
                .collect(Collectors.toSet());
    }

    /**
     * see {@link #getAnnotationValue(AnnotatedElement, Class, Class, String)}
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation type
     * @param <T>              the field type
     * @return the annotation field value
     */
    public static <T> T getAnnotationValue(AnnotatedElement annotatedElement, Class<? extends Annotation> annotationType, Class<T> fieldType) {
        return getAnnotationValue(annotatedElement, annotationType, fieldType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * <pre>
     * get annotation field value.
     *
     * example code:
     *     {@code
     *        public @interface TestAnnotation {
     *            String fieldName() default "";
     *        }
     *
     *        @TestAnnotation(fieldName = "myField")
     *        public class Test {
     *            public static void main(String[] args) {
     *                // the output is "myField"
     *                Annotations.getAnnotationValue(Test.class, TestAnnotation.class, String.class, "fieldName");
     *            }
     *        }
     *     }
     * </pre>
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation type
     * @param fieldType        the field class
     * @param fieldName        the annotation field name
     * @param <T>              the field type
     * @return the annotation field value
     * @see AnnotationUtil#getAnnotationValue(AnnotatedElement, Class, String)
     */
    public static <T> T getAnnotationValue(AnnotatedElement annotatedElement, Class<? extends Annotation> annotationType, Class<T> fieldType, String fieldName) {
        return fieldType.cast(AnnotationUtil.getAnnotationValue(annotatedElement, annotationType, fieldName));
    }

    /**
     * see {@link #getAnnotationValue(AnnotatedElement, Class, String)}
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation type
     * @param <T>              the field type
     * @return the annotation field value
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     */
    public static <T> T getAnnotationValue(AnnotatedElement annotatedElement, Class<? extends Annotation> annotationType) {
        return getAnnotationValue(annotatedElement, annotationType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * ignore the annotation field type to get annotation value
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation type
     * @param fieldName        the annotation field name
     * @param <T>              the field type
     * @return the annotation field value
     * @see #getAnnotationValue(AnnotatedElement, Class, Class, String)
     */
    public static <T> T getAnnotationValue(AnnotatedElement annotatedElement, Class<? extends Annotation> annotationType, String fieldName) {
        return AnnotationUtil.getAnnotationValue(annotatedElement, annotationType, fieldName);
    }

    /**
     * set annotation field value to default field name
     *
     * @param annotation the annotation
     * @param fieldValue the field value
     * @see AnnotationConstant#DEFAULT_FIELD_NAME
     */
    public static void setAnnotationValue(Annotation annotation, Object fieldValue) {
        setAnnotationValue(annotation, AnnotationConstant.DEFAULT_FIELD_NAME, fieldValue);
    }

    /**
     * set annotation field value to specified field name
     *
     * @param annotation          the annotation
     * @param annotationFieldName the annotation field name
     * @param fieldValue          the field value
     */
    public static void setAnnotationValue(Annotation annotation, String annotationFieldName, Object fieldValue) {
        AnnotationUtil.setValue(annotation, annotationFieldName, fieldValue);
    }

}