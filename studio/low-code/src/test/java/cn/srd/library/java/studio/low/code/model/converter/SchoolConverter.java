// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.converter;

import cn.srd.library.java.studio.low.code.model.po.SchoolPO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolVO;
import cn.srd.library.java.tool.convert.mapstruct.utils.IgnoreUnmappedMapperConfigurator;
import cn.srd.library.java.tool.convert.mapstruct.utils.MapstructMappingManager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 学校信息 converter
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Mapper(uses = {MapstructMappingManager.class}, config = IgnoreUnmappedMapperConfigurator.class)
public interface SchoolConverter {

    SchoolConverter INSTANCE = Mappers.getMapper(SchoolConverter.class);

    SchoolPO toPO(SchoolVO schoolVO);

    SchoolVO toVO(SchoolPO schoolPO);

}