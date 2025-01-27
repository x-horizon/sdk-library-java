package cn.library.java.studio.low.code.service;

import cn.library.java.contract.model.throwable.DataNotFoundException;
import cn.library.java.orm.contract.model.page.PageResult;
import cn.library.java.orm.mybatis.flex.postgresql.service.GenericService;
import cn.library.java.studio.low.code.model.po.TeacherPO;
import cn.library.java.studio.low.code.model.vo.TeacherGetConditionVO;
import cn.library.java.studio.low.code.model.vo.TeacherListConditionVO;
import cn.library.java.studio.low.code.model.vo.TeacherPageConditionVO;
import cn.library.java.studio.low.code.model.vo.TeacherVO;
import cn.library.java.studio.low.code.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师信息 service
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Service
public class TeacherService extends GenericService<TeacherPO, TeacherVO, TeacherRepository> {

    @Autowired private TeacherRepository teacherRepository;

    public TeacherVO getByCondition(TeacherGetConditionVO conditionVO) {
        return teacherRepository.openNormalQuery()
                .where(TeacherPO::getId).equalsTo(conditionVO.getId())
                .and(TeacherPO::getName).likeIfNotBlank(conditionVO.getName())
                .<TeacherVO>getToVO()
                .orElseThrow(DataNotFoundException::new);
    }

    public List<TeacherVO> listByCondition(TeacherListConditionVO conditionVO) {
        return teacherRepository.openNormalQuery()
                .where(TeacherPO::getName).likeIfNotBlank(conditionVO.getName())
                .listToVOs();
    }

    public PageResult<TeacherVO> pageByCondition(TeacherPageConditionVO conditionVO) {
        return teacherRepository.openNormalQuery()
                .where(TeacherPO::getName).likeIfNotBlank(conditionVO.getName())
                .pageToVO(conditionVO.getPageIndex(), conditionVO.getPageSize());
    }

}