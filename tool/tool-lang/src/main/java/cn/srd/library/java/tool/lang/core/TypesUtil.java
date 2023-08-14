package cn.srd.library.java.tool.lang.core;

import cn.srd.library.java.tool.constant.core.StringPool;
import cn.srd.library.java.tool.lang.core.object.Objects;
import cn.srd.library.java.tool.lang.core.validation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Type 工具
 *
 * @author wjm
 * @since 2021/5/10 17:46
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypesUtil {

    /**
     * 获取方法的第一个入参类型
     *
     * @param method 输入方法对象
     * @return 方法的第一个入参类型
     */
    public static Type getFirstParametricType(@Nullable Method method) {
        return getParametricType(method, 0);
    }

    /**
     * 获取方法第 n 个入参类型，从 0 开始
     *
     * @param method 输入方法对象
     * @param index  索引位置
     * @return 方法的第 n 个入参类型
     */
    public static Type getParametricType(@Nullable Method method, int index) {
        List<Type> types = getParametricTypes(method);
        if (types.size() > index) {
            return types.get(index);
        }
        return null;
    }

    /**
     * 获取方法的入参类型列表
     *
     * @param method 输入方法对象
     * @return 方法的入参类型列表
     */
    @NonNull
    public static List<Type> getParametricTypes(@Nullable Method method) {
        if (Objects.isNull(method)) {
            return new ArrayList<>();
        }
        return CollectionsUtil.newArrayList(method.getGenericParameterTypes());
    }

    /**
     * 获取方法的第一个入参类型的名字，参考
     *
     * @param method 输入方法对象
     * @return 方法的第一个入参类型的名字
     */
    @NonNull
    public static String getFirstParametricTypeName(@Nullable Method method) {
        if (Objects.isNull(method)) {
            return StringPool.EMPTY;
        }
        Type firstParameterType = method.getGenericParameterTypes()[0];
        return firstParameterType == null ? StringPool.EMPTY : firstParameterType.getTypeName();
    }

    /**
     * 获取方法所有入参类型的名字
     * <pre>
     *   本方法对于入参若有泛型，会带泛型输出：
     *      例如方法入参为 List&lt;TestBean&gt;：
     *        带泛型输出：method.getGenericParameterTypes() -> java.util.List&lt;cn.test.bean.TestBean&gt;
     *        不带泛型输出：method.getParameterTypes() -> java.util.List
     *   示例：
     *      cn.test.bean.TestBean
     *      java.util.List&lt;cn.test.bean.TestBean&gt;
     *      java.util.Set&lt;cn.test.bean.TestBean&gt;
     * </pre>
     *
     * @param method 输入方法对象
     * @return 方法所有入参类型的名字
     */
    @NonNull
    public static List<String> getParameterTypeNames(@Nullable Method method) {
        return CollectionsUtil.toList(getParametricTypes(method), Type::getTypeName);
    }

    /**
     * 获取方法出参类型
     *
     * @param method 输入方法对象
     * @return 方法出参类型
     */
    public static Type getReturnType(@Nullable Method method) {
        return method == null ? null : method.getGenericReturnType();
    }

    /**
     * 获取方法出参类型的名字
     * <pre>
     *   本方法对于出参若有泛型，会带泛型输出：
     *      例如方法出参为 List&lt;TestBean&gt;：
     *        带泛型输出：method.getGenericParameterTypes() -> java.util.List&lt;cn.test.bean.TestBean&gt;
     *        不带泛型输出：method.getParameterTypes() -> java.util.List
     *   示例：
     *      cn.test.bean.TestBean
     *      java.util.List&lt;cn.test.bean.TestBean&gt;
     *      java.util.Set&lt;cn.test.bean.TestBean&gt;
     * </pre>
     *
     * @param method 输入方法对象
     * @return 方法出参类型的名字
     */
    @NonNull
    public static String getReturnTypeName(@Nullable Method method) {
        if (Objects.isNull(method)) {
            return StringPool.EMPTY;
        }
        return method.getGenericReturnType().getTypeName();
    }

    /**
     * 判断方法的第一个入参是否存在泛型
     *
     * @param method 输入方法对象
     * @return 方法的第一个入参是否存在泛型
     */
    public static boolean isFirstParameterExistGeneric(@Nullable Method method) {
        return StringsUtil.containsAny(getFirstParametricTypeName(method), StringPool.SINGLE_BOOK_NAME_LEFT, StringPool.SINGLE_BOOK_NAME_RIGHT);
    }

    /**
     * 获得Type对应的原始类
     *
     * @param type 类型对象
     * @return 类型对象原始类对象
     */
    public static Class<?> getClass(@Nullable Type type) {
        if (null != type) {
            if (type instanceof Class<?> check) {
                return check;
            } else if (type instanceof ParameterizedType check) {
                return (Class<?>) check.getRawType();
            } else if (type instanceof TypeVariable<?> check) {
                return (Class<?>) check.getBounds()[0];
            } else if (type instanceof WildcardType check) {
                final Type[] upperBounds = check.getUpperBounds();
                if (upperBounds.length == 1) {
                    return getClass(upperBounds[0]);
                }
            }
        }
        return null;
    }

    /**
     * see {@link #getEmbedGenericTypes(Class, String)}
     *
     * @param fieldOfClass 字段所在的类
     * @param fieldName    字段名
     * @return 泛型类类型
     */
    public static Class<?> getEmbedGenericTypeClass(Class<?> fieldOfClass, String fieldName) {
        return (Class<?>) getEmbedGenericTypes(fieldOfClass, fieldName)[0];
    }

    /**
     * see {@link #getEmbedGenericTypes(Class, String)}
     *
     * @param fieldOfClass 字段所在的类
     * @param fieldName    字段名
     * @return 字段类型
     */
    public static Class<?> getTypeClass(Class<?> fieldOfClass, String fieldName) {
        Field field = ClassesUtil.getFieldDeep(fieldOfClass, fieldName);
        return Objects.isNull(field) ? null : field.getType();
    }

    /**
     * 获取泛型类类型
     * <pre>
     *  示例类：
     *  public class Test {
     *      private List&lt;String&gt; field1;
     *      private List&lt;List&lt;?&gt;&gt; field2;
     *      private List&lt;List&lt;String&gt;&gt; field3;
     *      private List&lt;? extends List&lt;?&gt;&gt; field4;
     *      private List&lt;? extends List&lt;? extends List&lt;?&gt;&gt;&gt; field5;
     *      private String field6;
     *  }
     *
     *  入参：Test.class, "field1" ，结果： Type[0] {Class@1994}                 class java.lang.String
     *  入参：Test.class, "field2" ，结果： Type[0] {ParameterizedTypeImpl@1994} java.util.List&lt;?&gt;
     *  入参：Test.class, "field3" ，结果： Type[0] {ParameterizedTypeImpl@1994} java.util.List&lt;java.lang.String&gt;
     *  入参：Test.class, "field4" ，结果： Type[0] {WildcardTypeImpl@1994}      ? extends java.util.List&lt;?&gt;
     *  入参：Test.class, "field5" ，结果： Type[0] {WildcardTypeImpl@1994}      ? extends java.util.List&lt;? extends java.util.List&lt;?&gt;&gt;
     *  入参：Test.class, "field6" ，结果： Class   {Class@1994}                 class java.lang.String
     *
     *  对于 field1，可通过 {@link #getEmbedGenericTypeClass(Class, String)} 直接获取泛型类类型
     *  对于 field6，可通过 {@link #getTypeClass(Class, String)} 获取类类型
     * </pre>
     *
     * @param fieldOfClass 字段所在的类
     * @param fieldName    字段名
     * @return 泛型类类型
     */
    @SneakyThrows
    public static Type[] getEmbedGenericTypes(Class<?> fieldOfClass, String fieldName) {
        return ((ParameterizedType) ClassesUtil.getFieldDeep(fieldOfClass, fieldName).getGenericType()).getActualTypeArguments();
    }

}