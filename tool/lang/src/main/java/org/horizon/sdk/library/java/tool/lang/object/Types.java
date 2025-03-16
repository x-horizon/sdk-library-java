package org.horizon.sdk.library.java.tool.lang.object;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.dromara.hutool.core.reflect.TypeUtil;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.functional.Action;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * toolkit for {@link Type}
 *
 * @author wjm
 * @since 2021-05-10 17:46
 */
@CanIgnoreReturnValue
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Types {

    private static final Map<Class<?>, Class<?>> CLASS_MAPPING_GENERIC_TYPE_MAP = Collections.newConcurrentHashMap();

    /**
     * return true if the field has generic
     *
     * @param input the input element
     * @return return true if the field has generic
     */
    public static boolean hasGeneric(Field input) {
        return Nil.isNotNull(input) && input.getGenericType() instanceof ParameterizedType;
    }

    /**
     * return true if the first parameter on method has generic
     *
     * @param input the input element
     * @return return true if the first parameter on method has generic
     */
    public static boolean isFirstParameterHasGeneric(Method input) {
        return Strings.containsAny(getFirstParameterTypeName(input), SymbolConstant.SINGLE_BOOK_NAME_LEFT, SymbolConstant.SINGLE_BOOK_NAME_RIGHT);
    }

    /**
     * get the first parameter type on method
     *
     * @param input the input element
     * @return the first parameter type on method
     */
    public static Type getFirstParameterType(Method input) {
        return getParameterType(input, 0);
    }

    /**
     * get the specified index(start from 0) parameter type on method
     *
     * @param input          the input element
     * @param specifiedIndex the specified index(start from 0)
     * @return the specified index(start from 0) parameter type on method
     */
    public static Type getParameterType(Method input, int specifiedIndex) {
        List<Type> types = getParameterTypes(input);
        if (types.size() > specifiedIndex) {
            return types.get(specifiedIndex);
        }
        return null;
    }

    /**
     * get all parameter types on method
     *
     * @param input the input element
     * @return all parameter types on method
     */
    public static List<Type> getParameterTypes(Method input) {
        return Action.<List<Type>>ifNull(input)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofArrayList(input.getGenericParameterTypes()))
                .get();
    }

    /**
     * get the first parameter type name on method
     *
     * @param input the input element
     * @return the first parameter type name on method
     * @see #getParameterTypeNames(Method)
     */
    public static String getFirstParameterTypeName(Method input) {
        return Action.<String>ifNull(input)
                .then(() -> SymbolConstant.EMPTY)
                .otherwise(() -> input.getGenericParameterTypes()[0].getTypeName())
                .get();
    }

    /**
     * get all parameter type names on method
     *
     * @param input the input element
     * @return all parameter type names on method
     * @apiNote if the method parameter has a generic type, the generic name will also be output, for example:
     * <ul>
     *   <li>the method parameter is List&lt;TestBean&gt;, then output is: java.util.List&lt;cn.test.bean.TestBean&gt;.</li>
     *   <li>the method parameter is List,                 then output is: java.util.List.</li>
     * </ul>
     */
    public static List<String> getParameterTypeNames(Method input) {
        return Converts.toList(getParameterTypes(input), Type::getTypeName);
    }

    /**
     * get return type on method
     *
     * @param input the input element
     * @return return type on method
     */
    public static Type getReturnType(Method input) {
        return Nil.isNull(input) ? null : input.getGenericReturnType();
    }

    /**
     * get return type name on method
     *
     * @param input the input element
     * @return return type name on method
     * @apiNote if the method return parameter has a generic type, the generic name will also be output, for example:
     * <ul>
     *   <li>the method parameter is List&lt;TestBean&gt;, then output is: java.util.List&lt;cn.test.bean.TestBean&gt;.</li>
     *   <li>the method parameter is List,                 then output is: java.util.List.</li>
     * </ul>
     */
    public static String getReturnTypeName(Method input) {
        return Action.<String>ifNull(input)
                .then(() -> SymbolConstant.EMPTY)
                .otherwise(() -> input.getGenericReturnType().getTypeName())
                .get();
    }

    /**
     * see {@link TypeUtil#getClass(Type)}
     *
     * @param input the input element
     * @return the original class of type
     */
    public static Class<?> getClass(Type input) {
        return TypeUtil.getClass(input);
    }

    /**
     * <p>retrieves the embedded generic types of the specified field.</p>
     *
     * <p>example analysis for different field types:</p>
     * <pre>{@code
     * public class Test {
     *     private List<String> field1;
     *     private List<List<?>> field2;
     *     private List<List<String>> field3;
     *     private List<? extends List<?>> field4;
     *     private List<? extends List<? extends List<?>>> field5;
     *     private String field6;
     * }
     * }</pre>
     *
     * <p>return value patterns:</p>
     * <ul>
     *  <li>{@code Types.getEmbedGenericTypes(Test.class, "field1")} → {@code [class java.lang.String]}</li>
     *  <li>{@code Types.getEmbedGenericTypes(Test.class, "field2")} → {@code [java.util.List<?>]}</li>
     *  <li>{@code Types.getEmbedGenericTypes(Test.class, "field3")} → {@code [java.util.List<java.lang.String>]}</li>
     *  <li>{@code Types.getEmbedGenericTypes(Test.class, "field4")} → {@code [? extends java.util.List<?>]}</li>
     *  <li>{@code Types.getEmbedGenericTypes(Test.class, "field5")} → {@code [? extends java.util.List<? extends java.util.List<?>>]}</li>
     *  <li>{@code Types.getEmbedGenericTypes(Test.class, "field6")} → {@code [java.lang.String]}</li>
     * </ul>
     *
     * <p>recommended companion methods:</p>
     * <ul>
     *  <li>for simple generic fields → {@link #getEmbedGenericTypeClass(Class, String)}</li>
     *  <li>for non-generic fields → {@link #getTypeClass(Class, String)}</li>
     * </ul>
     *
     * @param fieldLocatedClass the declaring class containing the field
     * @param fieldName         the name of the field to analyze
     * @return an array of {@link Type} objects representing the generic parameters,
     * or empty array if no generics exist
     * @see #getTypeClass(Class, String)
     * @see #getEmbedGenericTypeClass(Class, String)
     */
    @SneakyThrows
    public static Type[] getEmbedGenericTypes(Class<?> fieldLocatedClass, String fieldName) {
        return Action.<Type[]>infer(Nil.isNull(fieldLocatedClass) || Nil.isBlank(fieldName))
                .then(() -> Collections.newArray(Type.class))
                .otherwise(() -> ((ParameterizedType) Classes.getFieldDeep(fieldLocatedClass, fieldName).getGenericType()).getActualTypeArguments())
                .get();
    }

    /**
     * <p>retrieves the component type of the specified array class.</p>
     *
     * <p>usage examples:</p>
     * <pre>{@code
     * // String[].class → returns String.class
     * Class<?> arrayType = Types.getArrayGenericType(String[].class);
     *
     * // String.class → returns null
     * Class<?> nonArrayType = Types.getArrayGenericType(String.class);
     *
     * // List.class → returns null (can use {@link #getEmbedGenericTypes(Class, String)} for collections)
     * Class<?> collectionType = Types.getArrayGenericType(List.class);
     * }</pre>
     *
     * <p>key characteristics:</p>
     * <ul>
     *  <li>returns array component type for array classes</li>
     *  <li>returns {@code null} for non-array classes</li>
     *  <li>does not resolve generic type parameters for collections</li>
     * </ul>
     *
     * <p>related methods:</p>
     * <ul>
     *  <li>for collection generic types → {@link #getEmbedGenericTypeClass(Class, String)}</li>
     *  <li>for field type resolution → {@link #getTypeClass(Class, String)}</li>
     * </ul>
     *
     * @param input the specified class
     * @return the generic type of the specified array class
     */
    public static Class<?> getArrayGenericType(Class<?> input) {
        return Nil.isNull(input) ? null : input.getComponentType();
    }

    /**
     * <p>retrieves the resolved generic type of the specified class first type parameter.</p>
     *
     * <p>usage examples:</p>
     * <pre>{@code
     * class Test1<T> {}  // Generic class declaration
     * class Test2 implements TestInterface<String> {}  // Interface implementation
     * class Test3<T extends CharSequence> {}  // Bounded type parameter
     * class Test4 {}  // Non-generic class
     *
     * // returns String.class for Test1<String>
     * Class<?> type1 = Types.getClassGenericType(Test1.class);
     *
     * // returns String.class for Test2 implementing TestInterface<String>
     * Class<?> type2 = Types.getClassGenericType(Test2.class);
     *
     * // throws ClassCastException for bounded types (Test3<T extends CharSequence>)
     * Class<?> type3 = Types.getClassGenericType(Test3.class);
     *
     * // returns null for non-generic class (Test4)
     * Class<?> type4 = Types.getClassGenericType(Test4.class);
     * }</pre>
     *
     * <p>key constraints:</p>
     * <ul>
     *  <li>only resolves first type parameter</li>
     *  <li>requires concrete type binding in class hierarchy</li>
     *  <li>supports simple type parameters and interface implementations</li>
     *  <li><em>does not support:</em>
     *    <ul>
     *      <li>bounded/wildcard types (e.g., {@code T extends CharSequence})</li>
     *      <li>nested generic parameters</li>
     *    </ul>
     *  </li>
     * </ul>
     *
     * @param input the specified class
     * @return the first generic type of the specified class
     * @throws ClassCastException when encountering unsupported generic declarations
     * @see TypeUtil#getGenerics(Class)
     */
    public static Class<?> getClassGenericType(Class<?> input) {
        return CLASS_MAPPING_GENERIC_TYPE_MAP.computeIfAbsent(input, ignore -> (Class<?>) Arrays.stream(TypeUtil.getGenerics(input))
                .findFirst()
                .map(ParameterizedType::getActualTypeArguments)
                .stream()
                .flatMap(Arrays::stream)
                .findFirst()
                .orElse(null)
        );
    }

    /**
     * see {@link #getEmbedGenericTypes(Class, String)}
     *
     * @param fieldLocatedClass the class where this field is located
     * @param fieldName         the field name
     * @return the embed generic types
     */
    public static Class<?> getEmbedGenericTypeClass(Class<?> fieldLocatedClass, String fieldName) {
        return (Class<?>) getEmbedGenericTypes(fieldLocatedClass, fieldName)[0];
    }

    /**
     * see {@link #getEmbedGenericTypes(Class, String)}
     *
     * @param fieldLocatedClass the class where this field is located
     * @param fieldName         the field name
     * @return the field class
     */
    public static Class<?> getTypeClass(Class<?> fieldLocatedClass, String fieldName) {
        Field field = Classes.getFieldDeep(fieldLocatedClass, fieldName);
        return Nil.isNull(field) ? null : field.getType();
    }

}