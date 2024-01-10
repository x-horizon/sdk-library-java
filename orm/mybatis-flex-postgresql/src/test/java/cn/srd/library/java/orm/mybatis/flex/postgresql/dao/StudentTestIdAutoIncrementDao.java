package cn.srd.library.java.orm.mybatis.flex.postgresql.dao;

import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdAutoIncrementWithVersionPO;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTestIdAutoIncrementDao extends GenericDao<StudentTestIdAutoIncrementWithVersionPO> {

}