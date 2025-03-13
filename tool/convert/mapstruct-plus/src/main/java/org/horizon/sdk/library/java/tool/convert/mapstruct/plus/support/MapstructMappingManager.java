package org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;
import org.horizon.sdk.library.java.tool.lang.number.Hexes;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapstruct 属性转换管理，用于绑定转换方法
 *
 * @author wjm
 * @since 2021-03-11 10:25
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MapstructMappingQualify
public class MapstructMappingManager {

    /**
     * List =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructListToString
    public static String listToString(List<String> value) {
        return Nil.isNotEmpty(value) ? value.toString() : null;
    }

    /**
     * List&lt;Integer&gt; =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructListIntegerToString
    public static String listIntegerToString(List<Integer> value) {
        return Nil.isNotEmpty(value) ? Strings.joinWithComma(value) : null;
    }

    /**
     * String =&gt; List&lt;String&gt;
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructStringToListString
    public static List<String> stringToListString(String value) {
        return Nil.isNotEmpty(value) ? Strings.splitByComma(value) : null;
    }

    /**
     * String =&gt; List&lt;Integer&gt;
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructStringToListInteger
    public static List<Integer> stringToListInteger(String value) {
        return Strings.splitByComma(value).stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * 展示 Enum 的字符串字段值
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructEnumToEnumString
    public static String enumToEnumString(Enum<?> value) {
        return Enums.getFieldValue(value, String.class);
    }

    /**
     * 展示 Enum 的数字字段值
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructEnumToEnumCode
    public static Integer enumToEnumNumber(Enum<?> value) {
        return Enums.getFieldValue(value, Integer.class);
    }

    /**
     * Byte[] =&gt; Hex String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructByteArrayToHexString
    public static String byteArrayToHexString(Byte[] value) {
        return Hexes.toString(value);
    }

    /**
     * LocalDateTime =&gt; Long
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructLocalDateTimeToLong
    public static Long localDateTimeToLong(LocalDateTime value) {
        return Times.toLong(value);
    }

    /**
     * Long =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @MapstructLongToString
    public static String longToString(Long value) {
        return value.toString();
    }

    /**
     * null List =&gt; Empty ArrayList
     *
     * @param value 待转换对象
     * @param <T>   待转换对象l类型
     * @return 转换结果
     */
    @MapstructNullListToEmptyArrayList
    public static <T> List<T> longToString(List<T> value) {
        if (Nil.isEmpty(value)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(value);
    }

}