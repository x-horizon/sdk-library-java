// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.all;

import cn.srd.library.java.tool.convert.jackson.JacksonConverts;
import cn.srd.library.java.tool.convert.mapstruct.MapstructConverts;
import cn.srd.library.java.tool.convert.spring.SpringConverts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * All in one 转换器
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts extends cn.srd.library.java.tool.lang.convert.Converts {

    /**
     * 应用 Spring 转换器
     *
     * @return Spring 转换器
     */
    public static SpringConverts withSpring() {
        return SpringConverts.getInstance();
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