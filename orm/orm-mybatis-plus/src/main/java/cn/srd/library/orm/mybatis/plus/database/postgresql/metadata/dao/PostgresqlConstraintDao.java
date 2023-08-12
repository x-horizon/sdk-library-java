package cn.srd.library.orm.mybatis.plus.database.postgresql.metadata.dao;

import cn.srd.library.orm.mybatis.plus.core.GenericCurdDao;
import cn.srd.library.orm.mybatis.plus.database.postgresql.metadata.bean.po.PostgresqlConstraintPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_constraint) dao
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlConstraintDao extends GenericCurdDao<PostgresqlConstraintPO> {

}