package cn.srd.itcp.sugar.component.convert.mapstruct.utils;

import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.*;
import cn.srd.itcp.sugar.tool.core.algorithm.Algorithms;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapstruct 属性转换管理，用于绑定转换方法
 *
 * @author wjm
 * @since 2021/3/11 10:25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@MapstructMappingQualify
public class MapstructMappingManager {

    /**
     * List =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructListToString
    public static String listToString(@Nullable List<String> value) {
        return Objects.isNotEmpty(value) ? value.toString() : null;
    }

    /**
     * List&lt;Integer&gt; =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructListIntegerToString
    public static String listIntegerToString(@Nullable List<Integer> value) {
        return Objects.isNotEmpty(value) ? StringsUtil.join(StringPool.COMMA, value) : null;
    }

    /**
     * String =&gt; List&lt;String&gt;
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructStringToListString
    public static List<String> stringToListString(@Nullable String value) {
        return Objects.isNotEmpty(value) ? CollectionsUtil.toList(value) : null;
    }

    /**
     * String =&gt; List&lt;Integer&gt;
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructStringToListInteger
    public static List<Integer> stringToListInteger(@Nullable String value) {
        return StringsUtil.splitToListInteger(value, StringPool.COMMA);
    }

    /**
     * 展示 Enum 的字符串字段值
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructEnumToEnumString
    public static String enumToEnumString(@Nullable Enum<?> value) {
        return EnumsUtil.getEnumValue(value, String.class);
    }

    /**
     * 展示 Enum 的数字字段值
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructEnumToEnumCode
    public static Integer enumToEnumNumber(@Nullable Enum<?> value) {
        return EnumsUtil.getEnumValue(value, Integer.class);
    }

    /**
     * Byte[] =&gt; Hex String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructByteArrayToHexString
    public static String byteArrayToHexString(@Nullable Byte[] value) {
        return HexsUtil.hexToString(value, false);
    }

    /**
     * LocalDateTime =&gt; Long
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @Nullable
    @MapstructLocalDateTimeToLong
    public static Long localDateTimeToLong(@Nullable LocalDateTime value) {
        return TimeUtil.toLong(value);
    }

    /**
     * Long =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @NonNull
    @MapstructLongToString
    public static String longToString(@NonNull Long value) {
        return value.toString();
    }

    /**
     * {@link JSONObject} =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @NonNull
    @MapstructJSONObjectToStringFormatter
    public static String jsonObjectToString(@Nullable JSONObject value) {
        return Objects.isNotEmpty(value) ? value.toJSONString() : StringPool.EMPTY;
    }

    /**
     * String =&gt; {@link JSONObject}
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @NonNull
    @MapstructStringToJSONObjectFormatter
    public static JSONObject stringToJSONObject(@Nullable String value) {
        return Objects.isNotBlank(value) ? JSON.parseObject(value) : new JSONObject();
    }

    /**
     * null List =&gt; Empty ArrayList
     *
     * @param value 待转换对象
     * @param <T>   待转换对象l类型
     * @return 转换结果
     */
    @NonNull
    @MapstructNullListToEmptyArrayList
    public static <T> List<T> longToString(@NonNull List<T> value) {
        if (Objects.isEmpty(value)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(value);
    }

    /**
     * Boolean =&gt; String
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @NonNull
    @MapstructBooleanToString
    public static String booleanToString(@Nullable Boolean value) {
        return Objects.isNull(value) ? "未知" : BooleansUtil.toString(value, "是", "否");
    }

    /**
     * 数字 =&gt; 高位
     *
     * @param value 待转换对象
     * @return 转换结果
     */
    @NonNull
    @ParseHighestOneBit
    public static List<Integer> parseHighestOneBit(Integer value) {
        return Algorithms.parseHighestOneBit(value);
    }

}
