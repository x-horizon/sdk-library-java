// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.annotation;

import cn.hutool.core.annotation.AnnotationUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author wjm
 * @since 2021-03-21 19:22
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Annotations {

    /**
     * see {@link #getAnnotationValue(AnnotatedElement, Class, Class, String)}
     *
     * @param annotatedElement the annotated element
     * @param annotationType   the annotation type
     * @param <T>              the field type
     * @return the annotation
     */
    public static <T extends Annotation> T getAnnotation(AnnotatedElement annotatedElement, Class<T> annotationType) {
        return AnnotationUtil.getAnnotation(annotatedElement, annotationType);
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
        return fieldType.cast(AnnotationUtil.getAnnotationValue(annotatedElement, annotationType));
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
     *                Annotations.getAnnotationValue(Test.class, TestAnnotation.class, "fieldName");
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

    public static void setAnnotationValue(Annotation annotation, String annotationFieldName, Object valueToSet) {
        AnnotationUtil.setValue(annotation, annotationFieldName, valueToSet);
    }

}
