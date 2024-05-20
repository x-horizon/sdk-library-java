// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.service;

import cn.srd.library.java.studio.low.code.model.vo.TeacherGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherVO;
import cn.srd.library.java.tool.convert.all.Converts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 教师信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-05-20 19:14
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {

    @Autowired private TeacherService teacherService;

    @Test
    void testSave() {
        String teacherData = "";
        TeacherVO teacherVO = Converts.withJackson().toBean(teacherData, TeacherVO.class);
        teacherService.save(teacherVO);
    }

    @Test
    void testUpdate() {
        String teacherData = "";
        TeacherVO teacherVO = Converts.withJackson().toBean(teacherData, TeacherVO.class);
        teacherService.updateById(teacherVO);
    }

    @Test
    void testDeleteById() {
        teacherService.deleteById(1L);
    }

    @Test
    void testDeleteByIds() {
        teacherService.deleteByIds(1L, 2L);
    }

    @Test
    void testGetByCondition() {
        String teacherGetConditionData = "";
        TeacherGetConditionVO teacherGetConditionVO = Converts.withJackson().toBean(teacherGetConditionData, TeacherGetConditionVO.class);
        teacherService.getByCondition(teacherGetConditionVO);
    }

    @Test
    void testListByCondition() {
        String teacherListConditionData = "";
        TeacherListConditionVO teacherListConditionVO = Converts.withJackson().toBean(teacherListConditionData, TeacherListConditionVO.class);
        teacherService.listByCondition(teacherListConditionVO);
    }

    @Test
    void testPageByCondition() {
        String teacherPageConditionData = "";
        TeacherPageConditionVO teacherPageConditionVO = Converts.withJackson().toBean(teacherPageConditionData, TeacherPageConditionVO.class);
        teacherService.pageByCondition(teacherPageConditionVO);
    }

}