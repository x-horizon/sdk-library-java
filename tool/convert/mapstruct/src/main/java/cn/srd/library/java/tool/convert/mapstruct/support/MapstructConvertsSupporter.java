// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import java.util.List;

/**
 * Mapstruct 转换器支持
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@Deprecated
public interface MapstructConvertsSupporter {

    /**
     * java.util.List
     */
    String LIST_NAME = List.class.getName();

    /**
     * 根据待转换类生成匹配转换方法的key
     *
     * @param source 待转换类
     * @return 匹配转换方法的key
     */
    String buildKeyToMatchMapstructMethod(Object source);

    /**
     * 根据待转换类和目标类生成匹配转换方法的key
     *
     * @param target  待转换类
     * @param sources 目标类
     * @return 匹配转换方法的key
     */
    String buildKeyToMatchMapstructMethod(Class<?> target, Object... sources);

    /**
     * 获取默认值
     *
     * @param defaultValue 提供的默认值
     * @param <T>          提供的默认值类型
     * @return 获取到的默认值
     */
    <T> Object getDefaultValue(T defaultValue);

}
