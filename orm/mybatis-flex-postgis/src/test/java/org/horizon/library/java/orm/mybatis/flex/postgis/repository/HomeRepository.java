package org.horizon.library.java.orm.mybatis.flex.postgis.repository;

import org.horizon.library.java.orm.mybatis.flex.postgis.model.po.HomePO;
import org.horizon.library.java.orm.mybatis.flex.postgresql.repository.GenericRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjm
 * @since 2024-07-24 09:27
 */
@Repository
public interface HomeRepository extends GenericRepository<HomePO> {

}