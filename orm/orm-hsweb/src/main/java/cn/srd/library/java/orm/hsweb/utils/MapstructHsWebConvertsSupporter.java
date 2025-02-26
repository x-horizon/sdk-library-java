package cn.srd.library.java.orm.hsweb.utils;

import cn.srd.library.java.orm.hsweb.page.PageResult;
import cn.srd.library.java.tool.constant.core.StringPool;
import cn.srd.library.java.tool.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.srd.library.java.tool.convert.mapstruct.support.MapstructConvertsSupporter;
import cn.srd.library.java.tool.lang.core.CollectionsUtil;
import cn.srd.library.java.tool.lang.core.validation.Nullable;
import lombok.NonNull;
import org.hswebframework.web.api.crud.entity.PagerResult;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Mapstruct 转换器支持：{@link PagerResult} 类型
 *
 * @author wjm
 * @since 2022/6/18 19:17
 */
@Deprecated
public class MapstructHsWebConvertsSupporter implements MapstructConvertsSupporter {

    /**
     * singleton pattern
     */
    public static final MapstructHsWebConvertsSupporter INSTANCE = new MapstructHsWebConvertsSupporter();

    /**
     * org.hswebframework.web.api.crud.entity.PagerResult
     */
    private static final String PAGER_RESULT_NAME = PagerResult.class.getName();

    /**
     * cn.commons.convert.all.hsweb.page.PageResult
     */
    private static final String PAGE_RESULT_NAME = PageResult.class.getName();

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Object source) {
        // PagerResult 类型从第一层缓存永远获取不到转换方法，因此此处直接返回 null
        return null;
    }

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Class<?> target, @NonNull Object... sources) {
        if (sources.length == 1) {
            return PAGER_RESULT_NAME + ((PagerResult<?>) sources[0]).getData().get(0).getClass().getName() + StringPool.SLASH + PAGE_RESULT_NAME + target.getName();
        }
        // PagerResult 类型只支持一对一转换
        throw new MapstructConvertMethodUnsupportedException();
    }

    @Override
    public <T> Object getDefaultValue(@Nullable T defaultValue) {
        return Objects.isNull(defaultValue)
                ?
                PageResult.builder()
                        .pageIndex(0L)
                        .pages(0L)
                        .pageSize(0L)
                        .total(0L)
                        .data(new ArrayList<>())
                        .build()
                :
                // TODO wjm 关于分页参数未优化，应该不会有这种需求
                PageResult.builder()
                        .pageIndex(1L)
                        .pages(1L)
                        .pageSize(1L)
                        .total(1L)
                        .data(CollectionsUtil.newArrayList(defaultValue))
                        .build();
    }

}
