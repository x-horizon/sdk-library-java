package cn.srd.itcp.sugar.tools.core;

import cn.srd.itcp.sugar.tools.constant.StringPool;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Type 工具
 *
 * @author wjm
 * @date 2021/5/10 17:46
 */
public class TypesUtil {

    private TypesUtil() {
    }

    /**
     * 获取方法的第一个入参类型
     *
     * @param method
     * @return
     */
    public static Type getFirstParametricType(@Nullable Method method) {
        return getParametricType(method, 0);
    }

    /**
     * 获取方法第 n 个入参类型，从 0 开始
     *
     * @param method
     * @param index
     * @return
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
     * @param method
     * @return
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
     * @param method
     * @return
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
     *      例如方法入参为 List<`TestBean>：
     *        带泛型输出：method.getGenericParameterTypes() -> java.util.List<`cn.test.bean.TestBean>
     *        不带泛型输出：method.getParameterTypes() -> java.util.List
     *   示例：
     *      cn.test.bean.TestBean
     *      java.util.List<`cn.test.bean.TestBean>
     *      java.util.Set<`cn.test.bean.TestBean>
     * </pre>
     *
     * @param method
     * @return
     */
    @NonNull
    public static List<String> getParameterTypeNames(@Nullable Method method) {
        return CollectionsUtil.toList(getParametricTypes(method), Type::getTypeName);
    }

    /**
     * 获取方法出参类型
     *
     * @param method
     * @return
     */
    public static Type getReturnType(@Nullable Method method) {
        return method == null ? null : method.getGenericReturnType();
    }

    /**
     * 获取方法出参类型的名字
     * <pre>
     *   本方法对于出参若有泛型，会带泛型输出：
     *      例如方法出参为 List<`TestBean>：
     *        带泛型输出：method.getGenericParameterTypes() -> java.util.List<`cn.test.bean.TestBean>
     *        不带泛型输出：method.getParameterTypes() -> java.util.List
     *   示例：
     *      cn.test.bean.TestBean
     *      java.util.List<`cn.test.bean.TestBean>
     *      java.util.Set<`cn.test.bean.TestBean>
     * </pre>
     *
     * @param method
     * @return
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
     * @param method
     * @return
     */
    public static boolean isFirstParameterExistGeneric(@Nullable Method method) {
        return StringsUtil.containsAny(getFirstParametricTypeName(method), StringPool.SINGLE_BOOK_NAME_LEFT, StringPool.SINGLE_BOOK_NAME_RIGHT);
    }

    /**
     * 获得Type对应的原始类
     *
     * @param type
     * @return
     */
    public static Class<?> getClass(@Nullable Type type) {
        if (null != type) {
            if (type instanceof Class) {
                return (Class<?>) type;
            } else if (type instanceof ParameterizedType) {
                return (Class<?>) ((ParameterizedType) type).getRawType();
            } else if (type instanceof TypeVariable) {
                return (Class<?>) ((TypeVariable<?>) type).getBounds()[0];
            } else if (type instanceof WildcardType) {
                final Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                if (upperBounds.length == 1) {
                    return getClass(upperBounds[0]);
                }
            }
        }
        return null;
    }

}