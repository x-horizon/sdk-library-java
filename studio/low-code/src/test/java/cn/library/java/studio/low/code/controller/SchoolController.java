package cn.library.java.studio.low.code.controller;

import cn.library.java.orm.contract.model.page.PageResult;
import cn.library.java.studio.low.code.model.vo.SchoolGetConditionVO;
import cn.library.java.studio.low.code.model.vo.SchoolListConditionVO;
import cn.library.java.studio.low.code.model.vo.SchoolPageConditionVO;
import cn.library.java.studio.low.code.model.vo.SchoolVO;
import cn.library.java.studio.low.code.service.SchoolService;
import cn.library.java.tool.validation.jakarta.ValidList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学校信息 controller
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Tag(name = "学校信息管理")
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired private SchoolService schoolService;

    @Operation(summary = "保存数据")
    @PostMapping("/save")
    public void save(@RequestBody SchoolVO schoolVO) {
        schoolService.save(schoolVO);
    }

    @Operation(summary = "根据 id 更新数据")
    @PostMapping("/updateById")
    public void updateById(@RequestBody SchoolVO schoolVO) {
        schoolService.updateById(schoolVO);
    }

    @Operation(summary = "根据 id 删除数据")
    @PostMapping("/deleteByIds")
    public void deleteByIds(@Validated @RequestBody ValidList<Long> ids) {
        schoolService.deleteByIds(ids);
    }

    @Operation(summary = "根据条件查询数据")
    @PostMapping("/getByCondition")
    public SchoolVO getByCondition(@RequestBody SchoolGetConditionVO conditionVO) {
        return schoolService.getByCondition(conditionVO);
    }

    @Operation(summary = "根据条件查询列表数据")
    @PostMapping("/listByCondition")
    public List<SchoolVO> listByCondition(@RequestBody SchoolListConditionVO conditionVO) {
        return schoolService.listByCondition(conditionVO);
    }

    @Operation(summary = "根据条件查询分页数据")
    @PostMapping("/pageByCondition")
    public PageResult<SchoolVO> pageByCondition(@RequestBody SchoolPageConditionVO conditionVO) {
        return schoolService.pageByCondition(conditionVO);
    }

}