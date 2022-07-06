package cn.srd.itcp.sugar.convert.all.core;

import cn.srd.itcp.sugar.convert.fastjson.core.FastJsonConverts;
import cn.srd.itcp.sugar.convert.jackson.core.JacksonConverts;
import cn.srd.itcp.sugar.convert.mapstruct.core.MapstructConverts;
import cn.srd.itcp.sugar.convert.spring.core.SpringConverts;

/**
 * All in one 转换器
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class Converts extends cn.srd.itcp.sugar.tools.core.convert.Converts {

    protected Converts() {
    }

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
    public static MapstructConverts withGenericMapstruct() {
        return MapstructConverts.getInstance();
    }

}

