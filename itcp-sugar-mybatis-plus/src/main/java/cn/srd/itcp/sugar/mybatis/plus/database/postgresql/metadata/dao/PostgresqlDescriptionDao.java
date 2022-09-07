package cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.dao;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdDao;
import cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.bean.po.PostgresqlDescriptionPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_description) dao
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlDescriptionDao extends GenericCurdDao<PostgresqlDescriptionPO> {

}