package cn.library.java.tool.lang.object;

import cn.hutool.core.util.TypeUtil;
import cn.library.java.contract.constant.text.SymbolConstant;
import cn.library.java.tool.lang.collection.Collections;
import cn.library.java.tool.lang.convert.Converts;
import cn.library.java.tool.lang.functional.Action;
import cn.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

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
                .then(() -> Collections.newArrayList())
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
                .otherwise(() -> {
                    Type firstParameterType = input.getGenericParameterTypes()[0];
                    return Nil.isNull(firstParameterType) ? SymbolConstant.EMPTY : firstParameterType.getTypeName();
                })
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
     * <pre>
     * get the embed generic types
     *
     * for example：
     *
     *  public class Test {
     *      private List&lt;String&gt; field1;
     *      private List&lt;List&lt;?&gt;&gt; field2;
     *      private List&lt;List&lt;String&gt;&gt; field3;
     *      private List&lt;? extends List&lt;?&gt;&gt; field4;
     *      private List&lt;? extends List&lt;? extends List&lt;?&gt;&gt;&gt; field5;
     *      private String field6;
     *  }
     *
     *  Types.getEmbedGenericTypes(Test.class, "field1"), the output is: Type[0] {Class@1994}                 class java.lang.String
     *  Types.getEmbedGenericTypes(Test.class, "field2"), the output is: Type[0] {ParameterizedTypeImpl@1994} java.util.List&lt;?&gt;
     *  Types.getEmbedGenericTypes(Test.class, "field3"), the output is: Type[0] {ParameterizedTypeImpl@1994} java.util.List&lt;java.lang.String&gt;
     *  Types.getEmbedGenericTypes(Test.class, "field4"), the output is: Type[0] {WildcardTypeImpl@1994}      ? extends java.util.List&lt;?&gt;
     *  Types.getEmbedGenericTypes(Test.class, "field5"), the output is: Type[0] {WildcardTypeImpl@1994}      ? extends java.util.List&lt;? extends java.util.List&lt;?&gt;&gt;
     *  Types.getEmbedGenericTypes(Test.class, "field6"), the output is: Class   {Class@1994}                 class java.lang.String
     *
     *  about field1，you can use {@link #getEmbedGenericTypeClass(Class, String)} to get the generic class
     *  about field6，you can use {@link #getTypeClass(Class, String)} to get the generic class
     * </pre>
     *
     * @param fieldLocatedClass the class where this field is located
     * @param fieldName         the field name
     * @return the embed generic types
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
     * <pre>
     * get the generic type of the specified array class.
     *
     * example code:
     * {@code
     *      public class Test {
     *          public static void main(String[] args) {
     *              // the output is String.class.
     *              Types.getArrayGenericType(String[].class);
     *              // the output is null.
     *              Types.getArrayGenericType(String.class);
     *              // the output is null.
     *              // see {@link #getEmbedGenericTypeClass(Class, String)} to get embed generic type
     *              Types.getArrayGenericType(List.class);
     *          }
     *      }
     * }
     * </pre>
     *
     * @param input the specified class
     * @return the generic type of the specified array class
     */
    public static Class<?> getArrayGenericType(Class<?> input) {
        return Nil.isNull(input) ? null : input.getComponentType();
    }

    /**
     * <pre>
     * get the generic type of the specified class.
     *
     * example code:
     * {@code
     *      public class Test1<String> {
     *
     *      }
     *
     *      public class Test2 implement TestInterface<String> {
     *
     *      }
     *
     *      public class Test3<T extends CharSequence> {
     *
     *      }
     *
     *      public class Test4 {
     *
     *      }
     *
     *      public class Test {
     *          public static void main(String[] args) {
     *              // the output is String.class.
     *              Types.getClassGenericType(Test1);
     *              // the output is String.class.
     *              Types.getClassGenericType(Test2);
     *              // unsupported embed generic type, will throw {@link ClassCastException}.
     *              Types.getClassGenericType(Test3);
     *              // the output is null.
     *              Types.getClassGenericType(Test4);
     *          }
     *      }
     * }
     * </pre>
     *
     * @param input the specified class
     * @return the generic type of the specified class
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