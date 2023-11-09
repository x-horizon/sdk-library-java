package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.orm.mybatis.flex.base.model.po.StudentPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<StudentPO> {

}
