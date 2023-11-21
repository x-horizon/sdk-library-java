// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.converter;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.mybatis.contract.base.model.PageResult;
import cn.srd.library.java.tool.convert.mapstruct.utils.IgnoreUnmappedMapperConfigurator;
import cn.srd.library.java.tool.convert.mapstruct.utils.MapstructMappingManager;
import com.mybatisflex.core.paginate.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Mapping(target = "total", source = "totalRow")
    @Mapping(target = "totalPages", source = "totalPage")
    @Mapping(target = "currentPage", source = "pageNumber")
    @Mapping(target = "pageSize", source = "pageSize")
    @Mapping(target = "data", source = "records")
    PageResult toPageResult(Page page);

}
