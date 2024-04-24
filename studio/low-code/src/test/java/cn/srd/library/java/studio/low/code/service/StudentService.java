// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.postgresql.service.GenericService;
import cn.srd.library.java.studio.low.code.dao.SchoolDao;
import cn.srd.library.java.studio.low.code.dao.StudentDao;
import cn.srd.library.java.studio.low.code.dao.TeacherDao;
import cn.srd.library.java.studio.low.code.model.bo.StudentCourseBO;
import cn.srd.library.java.studio.low.code.model.bo.StudentHobbyBO;
import cn.srd.library.java.studio.low.code.model.po.SchoolPO;
import cn.srd.library.java.studio.low.code.model.po.StudentPO;
import cn.srd.library.java.studio.low.code.model.po.TeacherPO;
import cn.srd.library.java.studio.low.code.model.vo.StudentGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
public class StudentService extends GenericService<StudentPO, StudentVO, StudentDao> {

    @Autowired private StudentDao studentDao;

    @Autowired private SchoolDao schoolDao;

    @Autowired private TeacherDao teacherDao;

    public StudentVO getByCondition(StudentGetConditionVO conditionVO) {
        return studentDao.openQuery()
                .where(StudentPO::getId).equalsTo(conditionVO.getId())
                .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .<StudentVO>getToVO()
                .orElseThrow(DataNotFoundException::new);
    }

    public List<StudentVO> listByCondition(StudentListConditionVO conditionVO) {
        // StudentHobbyBO studentHobbyBO = new StudentHobbyBO();
        // StudentCourseBO studentCourseBO = new StudentCourseBO();

        String a = studentDao.openJsonbQuery()
                .functionArrayUnnest(StudentPO::getCourseBOs)
                .innerJoin(StudentPO::getCourseBOs)
                .next()
                .functionObjectExtract(StudentPO::getCourseBOs, StudentHobbyBO::getAchievementTypes, StudentCourseBO::getCredit)
                .functionArrayUnnest()
                .innerJoin(StudentCourseBO::getCredit)
                .next()
                .functionObjectExtract(StudentCourseBO::getCredit, StudentHobbyBO::getLevelType, StudentCourseBO::getCredit, StudentCourseBO::getName, StudentHobbyBO::getLevelType)
                .innerJoin(StudentHobbyBO::getLevelType)
                .switchToNormalQuery()
                .where(StudentPO::getId).inIfNotEmpty(conditionVO.getIds())
                .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .andJsonQuery(StudentHobbyBO::getLevelType, StudentCourseBO::getName).likeIfNotBlank(conditionVO.getName())
                .toSQL();

        List<SchoolPO> schoolPOs = schoolDao.listLikeByField(SchoolPO::getName, conditionVO.getSchoolName());
        List<TeacherPO> teacherPOs = teacherDao.listLikeByField(TeacherPO::getName, conditionVO.getTeacherName());

        return studentDao.openQuery()
                .where(StudentPO::getId).inIfNotEmpty(conditionVO.getIds())
                .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .listToVOs();
    }

    public PageResult<StudentVO> pageByCondition(StudentPageConditionVO conditionVO) {
        return studentDao.openQuery()
                .where(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageNumber(), conditionVO.getPageSize());
    }

}