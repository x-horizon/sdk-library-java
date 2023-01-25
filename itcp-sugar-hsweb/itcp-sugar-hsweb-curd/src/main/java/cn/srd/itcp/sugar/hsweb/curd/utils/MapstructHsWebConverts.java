package cn.srd.itcp.sugar.hsweb.curd.utils;

import cn.srd.itcp.sugar.convert.mapstruct.core.MapstructConverts;
import cn.srd.itcp.sugar.convert.mapstruct.support.MapstructConvertsSupporterManager;
import cn.srd.itcp.sugar.hsweb.curd.page.PageResult;
import cn.srd.itcp.sugar.tools.core.Objects;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 在 All in one 转换器的基础上增加对 {@link PagerResult} =&gt; {@link PageResult} 的转换支持
 *
 * @author wjm
 * @since 2022/6/18 19:17
 */
@Deprecated
public class MapstructHsWebConverts {

    /**
     * protected block constructor
     */
    protected MapstructHsWebConverts() {
        super();
    }

    /**
     * slacker signal ton pattern
     */
    private static final class SingleTonHolder {
        private static final MapstructHsWebConverts INSTANCE = new MapstructHsWebConverts();
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static MapstructHsWebConverts getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    static {
        // 添加 Mapstruct HsWeb 转换器支持
        MapstructConvertsSupporterManager.addConvertSupporter(object -> {
            if (PagerResult.class.isAssignableFrom(object.getClass())) {
                return MapstructHsWebConvertsSupporter.INSTANCE;
            }
            return null;
        });
    }

    /**
     * 是否静默转换，即报错不抛出异常，只打印日志，默认不静默转换
     */
    private static final boolean DEFAULT_CONVERT_QUIETLY = Converts.DEFAULT_CONVERT_QUIETLY;

    /**
     * PagerResult Bean 一对一转换
     *
     * @param source 待转换的 PageResult
     * @param target 目标 PageResult
     * @param <T1>   待转换的 PagerResult 类型
     * @param <T2>   目标 PageResult 类型
     * @return 目标 PageResult
     */
    @NonNull
    public <T1, T2> PageResult<T2> toPageBean(@Nullable PagerResult<T1> source, @Nullable Class<T2> target) {
        return toPageBean(source, target, null, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * PagerResult Bean 一对一转换
     *
     * @param source       待转换的 PageResult
     * @param target       目标 PageResult
     * @param defaultValue 所有异常情况时的默认目标值
     * @param <T1>         待转换的 PagerResult 类型
     * @param <T2>         目标 PageResult 类型
     * @return 目标 PageResult
     */
    @NonNull
    public <T1, T2> PageResult<T2> toPageBean(@Nullable PagerResult<T1> source, @Nullable Class<T2> target, @Nullable T2 defaultValue) {
        return toPageBean(source, target, defaultValue, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * PagerResult Bean 一对一转换
     *
     * @param source  待转换的 PageResult
     * @param target  目标 PageResult
     * @param quietly 是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>    待转换的 PagerResult 类型
     * @param <T2>    目标 PageResult 类型
     * @return 目标 PageResult
     */
    @NonNull
    public <T1, T2> PageResult<T2> toPageBean(@Nullable PagerResult<T1> source, @Nullable Class<T2> target, @Nullable Boolean quietly) {
        return toPageBean(source, target, null, quietly);
    }

    /**
     * PagerResult Bean 一对一转换
     *
     * @param source       待转换的 PageResult
     * @param target       目标 PageResult
     * @param defaultValue 所有异常情况时的默认目标值
     * @param quietly      是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>         待转换的 PagerResult 类型
     * @param <T2>         目标 PageResult 类型
     * @return 目标 PageResult
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public <T1, T2> PageResult<T2> toPageBean(@Nullable PagerResult<T1> source, @Nullable Class<T2> target, @Nullable T2 defaultValue, @Nullable Boolean quietly) {
        if (Objects.isNull(source, target) || Objects.isEmpty(source.getData())) {
            return (PageResult<T2>) MapstructHsWebConvertsSupporter.INSTANCE.getDefaultValue(defaultValue);
        }
        return (PageResult<T2>) MapstructConverts.getInstance().convert(target, defaultValue, quietly, source);
    }

}
