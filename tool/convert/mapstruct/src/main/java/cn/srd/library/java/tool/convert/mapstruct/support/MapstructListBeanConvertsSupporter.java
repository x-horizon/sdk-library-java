// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapstruct 转换器支持：List 类型
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@Deprecated
public class MapstructListBeanConvertsSupporter implements MapstructConvertsSupporter {

    /**
     * singleton pattern
     */
    public static final MapstructListBeanConvertsSupporter INSTANCE = new MapstructListBeanConvertsSupporter();

    @Override
    public String buildKeyToMatchMapstructMethod(Object source) {
        // List 类型从第一层缓存永远获取不到转换方法，因此此处直接返回 null
        return null;
    }

    @Override
    public String buildKeyToMatchMapstructMethod(Class<?> target, Object... sources) {
        if (sources.length == 1) {
            return LIST_NAME + ((List<?>) sources[0]).get(0).getClass().getName() + SymbolConstant.SLASH + LIST_NAME + target.getName();
        }
        // List 类型只支持一对一转换
        throw new MapstructConvertMethodUnsupportedException();
    }

    @Override
    public <T> Object getDefaultValue(T defaultValue) {
        return Nil.isNull(defaultValue) ? new ArrayList<T>() : Collections.ofArrayList(defaultValue);
    }

}
