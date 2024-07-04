// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.studio.low.code.model.vo.StudentGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentVO;
import cn.srd.library.java.tool.convert.api.Converts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 学生信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-05-20 19:13
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class StudentServiceTest {

    @Autowired private StudentService studentService;

    @Test
    void testSave() {
        String studentData = "";
        StudentVO studentVO = Converts.onJackson().toBean(studentData, StudentVO.class);
        studentService.save(studentVO);
    }

    @Test
    void testUpdate() {
        String studentData = "";
        StudentVO studentVO = Converts.onJackson().toBean(studentData, StudentVO.class);
        studentService.updateById(studentVO);
    }

    @Test
    void testDeleteById() {
        studentService.deleteById(1L);
    }

    @Test
    void testDeleteByIds() {
        studentService.deleteByIds(1L, 2L);
    }

    @Test
    void testGetByCondition() {
        String studentGetConditionData = "";
        StudentGetConditionVO studentGetConditionVO = Converts.onJackson().toBean(studentGetConditionData, StudentGetConditionVO.class);
        studentService.getByCondition(studentGetConditionVO);
    }

    @Test
    void testListByCondition() {
        String studentListConditionData = "";
        StudentListConditionVO studentListConditionVO = Converts.onJackson().toBean(studentListConditionData, StudentListConditionVO.class);
        studentService.listByCondition(studentListConditionVO);
    }

    @Test
    void testPageByCondition() {
        String studentPageConditionData = "";
        StudentPageConditionVO studentPageConditionVO = Converts.onJackson().toBean(studentPageConditionData, StudentPageConditionVO.class);
        studentService.pageByCondition(studentPageConditionVO);
    }

}