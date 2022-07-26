package cn.srd.itcp.sugar.mybatis.plus.metadata.dao;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdDao;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po.PostgresqlAttributePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_attribute) dao
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlAttributeDao extends GenericCurdDao<PostgresqlAttributePO> {

}