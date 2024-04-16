// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.service.GenericService;
import cn.srd.library.java.studio.low.code.dao.SchoolDao;
import cn.srd.library.java.studio.low.code.model.po.SchoolPO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学校信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
@RequiredArgsConstructor
public class SchoolService extends GenericService<SchoolPO, SchoolVO, SchoolDao> {

    private final SchoolDao schoolDao;

    public List<SchoolVO> listByCondition(SchoolListConditionVO schoolListConditionVO) {
        return schoolDao.openQuery()
                .where(SchoolPO::getName).likeIfNotBlank(schoolListConditionVO.getName())
                .listToVOs();
    }

    public PageResult<SchoolVO> pageByCondition(SchoolPageConditionVO schoolPageConditionVO) {
        return schoolDao.openQuery()
                .where(SchoolPO::getName).likeIfNotBlank(schoolPageConditionVO.getName())
                .page(schoolPageConditionVO.getPageNumber(), schoolPageConditionVO.getPageSize());
    }

}