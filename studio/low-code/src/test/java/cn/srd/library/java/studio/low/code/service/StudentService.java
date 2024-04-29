// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.postgresql.service.GenericService;
import cn.srd.library.java.studio.low.code.model.bo.StudentBO;
import cn.srd.library.java.studio.low.code.model.bo.StudentHobbyBO;
import cn.srd.library.java.studio.low.code.model.bo.StudentHobbyBookBO;
import cn.srd.library.java.studio.low.code.model.po.SchoolPO;
import cn.srd.library.java.studio.low.code.model.po.StudentPO;
import cn.srd.library.java.studio.low.code.model.po.TeacherPO;
import cn.srd.library.java.studio.low.code.model.vo.StudentGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentVO;
import cn.srd.library.java.studio.low.code.repository.SchoolRepository;
import cn.srd.library.java.studio.low.code.repository.StudentRepository;
import cn.srd.library.java.studio.low.code.repository.TeacherRepository;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.RawQueryCondition;
import com.mybatisflex.core.query.RawQueryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.srd.library.java.orm.mybatis.flex.postgresql.chain2.JsonbQueryFunctionChainer.jsonbArrayElements;
import static com.mybatisflex.core.query.QueryMethods.exists;
import static com.mybatisflex.core.query.QueryMethods.selectOne;

