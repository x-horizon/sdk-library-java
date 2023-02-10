package cn.srd.itcp.sugar.orm.mybatis.plus.database.postgresql.metadata.dao;

import cn.srd.itcp.sugar.orm.mybatis.plus.core.GenericCurdDao;
import cn.srd.itcp.sugar.orm.mybatis.plus.database.postgresql.metadata.bean.po.PostgresqlAmPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_am) dao
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlAmDao extends GenericCurdDao<PostgresqlAmPO> {

}