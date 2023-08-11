package cn.srd.sugar.tool.convert.mapstruct.support;

import cn.srd.sugar.tool.constant.core.StringPool;
import cn.srd.sugar.tool.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.srd.sugar.tool.lang.core.CollectionsUtil;
import cn.srd.sugar.tool.lang.core.validation.Nullable;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mapstruct 转换器支持：List 类型
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructListBeanConvertsSupporter implements MapstructConvertsSupporter {

    /**
     * singleton pattern
     */
    public static final MapstructListBeanConvertsSupporter INSTANCE = new MapstructListBeanConvertsSupporter();

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Object source) {
        // List 类型从第一层缓存永远获取不到转换方法，因此此处直接返回 null
        return null;
    }

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Class<?> target, @NonNull Object... sources) {
        if (sources.length == 1) {
            return LIST_NAME + ((List<?>) sources[0]).get(0).getClass().getName() + StringPool.SLASH + LIST_NAME + target.getName();
        }
        // List 类型只支持一对一转换
        throw new MapstructConvertMethodUnsupportedException();
    }

    @Override
    public <T> Object getDefaultValue(@Nullable T defaultValue) {
        return Objects.isNull(defaultValue) ? new ArrayList<T>() : CollectionsUtil.newArrayList(defaultValue);
    }

}
