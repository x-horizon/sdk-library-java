package org.horizon.sdk.library.java.tool.lang.reflect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.swagger.v3.oas.annotations.media.Schema;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.dromara.hutool.core.reflect.ConstructorUtil;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.dromara.hutool.core.reflect.ReflectUtil;
import org.dromara.hutool.core.reflect.method.MethodUtil;
import org.horizon.sdk.library.java.contract.constant.library.Knife4jClassNames;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.annotation.Annotations;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.functional.SerializableConsumer;
import org.horizon.sdk.library.java.tool.lang.functional.SerializableFunction;
import org.horizon.sdk.library.java.tool.lang.object.Classes;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * toolkit for reflect
 *
 * @author wjm
 * @since 2021-06-03 11:54
 */
@CanIgnoreReturnValue
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reflects {

    private static final String SERIALIZABLE_WRITE_REPLACE_METHOD_NAME = "writeReplace";

    private static final boolean IS_KNIFE4J_SCHEMA_CLASS_EXIST = Classes.isExist(Knife4jClassNames.SCHEMA);

    /**
     * see {@link MethodUtil#getMethod(Class, String, Class[])}
     *
     * @param input        the input element
     * @param methodName   the method name
     * @param methodParams the method params
     * @return the specified method
     */
    public static Method getMethod(Class<?> input, String methodName, Class<?>... methodParams) {
        return MethodUtil.getMethod(input, methodName, methodParams);
    }

    /**
     * see {@link FieldUtil#getFields(Class)}
     *
     * @param input the input element
     * @return all fields including parent classes in the input element
     */
    public static Field[] getFields(Class<?> input) {
        return FieldUtil.getFields(input);
    }

    /**
     * see {@link FieldUtil#getFieldValue(Object, String)}
     *
     * @param input     the input element
     * @param fieldName the field name to find value
     * @return the field value
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T> T getFieldValue(Object input, String fieldName) {
        return (T) FieldUtil.getFieldValue(input, fieldName);
    }

    /**
     * see {@link FieldUtil#getFieldValue(Object, String)}
     *
     * @param input      the input element
     * @param fieldName  the field name to find value
     * @param fieldClass the field type to find value
     * @param <T>        the input element type
     * @param <R>        the field type
     * @return the field value
     */
    public static <T, R> R getFieldValue(T input, String fieldName, Class<R> fieldClass) {
        return fieldClass.cast(FieldUtil.getFieldValue(input, fieldName));
    }

    /**
     * see {@link FieldUtil#getFieldValue(Object, Field)}
     *
     * @param input      the input element
     * @param field      the field to find value
     * @param fieldClass the field type to find value
     * @param <T>        the input element type
     * @param <R>        the field type
     * @return the field value
     */
    public static <T, R> R getFieldValue(T input, Field field, Class<R> fieldClass) {
        return fieldClass.cast(FieldUtil.getFieldValue(input, field));
    }

    /**
     * see {@link FieldUtil#getFieldValue(Object, Field)}
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
        return Try.of(() -> FieldUtil.getFieldValue(input, field)).getOrNull();
    }

    @SneakyThrows
    public static <T> String getFieldComment(SerializableConsumer<T> serializableConsumer) {
        return getFieldComment(Converts.toSerializableFunction(serializableConsumer));
    }

    @SneakyThrows
    public static <T, R> String getFieldComment(SerializableFunction<T, R> serializableFunction) {
        Method method = setAccessible(serializableFunction.getClass().getDeclaredMethod(SERIALIZABLE_WRITE_REPLACE_METHOD_NAME));
        SerializedLambda lambda = (SerializedLambda) method.invoke(serializableFunction);
        Class<T> fieldClass = Classes.getClass(lambda.getImplClass());
        String fieldName = Classes.getFieldName(lambda.getImplMethodName());
        Field field = Classes.getField(fieldClass, fieldName);
        if (IS_KNIFE4J_SCHEMA_CLASS_EXIST) {
            Schema schema = field.getAnnotation(Schema.class);
            assert schema != null;
            String fieldDescription = schema.description();
            fieldName = Nil.isBlank(fieldDescription) ? fieldName : fieldDescription;
        }
        return fieldName;
    }

    /**
     * return all methods in a class and parent classes.
     *
     * @param input the input element
     * @return all methods in a class and parent classes.
     * @see MethodUtil#getMethods(Class)
     */
    public static Method[] getMethods(Class<?> input) {
        return MethodUtil.getMethods(input);
    }

    /**
     * see {@link ReflectUtil#setAccessible(AccessibleObject)}
     *
     * @param input the input element
     * @param <T>   the input element type
     * @return return accessible object
     */
    public static <T extends AccessibleObject> T setAccessible(T input) {
        ReflectUtil.setAccessible(input);
        return input;
    }

    /**
     * see {@link FieldUtil#setFieldValue(Object, String, Object)}
     *
     * @param input      the object of field
     * @param fieldName  the field name
     * @param fieldValue the field value to set
     */
    public static void setFieldValue(Object input, String fieldName, Object fieldValue) {
        FieldUtil.setFieldValue(input, fieldName, fieldValue);
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
     * see {@link ConstructorUtil#newInstance(String)}
     *
     * @param className the class name
     * @param <T>       the class type
     * @return the class instance
     */
    public static <T> T newInstance(String className) {
        return ConstructorUtil.newInstance(className);
    }

    /**
     * see {@link ConstructorUtil#newInstance(Class, Object...)}
     *
     * @param input  the class name
     * @param params the constructor params
     * @param <T>    the class type
     * @return the class instance
     */
    public static <T> T newInstance(Class<T> input, Object... params) {
        return ConstructorUtil.newInstance(input, params);
    }

    /**
     * invoke method
     *
     * @param input      the input element
     * @param methodName the method name
     * @param params     the method params
     * @param <T>        the return type
     * @return the method result
     * @see MethodUtil#invoke(Object, String, Object...)
     */
    public static <T> T invoke(Object input, String methodName, Object... params) {
        return MethodUtil.invoke(input, methodName, params);
    }

    /**
     * invoke method
     *
     * @param input  the input element
     * @param method the method
     * @param params the method params
     * @param <T>    the return type
     * @return the method result
     * @see MethodUtil#invoke(Object, Method, Object...)
     */
    public static <T> T invoke(Object input, Method method, Object... params) {
        return MethodUtil.invoke(input, method, params);
    }

    /**
     * invoke static method
     *
     * @param method the static method
     * @param params the static method params
     * @param <T>    the return type
     * @return the static method result
     * @see MethodUtil#invokeStatic(Method, Object...)
     */
    public static <T> T invokeStatic(Method method, Object... params) {
        return MethodUtil.invokeStatic(method, params);
    }

}