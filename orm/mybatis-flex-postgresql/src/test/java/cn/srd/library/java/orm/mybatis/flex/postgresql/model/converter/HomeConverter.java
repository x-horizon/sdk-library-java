// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.model.converter;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.HomePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo.HomeVO;
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
public interface HomeConverter {

    HomeConverter INSTANCE = Mappers.getMapper(HomeConverter.class);

    HomePO toPO(HomeVO homeVO);

    HomeVO toVO(HomePO homePO);

    @IterableMapping(elementTargetType = HomePO.class)
    List<HomePO> toPOs(List<HomeVO> homeVOs);

    @IterableMapping(elementTargetType = HomeVO.class)
    List<HomeVO> toVOs(List<HomePO> homePOs);

}