package cn.srd.library.java.orm.mybatis.plus.database.postgresql.metadata.dao;

import cn.srd.library.java.orm.mybatis.plus.core.GenericCurdDao;
import cn.srd.library.java.orm.mybatis.plus.database.postgresql.metadata.bean.po.PostgresqlTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_type) dao
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlTypeDao extends GenericCurdDao<PostgresqlTypePO> {

}