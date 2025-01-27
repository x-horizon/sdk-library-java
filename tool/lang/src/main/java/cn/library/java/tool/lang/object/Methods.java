package cn.library.java.tool.lang.object;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.tool.lang.functional.Action;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * toolkit for method
 *
 * @author wjm
 * @since 2024-08-13 19:26
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Methods {

    /**
     * get class of the method define
     *
     * @param method the method
     * @return the class of the method
     */
    public static Class<?> getDeclaredClass(Method method) {
        return Action.<Class<?>>ifNull(method).then(() -> null).otherwise(method::getDeclaringClass).get();
    }

    /**
     * get the full name of specified method, example: cn.foo.FooClass.fooMethod(String fooName)
     *
     * @param method the specified method
     * @return the full name
     */
    public static String getFullName(Method method) {
        String methodDeclaredClassName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String methodParameterTypeName = Arrays.stream(method.getParameters()).map(parameter -> STR."\{parameter.getType().getSimpleName()} \{parameter.getName()}").collect(Collectors.joining(", "));
        return STR."\{methodDeclaredClassName}.\{methodName}(\{methodParameterTypeName})";
    }

}