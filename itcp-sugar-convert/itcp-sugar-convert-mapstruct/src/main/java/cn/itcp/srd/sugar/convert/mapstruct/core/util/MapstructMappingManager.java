package cn.itcp.srd.sugar.convert.mapstruct.core.util;

import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.core.HexsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Mapstruct 属性转换管理，用于绑定转换方法
 *
 * @author wjm
 * @date 2021/3/11 10:25
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
    @MapstructEnumToEnumNumber
    public static Integer enumToEnumNumber(@Nullable Enum<?> value) {
        return EnumsUtil.getEnumValue(value, Integer.class);
    }

    /**
     * Byte[] => Hex String
     *
     * @param value
     * @return
     */
    @MapstructByteArrayToHexString
    public static String byteArrayToHexString(@Nullable Byte[] value) {
        return HexsUtil.hexToString(value, false);
    }

}
