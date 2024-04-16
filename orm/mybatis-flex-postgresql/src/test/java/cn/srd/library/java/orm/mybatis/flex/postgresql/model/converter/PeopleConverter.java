// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.model.converter;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.PeoplePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo.PeopleVO;
import cn.srd.library.java.tool.convert.mapstruct.utils.IgnoreUnmappedMapperConfigurator;
import cn.srd.library.java.tool.convert.mapstruct.utils.MapstructMappingManager;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjm
 * @since 2024-04-15 18:44
 */
@Mapper(uses = {MapstructMappingManager.class}, config = IgnoreUnmappedMapperConfigurator.class)
public interface PeopleConverter {

    PeopleConverter INSTANCE = Mappers.getMapper(PeopleConverter.class);

    PeoplePO toPO(PeopleVO peopleVO);

    PeopleVO toVO(PeoplePO peoplePO);

    @IterableMapping(elementTargetType = PeoplePO.class)
    List<PeoplePO> toPOs(List<PeopleVO> peopleVOs);

    @IterableMapping(elementTargetType = PeopleVO.class)
    List<PeopleVO> toVOs(List<PeoplePO> peoplePOs);

}