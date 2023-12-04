// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.reflect;

import cn.hutool.core.util.ReflectUtil;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * toolkit for reflect
 *
 * @author wjm
 * @since 2021-06-03 11:54
 */
@SuppressWarnings(SuppressWarningConstant.UNUSED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reflects {

    /**
     * see {@link ReflectUtil#getMethod(Class, String, Class[])}
     *
     * @param input        the input element
     * @param methodName   the method name
     * @param methodParams the method params
     * @return the specified method
     */
    public static Method getMethod(Class<?> input, String methodName, Class<?>... methodParams) {
        return ReflectUtil.getMethod(input, methodName, methodParams);
    }

    /**
     * see {@link ReflectUtil#getFields(Class)}
     *
     * @param input the input element
     * @return all fields including parent classes in the input element
     */
    public static Field[] getFields(Class<?> input) {
        return ReflectUtil.getFields(input);
    }

    /**
     * see {@link ReflectUtil#getFieldValue(Object, String)}
     *
     * @param input     the input element
     * @param fieldName the field name to find value
     * @return the field value
     */
    public static Object getFieldValue(Object input, String fieldName) {
        return ReflectUtil.getFieldValue(input, fieldName);
    }

    /**
     * see {@link ReflectUtil#getFieldValue(Object, String)}
     *
     * @param input      the input element
     * @param fieldName  the field name to find value
     * @param fieldClass the field type to find value
     * @param <T>        the input element type
     * @param <R>        the field type
     * @return the field value
     */
    public static <T, R> R getFieldValue(T input, String fieldName, Class<R> fieldClass) {
        return fieldClass.cast(ReflectUtil.getFieldValue(input, fieldName));
    }

    /**
     * see {@link ReflectUtil#getFieldValue(Object, Field)}
     *
     * @param input      the input element
     * @param field      the field to find value
     * @param fieldClass the field type to find value
     * @param <T>        the input element type
     * @param <R>        the field type
     * @return the field value
     */
    public static <T, R> R getFieldValue(T input, Field field, Class<R> fieldClass) {
        return fieldClass.cast(ReflectUtil.getFieldValue(input, field));
    }

    /**
     * see {@link ReflectUtil#getFieldValue(Object, Field)}
     *
     * @param input the input element
     * @param field the field
     * @return the field value
     * @apiNote used to tolerate all exceptions,
     * mainly due to the modularity impact after JDK16,
     * which prevents reflection of private final scoped fields.
     * please confirm if necessary before calling this function.
     */
    public static Object getFieldValueIgnoreThrowable(Object input, Field field) {
        return Try.of(() -> ReflectUtil.getFieldValue(input, field)).getOrNull();
    }

    /**
     * return all methods in a class and parent classes.
     *
     * @param input the input element
     * @return all methods in a class and parent classes.
     * @see ReflectUtil#getMethods(Class)
     */
    public static Method[] getMethods(Class<?> input) {
        return ReflectUtil.getMethods(input);
    }

    /**
     * see {@link ReflectUtil#setAccessible(AccessibleObject)}
     *
     * @param input the input element
     * @param <T>   the input element type
     */
    public static <T extends AccessibleObject> void setAccessible(T input) {
        ReflectUtil.setAccessible(input);
    }

    /**
     * see {@link ReflectUtil#setFieldValue(Object, String, Object)}
     *
     * @param input      the object of field
     * @param fieldName  the field name
     * @param fieldValue the field value to set
     */
    public static void setFieldValue(Object input, String fieldName, Object fieldValue) {
        ReflectUtil.setFieldValue(input, fieldName, fieldValue);
    }

    /**
     * see {@link Annotations#setAnnotationValue(Annotation, Object)}
     *
     * @param annotation the annotation
     * @param fieldValue the field value
     */
    public static void setAnnotationValue(Annotation annotation, Object fieldValue) {
        Annotations.setAnnotationValue(annotation, fieldValue);
    }

    /**
     * see {@link Annotations#setAnnotationValue(Annotation, String, Object)}
     *
     * @param annotation          the annotation
     * @param annotationFieldName the annotation field name
     * @param fieldValue          the field value
     */
    public static void setAnnotationValue(Annotation annotation, String annotationFieldName, Object fieldValue) {
        Annotations.setAnnotationValue(annotation, annotationFieldName, fieldValue);
    }

    /**
     * see {@link ReflectUtil#newInstance(String)}
     *
     * @param className the class name
     * @param <T>       the class type
     * @return the class instance
     */
    public static <T> T newInstance(String className) {
        return ReflectUtil.newInstance(className);
    }

    /**
     * see {@link ReflectUtil#newInstance(Class, Object...)}
     *
     * @param input  the class name
     * @param params the constructor params
     * @param <T>    the class type
     * @return the class instance
     */
    public static <T> T newInstance(Class<T> input, Object... params) {
        return ReflectUtil.newInstance(input, params);
    }

    /**
     * invoke method
     *
     * @param input      the input element
     * @param methodName the method name
     * @param params     the method params
     * @param <T>        the return type
     * @return the method result
     * @see ReflectUtil#invoke(Object, String, Object...)
     */
    public static <T> T invoke(Object input, String methodName, Object... params) {
        return ReflectUtil.invoke(input, methodName, params);
    }

    /**
     * invoke method
     *
     * @param input  the input element
     * @param method the method
     * @param params the method params
     * @param <T>    the return type
     * @return the method result
     * @see ReflectUtil#invoke(Object, Method, Object...)
     */
    public static <T> T invoke(Object input, Method method, Object... params) {
        return ReflectUtil.invoke(input, method, params);
    }

    /**
     * invoke static method
     *
     * @param method the static method
     * @param params the static method params
     * @param <T>    the return type
     * @return the static method result
     * @see ReflectUtil#invokeStatic(Method, Object...)
     */
    public static <T> T invokeStatic(Method method, Object... params) {
        return ReflectUtil.invokeStatic(method, params);
    }

}
