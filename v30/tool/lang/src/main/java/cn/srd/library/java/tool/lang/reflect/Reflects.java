// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.reflect;

import cn.hutool.core.util.ReflectUtil;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * toolkit for reflect
 *
 * @author wjm
 * @since 2021-06-03 11:54
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reflects {

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
     * <pre>
     * see {@link ReflectUtil#getFieldValue(Object, Field)}
     *
     * note:
     *  used to tolerate all exceptions,
     *  mainly due to the modularity impact after JDK16,
     *  which prevents reflection of private final scoped fields.
     *  please confirm if necessary before calling this function.
     * </pre>
     *
     * @param input the input element
     * @param field the field
     * @return the field value
     */
    public static Object getFieldValueIgnoreThrowable(Object input, Field field) {
        return Try.of(() -> ReflectUtil.getFieldValue(input, field)).getOrNull();
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

}
