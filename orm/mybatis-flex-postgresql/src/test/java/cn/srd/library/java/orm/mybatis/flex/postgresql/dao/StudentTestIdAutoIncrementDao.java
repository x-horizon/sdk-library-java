package cn.srd.library.java.orm.mybatis.flex.postgresql.dao;

import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdAutoIncrementPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentTestIdAutoIncrementDao extends GenericCurdDao<StudentTestIdAutoIncrementPO> {

}
