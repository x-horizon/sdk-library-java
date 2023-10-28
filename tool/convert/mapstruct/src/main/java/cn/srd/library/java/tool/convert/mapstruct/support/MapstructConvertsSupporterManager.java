// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import cn.srd.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Mapstruct 转换器支持类管理
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@Deprecated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapstructConvertsSupporterManager {

    /**
     * Mapstruct 转换器支持
     */
    private static final List<Function<Object, MapstructConvertsSupporter>> CONVERT_SUPPORTERS = new ArrayList<>();

    /**
     * 添加 Mapstruct 转换器支持
     *
     * @param supporterFunction Mapstruct 转换器支持
     */
    public static void addConvertSupporter(Function<Object, MapstructConvertsSupporter> supporterFunction) {
        CONVERT_SUPPORTERS.add(supporterFunction);
    }

    /**
     * 获取转换器支持
     *
     * @param source 匹配对象
     * @return Mapstruct 转换器支持
     */

    public static MapstructConvertsSupporter getSupporter(Object source) {
        for (Function<Object, MapstructConvertsSupporter> convertSupporter : CONVERT_SUPPORTERS) {
            MapstructConvertsSupporter mapstructConvertsSupporter = convertSupporter.apply(source);
            if (Nil.isNotNull(mapstructConvertsSupporter)) {
                return mapstructConvertsSupporter;
            }
        }
        return MapstructBeanConvertsSupporter.INSTANCE;
    }

}
