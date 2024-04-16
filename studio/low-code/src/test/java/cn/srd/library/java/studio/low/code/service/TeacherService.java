// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.service.GenericService;
import cn.srd.library.java.studio.low.code.dao.TeacherDao;
import cn.srd.library.java.studio.low.code.model.po.TeacherPO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
@RequiredArgsConstructor
public class TeacherService extends GenericService<TeacherPO, TeacherVO, TeacherDao> {

    private final TeacherDao teacherDao;

    public List<TeacherVO> listByCondition(TeacherListConditionVO teacherListConditionVO) {
        return teacherDao.openQuery()
                .where(TeacherPO::getName).likeIfNotBlank(teacherListConditionVO.getName())
                .listToVOs();
    }

    public PageResult<TeacherVO> pageByCondition(TeacherPageConditionVO teacherPageConditionVO) {
        return teacherDao.openQuery()
                .where(TeacherPO::getName).likeIfNotBlank(teacherPageConditionVO.getName())
                .page();
    }

}