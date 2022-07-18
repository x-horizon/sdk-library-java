package cn.srd.itcp.sugar.mybatis.plus.metadata.dao;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdDao;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po.PostgresqlDescriptionPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostgresqlDescriptionDao extends GenericCurdDao<PostgresqlDescriptionPO> {

}