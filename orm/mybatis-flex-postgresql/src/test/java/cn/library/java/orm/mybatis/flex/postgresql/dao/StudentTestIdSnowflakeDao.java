package cn.library.java.orm.mybatis.flex.postgresql.dao;

import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSnowflakePO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentTestIdSnowflakeDao extends BaseMapper<StudentTestIdSnowflakePO> {

}
