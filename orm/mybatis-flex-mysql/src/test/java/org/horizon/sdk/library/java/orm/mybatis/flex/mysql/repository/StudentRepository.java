package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.repository;

import org.horizon.sdk.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.po.StudentPO;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends GenericRepository<StudentPO> {

}