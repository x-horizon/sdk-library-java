package org.horizon.sdk.library.java.orm.mybatis.flex.base.converter;

import com.mybatisflex.core.paginate.Page;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.orm.contract.model.page.PageResult;
import org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support.IgnoreUnmappedMapperConfigurator;
import org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support.MapstructMappingManager;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

/**
 * the page converter
 *
 * @author wjm
 * @since 2023-11-22 00:31
 */
@Mapper(uses = MapstructMappingManager.class, config = IgnoreUnmappedMapperConfigurator.class)
public interface PageConverter {

    PageConverter INSTANCE = Mappers.getMapper(PageConverter.class);

    default <P extends PO> PageResult<P> toPageResult(Page<P> page) {
        if (Nil.isNull(page)) {
            return null;
        }
        return PageResult.<P>builder()
                .totalNumber(page.getTotalRow())
                .totalPageNumber(page.getTotalPage())
                .currentPageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .data(page.getRecords())
                .build();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default <P extends PO, V extends VO> PageResult<V> toPageResultVO(Page<P> page) {
        if (Nil.isNull(page)) {
            return null;
        }
        return PageResult.<V>builder()
                .totalNumber(page.getTotalRow())
                .totalPageNumber(page.getTotalPage())
                .currentPageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .data(page.getRecords().stream().map(po -> (V) po.toVO()).collect(Collectors.toList()))
                .build();
    }

}