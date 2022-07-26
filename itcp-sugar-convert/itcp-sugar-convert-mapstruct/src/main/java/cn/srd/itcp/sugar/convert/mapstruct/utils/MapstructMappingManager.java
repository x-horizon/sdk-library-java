package cn.srd.itcp.sugar.convert.mapstruct.utils;

import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.core.HexsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.TimeUtil;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapstruct 属性转换管理，用于绑定转换方法
 *
 * @author wjm
 * @since 2021/3/11 10:25
 */
@MapstructMappingQualify
public class MapstructMappingManager {

    /**
     * List => String
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructListToString
    public static String listToString(@Nullable List<String> value) {
        return Objects.isNotEmpty(value) ? value.toString() : null;
    }

    /**
     * String => List
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructStringToList
    public static List<String> stringToList(@Nullable String value) {
        return Objects.isNotEmpty(value) ? CollectionsUtil.toList(value) : null;
    }

    /**
     * 展示 Enum 的字符串字段值
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructEnumToEnumString
    public static String enumToEnumString(@Nullable Enum<?> value) {
        return EnumsUtil.getEnumValue(value, String.class);
    }

    /**
     * 展示 Enum 的数字字段值
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructEnumToEnumCode
    public static Integer enumToEnumNumber(@Nullable Enum<?> value) {
        return EnumsUtil.getEnumValue(value, Integer.class);
    }

    /**
     * Byte[] => Hex String
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructByteArrayToHexString
    public static String byteArrayToHexString(@Nullable Byte[] value) {
        return HexsUtil.hexToString(value, false);
    }

    /**
     * LocalDateTime => Long
     *
     * @param value
     * @return
     */
    @NonNull
    @MapstructLocalDateTimeToLong
    public static Long localDateTimeToLong(@NonNull LocalDateTime value) {
        return TimeUtil.toLong(value);
    }

    /**
     * Long => String
     *
     * @param value
     * @return
     */
    @NonNull
    @MapstructLongToString
    public static String longToString(@NonNull Long value) {
        return value.toString();
    }

    /**
     * JSONObject => String
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructJSONObjectToStringFormatter
    public static String jsonToString(@Nullable JSONObject value) {
        return Objects.isNotEmpty(value) ? value.toJSONString() : null;
    }

    /**
     * null List => Empty ArrayList
     *
     * @param value
     * @return
     */
    @NonNull
    @MapstructNullListToEmptyArrayList
    public static <T> ArrayList<T> longToString(@NonNull List<T> value) {
        if (Objects.isEmpty(value)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(value);
    }

}
