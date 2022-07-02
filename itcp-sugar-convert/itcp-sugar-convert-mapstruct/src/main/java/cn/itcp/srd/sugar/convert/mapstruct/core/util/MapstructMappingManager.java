package cn.itcp.srd.sugar.convert.mapstruct.core.util;

import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.springframework.lang.NonNull;
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
     * String => JSONObject
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructStringToJSON
    public static JSONObject stringToJSON(@Nullable String value) {
        return JSON.parseObject(value);
    }

    /**
     * JSONObject => String
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructJSONToString
    public static String jsonToString(@Nullable JSONObject value) {
        return Objects.isNotEmpty(value) ? value.toJSONString() : null;
    }

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
     * Bean => JSONObject，使用驼峰原则命名
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructBeanToJSON
    public static JSONObject beanToJSON(@Nullable Object value) {
        return beanToJSONObject(value, null);
    }

    /**
     * Bean => String，先转 JSONObject，再转 String
     *
     * @param value
     * @return
     */
    @Nullable
    @MapstructBeanToString
    public static String beanToString(@Nullable Object value) {
        JSONObject json = beanToJSONObject(value, null);
        if (Objects.isNotEmpty(json)) {
            return json.toJSONString();
        }
        return null;
    }

    /**
     * List => JSONArray，使用驼峰原则命名
     *
     * @param value
     * @return
     */
    @NonNull
    @MapstructListToJSONArray
    public static JSONArray listToJSONArray(@Nullable List<?> value) {
        return JSON.parseArray(JSON.toJSONString(value));
    }

    /**
     * 实体类转换 JSONObject，使用驼峰原则命名
     *
     * @param obj
     * @param filter 过滤配置
     * @return
     */
    private static JSONObject beanToJSONObject(Object obj, @Nullable SimplePropertyPreFilter filter) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
        return JSON.parseObject(JSON.toJSONString(obj, config, filter));
    }

}
