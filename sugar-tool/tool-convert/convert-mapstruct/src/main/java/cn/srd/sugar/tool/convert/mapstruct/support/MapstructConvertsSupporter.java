package cn.srd.sugar.tool.convert.mapstruct.support;

import cn.srd.sugar.tool.lang.core.validation.Nullable;
import lombok.NonNull;

import java.util.List;

/**
 * Mapstruct 转换器支持
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
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
    String buildKeyToMatchMapstructMethod(@NonNull Object source);

    /**
     * 根据待转换类和目标类生成匹配转换方法的key
     *
     * @param target  待转换类
     * @param sources 目标类
     * @return 匹配转换方法的key
     */
    String buildKeyToMatchMapstructMethod(@NonNull Class<?> target, @NonNull Object... sources);

    /**
     * 获取默认值
     *
     * @param defaultValue 提供的默认值
     * @param <T>          提供的默认值类型
     * @return 获取到的默认值
     */
    <T> Object getDefaultValue(@Nullable T defaultValue);

}
