package org.horizon.sdk.library.java.tool.spring.contract.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.springframework.cache.support.NullValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * toolkit for spring {@link NullValue}
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NullValues {

    /**
     * get {@link NullValue} class name
     *
     * @return {@link NullValue} class name
     */
    public static String getName() {
        return NullValue.class.getSimpleName();
    }

    /**
     * return true if it is {@link NullValue}
     *
     * @param input checked object
     * @return is it {@link NullValue}
     */
    public static boolean isNullValue(Object input) {
        return Nil.isNotNull(input) && Comparators.equals(NullValue.class, input.getClass());
    }

    /**
     * return true if it is not {@link NullValues}
     *
     * @param input checked object
     * @return is not {@link NullValue}
     */
    public static boolean isNotNullValue(Object input) {
        return !isNullValue(input);
    }

    /**
     * convert to {@link NullValue}
     *
     * @param input checked object
     * @param <T>   checked object type
     * @return null if it is {@link NullValue}, or do not convert
     */
    public static <T> T convertToNullIfNullValue(T input) {
        return isNullValue(input) ? null : input;
    }

    /**
     * convert to {@link NullValue}
     *
     * @param input           checked object
     * @param allowEmptyValue is allow {@link NullValue}
     * @return allow {@link NullValue} and checked object is null then return {@link NullValue}, or do not convert
     */
    public static Object convertNullToNullValueIfNeed(Object input, boolean allowEmptyValue) {
        return allowEmptyValue && Nil.isNull(input) ? NullValue.INSTANCE : input;
    }

    /**
     * remove the value is {@link NullValue} in map
     *
     * @param values the input elements
     * @param <K>    the map key type
     * @param <V>    the map value type
     * @return after remove the value is {@link NullValue} in map
     */
    public static <K, V> Map<K, V> removeNullValue(Map<K, V> values) {
        return values.entrySet()
                .stream()
                .filter(entry -> isNotNullValue(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * remove the value is {@link NullValue} in collection
     *
     * @param inputs the input elements
     * @param <T>    the value type
     * @return after remove the value is {@link NullValue} in collection
     */
    public static <T> List<T> removeNullValue(List<T> inputs) {
        return inputs.stream()
                .filter(NullValues::isNotNullValue)
                .collect(Collectors.toList());
    }

}