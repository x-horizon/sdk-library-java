// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.converter;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.tool.convert.mapstruct.utils.IgnoreUnmappedMapperConfigurator;
import cn.srd.library.java.tool.convert.mapstruct.utils.MapstructMappingManager;
import cn.srd.library.java.tool.lang.object.Nil;
import com.mybatisflex.core.paginate.Page;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * the page converter
 *
 * @author wjm
 * @since 2023-11-22 00:31
 */
@Mapper(uses = MapstructMappingManager.class, config = IgnoreUnmappedMapperConfigurator.class)
public interface PageConverter {

    PageConverter INSTANCE = Mappers.getMapper(PageConverter.class);

    default <T extends PO> PageResult<T> toPageResult(Page<T> page) {
        if (Nil.isNull(page)) {
            return null;
        }
        return PageResult.<T>builder()
                .totalNumber(page.getTotalRow())
                .totalPageNumber(page.getTotalPage())
                .currentPageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .data(page.getRecords())
                .build();
    }

}