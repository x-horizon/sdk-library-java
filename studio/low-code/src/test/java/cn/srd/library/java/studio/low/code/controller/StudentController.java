// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.controller;

import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.studio.low.code.model.vo.StudentGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.StudentVO;
import cn.srd.library.java.studio.low.code.service.StudentService;
import cn.srd.library.java.tool.validation.jakarta.ValidList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.srd.library.java.contract.model.protocol.WebResponse.success;

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
    public WebResponse<Void> save(@RequestBody StudentVO studentVO) {
        studentService.save(studentVO);
        return success();
    }

    @Operation(summary = "根据 id 更新数据")
    @PostMapping("/updateById")
    public WebResponse<Void> updateById(@RequestBody StudentVO studentVO) {
        studentService.updateById(studentVO);
        return success();
    }

    @Operation(summary = "根据 id 删除数据")
    @PostMapping("/deleteByIds")
    public WebResponse<Void> deleteByIds(@Validated @RequestBody ValidList<Long> ids) {
        studentService.deleteByIds(ids);
        return success();
    }

    @Operation(summary = "根据条件查询数据")
    @PostMapping("/getByCondition")
    public WebResponse<StudentVO> getByCondition(@RequestBody StudentGetConditionVO conditionVO) {
        return success(studentService.getByCondition(conditionVO));
    }

    @Operation(summary = "根据条件查询列表数据")
    @PostMapping("/listByCondition")
    public WebResponse<List<StudentVO>> listByCondition(@RequestBody StudentListConditionVO conditionVO) {
        return success(studentService.listByCondition(conditionVO));
    }

    @Operation(summary = "根据条件查询分页数据")
    @PostMapping("/pageByCondition")
    public WebResponse<PageResult<StudentVO>> pageByCondition(@RequestBody StudentPageConditionVO conditionVO) {
        return success(studentService.pageByCondition(conditionVO));
    }

}