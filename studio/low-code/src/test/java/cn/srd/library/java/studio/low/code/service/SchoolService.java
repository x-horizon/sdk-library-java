// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.service.GenericService;
import cn.srd.library.java.studio.low.code.dao.SchoolDao;
import cn.srd.library.java.studio.low.code.model.po.SchoolPO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学校信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
public class SchoolService extends GenericService<SchoolPO, SchoolVO, SchoolDao> {

    @Autowired private SchoolDao schoolDao;

    public SchoolVO getByCondition(SchoolGetConditionVO conditionVO) {
        return schoolDao.openQuery()
                .where(SchoolPO::getId).equalsTo(conditionVO.getId())
                .and(SchoolPO::getName).likeIfNotBlank(conditionVO.getName())
                .<SchoolVO>getToVO()
                .orElseThrow(DataNotFoundException::new);
    }

    public List<SchoolVO> listByCondition(SchoolListConditionVO conditionVO) {
        return schoolDao.openQuery()
                .where(SchoolPO::getId).inIfNotEmpty(conditionVO.getIds())
                .and(SchoolPO::getName).likeIfNotBlank(conditionVO.getName())
                .and(SchoolPO::getAddress).likeIfNotBlank(conditionVO.getAddress())
                .listToVOs();
    }

    public PageResult<SchoolVO> pageByCondition(SchoolPageConditionVO conditionVO) {
        return schoolDao.openQuery()
                .where(SchoolPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageNumber(), conditionVO.getPageSize());
    }

}