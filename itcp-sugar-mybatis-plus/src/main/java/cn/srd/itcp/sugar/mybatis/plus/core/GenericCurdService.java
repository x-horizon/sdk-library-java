package cn.srd.itcp.sugar.mybatis.plus.core;

import cn.srd.itcp.sugar.mybatis.plus.support.SQL;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;

import java.io.Serializable;

/**
 * 通用的增删查改 service
 *
 * @author wjm
 * @date 2022-07-05
 */
public class GenericCurdService<Dao extends GenericCurdDao<PO>, PO> extends MPJBaseServiceImpl<Dao, PO> {

    /**
     * <pre>
     *  根据主键查询单条数据；
     *  适用于 binary 类型的主键，使用该方法进行查询时会将主键解码为十六进制字符串，示例：
     *  SELECT * FROM table_name WHERE primary_key_name = UNHEX('11ECFC44878866EEB0E40242AC120002');
     * </pre>
     *
     * @param poClass    表映射的实体类
     * @param primaryKey 主键
     * @return PO 模型
     */
    public PO getById(Class<PO> poClass, Serializable primaryKey) {
        return getOne(MpWrappers.<PO>withLambda().apply(SQL.getDecodeHexPrimaryKey(poClass, primaryKey)));
    }

    /**
     * 查询单条数据
     *
     * @param queryWrapper 查询条件
     * @return 结果集
     */
    public PO getByCondition(Wrapper<PO> queryWrapper) {
        return getOne(queryWrapper);
    }

}
