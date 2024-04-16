// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.service.GenericService;
import cn.srd.library.java.studio.low.code.dao.StudentDao;
import cn.srd.library.java.studio.low.code.model.po.StudentPO;
import cn.srd.library.java.studio.low.code.model.vo.StudentListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
@RequiredArgsConstructor
public class StudentService extends GenericService<StudentPO, StudentVO, StudentDao> {

    private final StudentDao studentDao;

    public List<StudentVO> listByCondition(StudentListConditionVO studentListConditionVO) {
        return studentDao.openQuery()
                .where(StudentPO::getName).likeIfNotBlank(studentListConditionVO.getName())
                .listToVOs();
    }

    public PageResult<StudentVO> pageByCondition(StudentPageConditionVO studentPageConditionVO) {
        return studentDao.openQuery()
                .where(StudentPO::getName).likeIfNotBlank(studentPageConditionVO.getName())
                .page();
    }

}