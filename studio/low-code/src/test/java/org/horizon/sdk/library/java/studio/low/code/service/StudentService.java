package org.horizon.sdk.library.java.studio.low.code.service;

import org.horizon.sdk.library.java.contract.model.throwable.DataNotFoundException;
import org.horizon.sdk.library.java.orm.contract.model.page.PageResult;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain.JsonbQueryFunctionChainer;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.service.GenericService;
import org.horizon.sdk.library.java.studio.low.code.model.bo.*;
import org.horizon.sdk.library.java.studio.low.code.model.enums.StudentHobbyAchievementType;
import org.horizon.sdk.library.java.studio.low.code.model.po.SchoolPO;
import org.horizon.sdk.library.java.studio.low.code.model.po.StudentPO;
import org.horizon.sdk.library.java.studio.low.code.model.po.TeacherPO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentGetConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentListConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentVO;
import org.horizon.sdk.library.java.studio.low.code.repository.SchoolRepository;
import org.horizon.sdk.library.java.studio.low.code.repository.StudentRepository;
import org.horizon.sdk.library.java.studio.low.code.repository.TeacherRepository;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain.JsonbQueryFunctionChainer.jsonbArrayElements;
import static org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain.JsonbQueryFunctionChainer.jsonbExtractPath;

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
        return studentRepository.openNormalQuery()
                .where(StudentPO::getId).in(conditionVO.getIds())
                .and(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .and(StudentPO::getSchoolId).in(Converts.toList(schoolPOs, SchoolPO::getId))
                .switchToJsonbQuery()
                .and(StudentPO::getHobbyBO, StudentHobbyBO::getLevelType).castToSmallint().equalsTo(conditionVO.getHobbyParticipationLevelType().getCode())
                .and(StudentPO::getHobbyBO, StudentHobbyBO::getBookBO, StudentHobbyBookBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getHobbyBookName())
                .andExist(JsonbQueryFunctionChainer.jsonbArrayElements(StudentPO::getTeacherIds)
                        .addTableSuffix(StudentPO.class)
                        .where(StudentPO::getTeacherIds)
                        .castToBigint()
                        .in(Converts.toList(teacherPOs, TeacherPO::getId))
                )
                .andExist(jsonbArrayElements(jsonbExtractPath(StudentPO::getHobbyBO, StudentHobbyBO::getAchievementTypes))
                        .addTableSuffix(StudentPO.class)
                        .where(StudentHobbyBO::getAchievementTypes)
                        .castToInteger()
                        .inIfNotEmpty(Converts.toList(conditionVO.getHobbyAchievementTypes(), StudentHobbyAchievementType::getCode))
                )
                .andExist(jsonbArrayElements(jsonbExtractPath(StudentPO::getHobbyBO, StudentHobbyBO::getToolBOs))
                        .addTableSuffix(StudentPO.class)
                        .where(StudentHobbyBO::getToolBOs, StudentHobbyToolBO::getName)
                        .castToVarchar()
                        .likeIfNotBlank(conditionVO.getHobbyToolName())
                )
                .andExist(JsonbQueryFunctionChainer.jsonbArrayElements(StudentPO::getCourseBOs)
                                .addTableSuffix(StudentPO.class)
                                .where(StudentPO::getCourseBOs, StudentCourseBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getCourseName())
                                .and(StudentPO::getCourseBOs, StudentCourseBO::getBookBO, StudentCourseBookBO::getName).castToVarchar().likeIfNotBlank(conditionVO.getCourseBookName())
                        // .andExist(jsonbArrayElements(jsonbExtractPath(StudentPO::getCourseBOs, StudentCourseBO::getToolBOs)
                        //         .where(StudentHobbyBO::getToolBOs, StudentHobbyToolBO::getName).castToVarchar().likeIfNotBlank("学生")
                        // ))
                )
                .switchToNormalQuery()
                .ignoreLogicDelete()
                .listToVOs();
    }

    public PageResult<StudentVO> pageByCondition(StudentPageConditionVO conditionVO) {
        return studentRepository.openNormalQuery()
                .where(StudentPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageIndex(), conditionVO.getPageSize());
    }

}