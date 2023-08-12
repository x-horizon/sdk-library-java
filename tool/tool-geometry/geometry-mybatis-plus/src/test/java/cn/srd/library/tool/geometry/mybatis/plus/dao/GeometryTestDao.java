package cn.srd.library.tool.geometry.mybatis.plus.dao;

import cn.srd.library.orm.mybatis.plus.core.GenericCurdDao;
import cn.srd.library.tool.geometry.mybatis.plus.model.po.GeometryTestPO;
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