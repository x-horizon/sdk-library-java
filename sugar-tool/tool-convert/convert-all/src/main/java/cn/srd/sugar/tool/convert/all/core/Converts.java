package cn.srd.sugar.tool.convert.all.core;

import cn.srd.sugar.tool.convert.fastjson.core.FastJsonConverts;
import cn.srd.sugar.tool.convert.jackson.core.JacksonConverts;
import cn.srd.sugar.tool.convert.mapstruct.core.MapstructConverts;
import cn.srd.sugar.tool.convert.spring.core.SpringConverts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * All in one 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts extends cn.srd.sugar.tool.lang.core.convert.Converts {

    /**
     * 应用 Spring 转换器
     *
     * @return Spring 转换器
     */
    public static SpringConverts withSpring() {
        return SpringConverts.getInstance();
    }

    /**
     * 应用 FastJson 转换器
     *
     * @return FastJson 转换器
     */
    public static FastJsonConverts withFastJson() {
        return FastJsonConverts.getInstance();
    }

    /**
     * 应用 Jackson 转换器
     *
     * @return Jackson 转换器
     */
    public static JacksonConverts withJackson() {
        return JacksonConverts.getInstance();
    }

    /**
     * 应用 通用的 Mapstruct 转换器
     *
     * @return 通用的 Mapstruct 转换器
     */
    @Deprecated
    public static MapstructConverts withGenericMapstruct() {
        return MapstructConverts.getInstance();
    }

}

