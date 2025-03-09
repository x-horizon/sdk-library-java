package org.horizon.sdk.library.java.studio.low.code.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.horizon.sdk.library.java.orm.contract.model.page.PageResult;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentGetConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentListConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.StudentVO;
import org.horizon.sdk.library.java.studio.low.code.service.StudentService;
import org.horizon.sdk.library.java.tool.validation.jakarta.ValidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学生信息 controller
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Tag(name = "学生信息管理")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired private StudentService studentService;

    @Operation(summary = "保存数据")
    @PostMapping("/save")
    public void save(@RequestBody StudentVO studentVO) {
        studentService.save(studentVO);
    }

    @Operation(summary = "根据 id 更新数据")
    @PostMapping("/updateById")
    public void updateById(@RequestBody StudentVO studentVO) {
        studentService.updateById(studentVO);
    }

    @Operation(summary = "根据 id 删除数据")
    @PostMapping("/deleteByIds")
    public void deleteByIds(@Validated @RequestBody ValidList<Long> ids) {
        studentService.deleteByIds(ids);
    }

    @Operation(summary = "根据条件查询数据")
    @PostMapping("/getByCondition")
    public StudentVO getByCondition(@RequestBody StudentGetConditionVO conditionVO) {
        return studentService.getByCondition(conditionVO);
    }

    @Operation(summary = "根据条件查询列表数据")
    @PostMapping("/listByCondition")
    public List<StudentVO> listByCondition(@RequestBody StudentListConditionVO conditionVO) {
        return studentService.listByCondition(conditionVO);
    }

    @Operation(summary = "根据条件查询分页数据")
    @PostMapping("/pageByCondition")
    public PageResult<StudentVO> pageByCondition(@RequestBody StudentPageConditionVO conditionVO) {
        return studentService.pageByCondition(conditionVO);
    }

}