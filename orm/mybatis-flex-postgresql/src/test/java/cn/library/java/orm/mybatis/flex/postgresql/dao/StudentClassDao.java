package cn.library.java.orm.mybatis.flex.postgresql.dao;

import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentClassPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentClassDao extends BaseMapper<StudentClassPO> {

}
