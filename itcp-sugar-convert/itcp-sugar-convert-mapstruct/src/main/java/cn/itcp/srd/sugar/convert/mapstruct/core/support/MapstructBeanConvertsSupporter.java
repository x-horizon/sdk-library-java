package cn.itcp.srd.sugar.convert.mapstruct.core.support;

import cn.srd.itcp.sugar.tools.constant.StringPool;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Mapstruct 转换器支持：普通 Bean 类型
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class MapstructBeanConvertsSupporter implements MapstructConvertsSupporter {

    public static final MapstructBeanConvertsSupporter INSTANCE = new MapstructBeanConvertsSupporter();

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Object source) {
        return source.getClass().getName();
    }

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Class<?> target, @NonNull Object... sources) {
        if (sources.length == 1) {
            return sources[0].getClass().getName() + StringPool.SLASH + target.getName();
        }

        // 这里使用 + 拼接字符串而不是使用 StringBuilder，详情参考 StringBenchmarkTest
        String key = "";
        for (Object source : sources) {
            key = key + source.getClass().getName() + StringPool.SLASH;
        }
        return key + target.getName();
    }

    @Override
    public <T> Object getDefaultValue(@Nullable T defaultValue) {
        return defaultValue;
    }

}
