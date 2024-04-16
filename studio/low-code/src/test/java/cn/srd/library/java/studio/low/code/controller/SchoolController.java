// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.controller;

import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.studio.low.code.model.vo.SchoolListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolVO;
import cn.srd.library.java.studio.low.code.service.SchoolService;
import cn.srd.library.java.tool.validation.jakarta.ValidList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.srd.library.java.contract.model.protocol.WebResponse.success;

/**
 * 学校信息 controller
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Tag(name = "学校信息管理")
@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @Operation(summary = "保存数据")
    @PostMapping("/save")
    public WebResponse<Void> save(@RequestBody SchoolVO schoolVO) {
        schoolService.save(schoolVO);
        return success();
    }

    @Operation(summary = "更新数据")
    @PostMapping("/update")
    public WebResponse<Void> update(@RequestBody SchoolVO schoolVO) {
        schoolService.updateById(schoolVO);
        return success();
    }

    @Operation(summary = "根据 id 删除数据")
    @PostMapping("/deleteById")
    public WebResponse<Void> deleteById(@RequestParam Long id) {
        schoolService.deleteById(id);
        return success();
    }

    @Operation(summary = "根据 id 批量删除数据")
    @PostMapping("/deleteByIds")
    public WebResponse<Void> deleteByIds(@Validated @RequestBody ValidList<Long> ids) {
        schoolService.deleteByIds(ids);
        return success();
    }

    @Operation(summary = "根据 id 查询数据")
    @PostMapping("/getById")
    public WebResponse<SchoolVO> getById(@RequestParam Long id) {
        return success(schoolService.getById(id).orElseThrow(DataNotFoundException::new));
    }

    @Operation(summary = "根据 id 查询列表数据")
    @PostMapping("/listByIds")
    public WebResponse<List<SchoolVO>> listByIds(@Validated @RequestBody ValidList<Long> ids) {
        return success(schoolService.listByIds(ids));
    }

    @Operation(summary = "根据条件查询列表数据")
    @PostMapping("/listByCondition")
    public WebResponse<List<SchoolVO>> listByCondition(@RequestBody SchoolListConditionVO schoolListConditionVO) {
        return success(schoolService.listByCondition(schoolListConditionVO));
    }

    @Operation(summary = "根据条件查询分页数据")
    @PostMapping("/pageByCondition")
    public WebResponse<PageResult<SchoolVO>> pageByCondition(@RequestBody SchoolPageConditionVO schoolPageConditionVO) {
        return success(schoolService.pageByCondition(schoolPageConditionVO));
    }

}