/**
 * 学生信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
public class StudentService extends GenericService<StudentPO, StudentVO, StudentRepository> {

    @Autowired private StudentRepository studentRepository;

    @Autowired private SchoolRepository schoolRepository;

    @Autowired private TeacherRepository teacherRepository;

    public StudentVO getByCondition(StudentGetConditionVO conditionVO) {
        return studentRepository.openNormalQuery()
                .where(StudentPO::getId).equalsTo(conditionVO.getId())
                .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .<StudentVO>getToVO()
                .orElseThrow(DataNotFoundException::new);
    }

    public List<StudentVO> listByCondition(StudentListConditionVO conditionVO) {
        List<SchoolPO> schoolPOs = schoolRepository.listLikeByField(SchoolPO::getName, conditionVO.getSchoolName());
        List<TeacherPO> teacherPOs = teacherRepository.listLikeByField(TeacherPO::getName, conditionVO.getTeacherName());

        String c = QueryWrapper.create()
                .select()
                .from("student")
                .where("1=1")
                .and(exists(selectOne()
                        .from(new RawQueryTable("JSONB_ARRAY_ELEMENTS(\"student\".teacher_ids)"))
                        .as("studentPO_teacher_ids_540330549004101")
                        .where(new RawQueryCondition("\"studentPO_teacher_ids_540330549004101\"::INTEGER"))
                        .in(StudentBO::getName, 1, 2, 3)
                )).toSQL();

        // String d = DbChain.table("JSONB_ARRAY_ELEMENTS(\"student\".teacher_ids)")
        //         .select("1")
        //         .where("\"studentPO_teacher_ids_540330549004101\"::INTEGER IN (1, 2, 3))")
        //         .toSQL();

        String b = studentRepository.openNormalQuery()
                .where(StudentPO::getId).inIfNotEmpty(conditionVO.getIds())
                .switchToJsonbQuery()
                .and(StudentPO::getHobbyBO, StudentHobbyBO::getBookBO, StudentHobbyBookBO::getName).castToVarchar().likeIfNotBlank("学生")
                .andExist(jsonbArrayElements(StudentPO::getTeacherIds)
                        .where(StudentPO::getTeacherIds)
                        .castToBigint()
                        .in(1, 2)
                )
                .switchToNormalQuery()
                .toSQL();

        System.out.println();

        // List<StudentVO> a = studentRepository
        //         .openJsonbQuery()
        //         // 展开学生信息的 teacherIds
        //         .functionArrayUnnest(StudentPO::getTeacherIds)
        //         .innerJoin()
        //         // 提取学生信息的 hobbyBO.specificInterestNames
        //         .functionObjectExtract(StudentPO::getHobbyBO, StudentHobbyBO::getSpecificInterestNames)
        //         // 展开学生信息的 hobbyBO -> specificInterestNames
        //         .functionArrayUnnest()
        //         .innerJoin()
        //         // 提取学生信息的 hobbyBO.achievementTypes
        //         .functionObjectExtract(StudentPO::getHobbyBO, StudentHobbyBO::getAchievementTypes)
        //         // 展开学生信息的 hobbyBO.achievementTypes
        //         .functionArrayUnnest()
        //         .innerJoin()
        //         // 提取学生信息的 hobbyBO.bookBO
        //         .functionObjectExtract(StudentPO::getHobbyBO, StudentHobbyBO::getBookBO)
        //         .innerJoin()
        //         // 提取学生信息的 hobbyBO.toolBOs
        //         .functionObjectExtract(StudentPO::getHobbyBO, StudentHobbyBO::getToolBOs)
        //         // 展开学生信息-的 hobbyBO.toolBOs
        //         .functionArrayUnnest()
        //         .innerJoin()
        //         // 展开学生信息的 courseBOs
        //         .functionArrayUnnest(StudentPO::getCourseBOs)
        //         .innerJoin()
        //         // 提取学生信息的 courseBO.bookBO
        //         .functionObjectExtract(StudentPO::getCourseBOs, StudentCourseBO::getBookBO)
        //         .innerJoin()
        //         // 提取学生信息的 courseBO.toolBOs
        //         .functionObjectExtract(StudentPO::getCourseBOs, StudentCourseBO::getToolBOs)
        //         // 展开学生信息的 courseBO.toolBOs
        //         .functionArrayUnnest()
        //         .innerJoin()
        //         .switchToNormalQuery()
        //         .where(StudentPO::getId).inIfNotEmpty(conditionVO.getIds())
        //         .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
        //         .and(StudentPO::getSchoolId).in(Converts.toList(schoolPOs, SchoolPO::getId))
        //         .switchToJsonbQuery()
        //         .and(StudentPO::getTeacherIds).castToBigint().in(Converts.toList(teacherPOs, TeacherPO::getId))
        //         .and(StudentPO::getCourseBOs, StudentCourseBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getCourseName())
        //         .and(StudentPO::getCourseBOs, StudentCourseBO::getCredit).castToInteger().greaterThan(conditionVO.getCourseCredit())
        //         .and(StudentCourseBO::getBookBO, StudentCourseBookBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getCourseBookName())
        //         .and(StudentCourseBO::getToolBOs, StudentCourseToolBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getCourseToolName())
        //         .and(StudentPO::getHobbyBO, StudentHobbyBO::getPrimaryInterestName).castToVarchar().likeIfNotBlank(conditionVO.getHobbyPrimaryInterestName())
        //         .and(StudentPO::getHobbyBO, StudentHobbyBO::getSpecificInterestNames).castToVarchar().likeIfNotBlank(conditionVO.getHobbySpecificInterestNames())
        //         .and(StudentPO::getHobbyBO, StudentHobbyBO::getLevelType).castToSmallint().equalsIfNotNull(conditionVO.getHobbyParticipationLevelType().getCode())
        //         .and(StudentPO::getHobbyBO, StudentHobbyBO::getAchievementTypes).castToSmallint().inIfNotEmpty(conditionVO.getHobbyAchievementType().getCode())
        //         .and(StudentPO::getHobbyBO, StudentHobbyBO::getAchievementTypes).castToSmallint().inIfNotEmpty(Converts.toList(conditionVO.getHobbyAchievementTypes(), StudentHobbyAchievementType::getCode))
        //         .and(StudentHobbyBO::getBookBO, StudentHobbyBookBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getHobbyBookName())
        //         .and(StudentHobbyBO::getToolBOs, StudentHobbyToolBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getHobbyToolName())
        //         .switchToNormalQuery()
        //         .listToVOs();

        return studentRepository.openNormalQuery()
                .where(StudentPO::getId).inIfNotEmpty(conditionVO.getIds())
                .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .listToVOs();
    }

    public PageResult<StudentVO> pageByCondition(StudentPageConditionVO conditionVO) {
        return studentRepository.openNormalQuery()
                .where(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageNumber(), conditionVO.getPageSize());
    }

}