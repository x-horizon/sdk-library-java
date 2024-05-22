// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.all.mapstruct.test;

import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.GradeDO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.StudentDO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.GradeVO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.StudentVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MapstructConvertsTest {

    private static final GradeDO GRADE_DO = GradeDO.newDO();

    private static final GradeVO GRADE_VO = GradeVO.newVO();

    private static final List<GradeDO> GRADE_DOS = GradeDO.newDOs();

    private static final List<GradeVO> GRADE_VOS = GradeVO.newVOs();

    private static final StudentDO STUDENT_DO = StudentDO.newDO();

    private static final StudentVO STUDENT_VO = StudentVO.newVO();

    private static final List<StudentDO> STUDENT_DOS = StudentDO.newDOs();

    private static final List<StudentVO> STUDENT_VOS = StudentVO.newVOs();

    @Test
    void testMapstructConverts() {
        GradeVO gradeVO = Converts.withMapstruct().toBean(GRADE_DO, GradeVO.class);
        GradeDO gradeDO = Converts.withMapstruct().toBean(GRADE_VO, GradeDO.class);
        List<GradeVO> gradeVOs = Converts.withMapstruct().toBeans(GRADE_DOS, GradeVO.class);
        List<GradeDO> gradeDOs = Converts.withMapstruct().toBeans(GRADE_VOS, GradeDO.class);

        StudentVO studentVO = Converts.withMapstruct().toBean(STUDENT_DO, StudentVO.class);
        StudentDO studentDO = Converts.withMapstruct().toBean(STUDENT_VO, StudentDO.class);
        List<StudentVO> studentVOs = Converts.withMapstruct().toBeans(STUDENT_DOS, StudentVO.class);
        List<StudentDO> studentDOs = Converts.withMapstruct().toBeans(STUDENT_VOS, StudentDO.class);
    }

}