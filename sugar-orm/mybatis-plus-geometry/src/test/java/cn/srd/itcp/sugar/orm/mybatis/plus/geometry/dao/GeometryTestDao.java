package cn.srd.itcp.sugar.orm.mybatis.plus.geometry.dao;

import cn.srd.itcp.sugar.orm.mybatis.plus.common.core.GenericCurdDao;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.model.po.GeometryTestPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * table geometry_test orm test
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@Mapper
public interface GeometryTestDao extends GenericCurdDao<GeometryTestPO> {

}