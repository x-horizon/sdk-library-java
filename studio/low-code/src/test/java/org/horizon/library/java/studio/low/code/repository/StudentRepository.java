package org.horizon.library.java.studio.low.code.repository;

import org.horizon.library.java.orm.mybatis.flex.postgresql.repository.GenericRepository;
import org.horizon.library.java.studio.low.code.model.po.StudentPO;
import org.springframework.stereotype.Repository;

/**
 * 学生信息 repository
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Repository
public interface StudentRepository extends GenericRepository<StudentPO> {

}