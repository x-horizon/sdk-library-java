// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.controller;

import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.studio.low.code.model.vo.TeacherGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherVO;
import cn.srd.library.java.studio.low.code.service.TeacherService;
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
 * 教师信息 controller
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Tag(name = "教师信息管理")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired private TeacherService teacherService;

    @Operation(summary = "保存数据")
    @PostMapping("/save")
    public WebResponse<Void> save(@RequestBody TeacherVO teacherVO) {
        teacherService.save(teacherVO);
        return success();
    }

    @Operation(summary = "根据 id 更新数据")
    @PostMapping("/updateById")
    public WebResponse<Void> updateById(@RequestBody TeacherVO teacherVO) {
        teacherService.updateById(teacherVO);
        return success();
    }

    @Operation(summary = "根据 id 删除数据")
    @PostMapping("/deleteByIds")
    public WebResponse<Void> deleteByIds(@Validated @RequestBody ValidList<Long> ids) {
        teacherService.deleteByIds(ids);
        return success();
    }

    @Operation(summary = "根据条件查询数据")
    @PostMapping("/getByCondition")
    public WebResponse<TeacherVO> getByCondition(@RequestBody TeacherGetConditionVO conditionVO) {
        return success(teacherService.getByCondition(conditionVO));
    }

    @Operation(summary = "根据条件查询列表数据")
    @PostMapping("/listByCondition")
    public WebResponse<List<TeacherVO>> listByCondition(@RequestBody TeacherListConditionVO conditionVO) {
        return success(teacherService.listByCondition(conditionVO));
    }

    @Operation(summary = "根据条件查询分页数据")
    @PostMapping("/pageByCondition")
    public WebResponse<PageResult<TeacherVO>> pageByCondition(@RequestBody TeacherPageConditionVO conditionVO) {
        return success(teacherService.pageByCondition(conditionVO));
    }

}