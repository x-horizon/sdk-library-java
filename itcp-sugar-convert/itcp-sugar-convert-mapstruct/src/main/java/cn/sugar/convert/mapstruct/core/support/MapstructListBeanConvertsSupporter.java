package cn.sugar.convert.mapstruct.core.support;

import cn.sugar.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.sugar.tools.constant.StringPool;
import cn.sugar.tools.core.CollectionsUtil;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mapstruct 转换器支持：List 类型
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class MapstructListBeanConvertsSupporter implements MapstructConvertsSupporter {

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
