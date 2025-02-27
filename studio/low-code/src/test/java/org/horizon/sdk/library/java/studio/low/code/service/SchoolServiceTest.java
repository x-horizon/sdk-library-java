package org.horizon.sdk.library.java.studio.low.code.service;

import org.horizon.sdk.library.java.studio.low.code.model.vo.SchoolGetConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.SchoolListConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.SchoolPageConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.SchoolVO;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 学校信息 service test
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SchoolServiceTest {

    @Autowired private SchoolService schoolService;

    @Test
    void testSave() {
        String schoolData = "";
        SchoolVO schoolVO = Converts.onJackson().toBean(schoolData, SchoolVO.class);
        schoolService.save(schoolVO);
    }

    @Test
    void testUpdate() {
        String schoolData = "";
        SchoolVO schoolVO = Converts.onJackson().toBean(schoolData, SchoolVO.class);
        schoolService.updateById(schoolVO);
    }

    @Test
    void testDeleteById() {
        schoolService.deleteById(1L);
    }

    @Test
    void testDeleteByIds() {
        schoolService.deleteByIds(1L, 2L);
    }

    @Test
    void testGetByCondition() {
        String schoolGetConditionData = "";
        SchoolGetConditionVO schoolGetConditionVO = Converts.onJackson().toBean(schoolGetConditionData, SchoolGetConditionVO.class);
        schoolService.getByCondition(schoolGetConditionVO);
    }

    @Test
    void testListByCondition() {
        String schoolListConditionData = "";
        SchoolListConditionVO schoolListConditionVO = Converts.onJackson().toBean(schoolListConditionData, SchoolListConditionVO.class);
        schoolService.listByCondition(schoolListConditionVO);
    }

    @Test
    void testPageByCondition() {
        String schoolPageConditionData = "";
        SchoolPageConditionVO schoolPageConditionVO = Converts.onJackson().toBean(schoolPageConditionData, SchoolPageConditionVO.class);
        schoolService.pageByCondition(schoolPageConditionVO);
    }

}