package cn.srd.itcp.sugar.framework.spring.tool.common.core;

import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.cache.support.NullValue;

/**
 * {@link NullValue} tool
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
public class NullValueUtil {

    /**
     * private block constructor
     */
    private NullValueUtil() {
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
    public static <T> T convertNullValueToNullIfNeed(T input) {
        return isNullValue(input) ? null : input;
    }

    /**
     * convert {@link NullValue} to null if need
     *
     * @param input          checked object
     * @param allowNullValue is allow {@link NullValue}
     * @return allow {@link NullValue} and checked object is null then return {@link NullValue}, or do not convert
     */
    public static Object convertNullToNullValueIfNeed(Object input, boolean allowNullValue) {
        return allowNullValue && Objects.isNull(input) ? NullValue.INSTANCE : input;
    }

}
