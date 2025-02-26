package cn.srd.library.java.tool.spring.common.core;

import cn.srd.library.java.tool.lang.core.object.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cache.support.NullValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link NullValue} tool
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NullValueUtil {

    /**
     * get {@link NullValue} class name
     *
     * @return {@link NullValue} class name
     */
    public static String getName() {
        return NullValue.class.getSimpleName();
    }

    /**
     * is it {@link NullValue}
     *
     * @param input checked object
     * @return is it {@link NullValue}
     */
    public static boolean isNullValue(Object input) {
        return Objects.isNotNull(input) && Objects.equals(NullValue.class, input.getClass());
    }

    /**
     * is not {@link NullValue}
     *
     * @param input checked object
     * @return is not {@link NullValue}
     */
    public static boolean isNotNullValue(Object input) {
        return !isNullValue(input);
    }

    /**
     * convert null to {@link NullValue} if need
     *
     * @param input checked object
     * @param <T>   checked object type
     * @return null if it is {@link NullValue}, or do not convert
     */
    public static <T> T convertToNullIfNullValue(T input) {
        return isNullValue(input) ? null : input;
    }

    /**
     * convert {@link NullValue} to null if need
     *
     * @param input           checked object
     * @param allowEmptyValue is allow {@link NullValue}
     * @return allow {@link NullValue} and checked object is null then return {@link NullValue}, or do not convert
     */
    public static Object convertNullToNullValueIfNeed(Object input, boolean allowEmptyValue) {
        return allowEmptyValue && Objects.isNull(input) ? NullValue.INSTANCE : input;
    }

    /**
     * remove the value is {@link NullValue} in map
     *
     * @param values the input
     * @param <K>    the map key type
     * @param <V>    the map value type
     * @return after remove the value is {@link NullValue} in map
     */
    public static <K, V> Map<K, V> filterNullValue(Map<K, V> values) {
        return values.entrySet().stream()
                .filter(entry -> isNotNullValue(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * remove the value is {@link NullValue} in collection
     *
     * @param values the input
     * @param <T>    the value type
     * @return after remove the value is {@link NullValue} in collection
     */
    public static <T> List<T> filterNullValue(List<T> values) {
        return values.stream().filter(NullValueUtil::isNotNullValue).toList();
    }

}
