// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import cn.srd.library.java.contract.constant.text.SymbolConstant;

/**
 * Mapstruct 转换器支持：普通 Bean 类型
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@Deprecated
public class MapstructBeanConvertsSupporter implements MapstructConvertsSupporter {

    /**
     * singleton pattern
     */
    public static final MapstructBeanConvertsSupporter INSTANCE = new MapstructBeanConvertsSupporter();

    @Override
    public String buildKeyToMatchMapstructMethod(Object source) {
        return source.getClass().getName();
    }

    @Override
    public String buildKeyToMatchMapstructMethod(Class<?> target, Object... sources) {
        if (sources.length == 1) {
            return sources[0].getClass().getName() + SymbolConstant.SLASH + target.getName();
        }

        // 这里使用 + 拼接字符串而不是使用 StringBuilder，详情参考 StringBenchmarkTest
        String key = "";
        for (Object source : sources) {
            key = key + source.getClass().getName() + SymbolConstant.SLASH;
        }
        return key + target.getName();
    }

    @Override
    public <T> Object getDefaultValue(T defaultValue) {
        return defaultValue;
    }

}