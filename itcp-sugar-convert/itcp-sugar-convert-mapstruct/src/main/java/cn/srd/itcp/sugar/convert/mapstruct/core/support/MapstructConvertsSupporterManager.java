package cn.srd.itcp.sugar.convert.mapstruct.core.support;

import cn.srd.itcp.sugar.tools.core.Objects;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Mapstruct 转换器支持类管理
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class MapstructConvertsSupporterManager {

    /**
     * Mapstruct 转换器支持
     */
    private static final List<Function<Object, MapstructConvertsSupporter>> CONVERT_SUPPORTERS = new ArrayList<>();

    /**
     * 添加 Mapstruct 转换器支持
     *
     * @param supporterFunction
     */
    public static void addConvertSupporter(Function<Object, MapstructConvertsSupporter> supporterFunction) {
        CONVERT_SUPPORTERS.add(supporterFunction);
    }

    /**
     * 获取转换器支持
     *
     * @param source
     * @return
     */
    @NonNull
    public static MapstructConvertsSupporter getSupporter(@NonNull Object source) {
        for (Function<Object, MapstructConvertsSupporter> convertSupporter : CONVERT_SUPPORTERS) {
            MapstructConvertsSupporter mapstructConvertsSupporter = convertSupporter.apply(source);
            if (Objects.isNotNull(mapstructConvertsSupporter)) {
                return mapstructConvertsSupporter;
            }
        }
        return MapstructBeanConvertsSupporter.INSTANCE;
    }

}
