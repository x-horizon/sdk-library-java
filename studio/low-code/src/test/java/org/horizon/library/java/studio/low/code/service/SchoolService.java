package org.horizon.library.java.studio.low.code.service;

import org.horizon.library.java.contract.model.throwable.DataNotFoundException;
import org.horizon.library.java.orm.contract.model.page.PageResult;
import org.horizon.library.java.orm.mybatis.flex.postgresql.service.GenericService;
import org.horizon.library.java.studio.low.code.model.po.SchoolPO;
import org.horizon.library.java.studio.low.code.model.vo.SchoolGetConditionVO;
import org.horizon.library.java.studio.low.code.model.vo.SchoolListConditionVO;
import org.horizon.library.java.studio.low.code.model.vo.SchoolPageConditionVO;
import org.horizon.library.java.studio.low.code.model.vo.SchoolVO;
import org.horizon.library.java.studio.low.code.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学校信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
public class SchoolService extends GenericService<SchoolPO, SchoolVO, SchoolRepository> {

    @Autowired private SchoolRepository schoolRepository;

    public SchoolVO getByCondition(SchoolGetConditionVO conditionVO) {
        return schoolRepository.openNormalQuery()
                .where(SchoolPO::getId).equalsTo(conditionVO.getId())
                .and(SchoolPO::getName).likeIfNotBlank(conditionVO.getName())
                .<SchoolVO>getToVO()
                .orElseThrow(DataNotFoundException::new);
    }

    public List<SchoolVO> listByCondition(SchoolListConditionVO conditionVO) {
        return schoolRepository.openNormalQuery()
                .where(SchoolPO::getId).inIfNotEmpty(conditionVO.getIds())
                .and(SchoolPO::getName).likeIfNotBlank(conditionVO.getName())
                .and(SchoolPO::getAddress).likeIfNotBlank(conditionVO.getAddress())
                .listToVOs();
    }

    public PageResult<SchoolVO> pageByCondition(SchoolPageConditionVO conditionVO) {
        return schoolRepository.openNormalQuery()
                .where(SchoolPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageIndex(), conditionVO.getPageSize());
    }

}