// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.postgresql.service.GenericService;
import cn.srd.library.java.studio.low.code.dao.TeacherDao;
import cn.srd.library.java.studio.low.code.model.po.TeacherPO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
public class TeacherService extends GenericService<TeacherPO, TeacherVO, TeacherDao> {

    @Autowired private TeacherDao teacherDao;

    public TeacherVO getByCondition(TeacherGetConditionVO conditionVO) {
        return teacherDao.openQuery()
                .where(TeacherPO::getId).equalsTo(conditionVO.getId())
                .and(TeacherPO::getName).likeIfNotBlank(conditionVO.getName())
                .<TeacherVO>getToVO()
                .orElseThrow(DataNotFoundException::new);
    }

    public List<TeacherVO> listByCondition(TeacherListConditionVO conditionVO) {
        return teacherDao.openQuery()
                .where(TeacherPO::getName).likeIfNotBlank(conditionVO.getName())
                .listToVOs();
    }

    public PageResult<TeacherVO> pageByCondition(TeacherPageConditionVO conditionVO) {
        return teacherDao.openQuery()
                .where(TeacherPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageNumber(), conditionVO.getPageSize());
    }

}