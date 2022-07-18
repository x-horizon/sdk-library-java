package cn.srd.itcp.sugar.mybatis.plus.metadata.dao;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdDao;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po.PostgresqlClassPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_class) dao
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlClassDao extends GenericCurdDao<PostgresqlClassPO> {

}