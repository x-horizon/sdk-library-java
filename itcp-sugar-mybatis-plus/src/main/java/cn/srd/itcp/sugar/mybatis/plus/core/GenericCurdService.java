package cn.srd.itcp.sugar.mybatis.plus.core;

import cn.srd.itcp.sugar.mybatis.plus.support.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 通用的增删查改 service
 *
 * @author wjm
 * @date 2022-07-05
 */
@Service
public class GenericCurdService<PO> {

    @Autowired
    private GenericCurdDao<PO> genericCurdDao;

    /**
     * 新增单个数据，返回新增后的数据
     *
     * @param po PO 模型
     * @return 新增后的数据
     */
    public PO save(PO po) {
        return genericCurdDao.save(po);
    }

    /**
     * 批量新增数据，返回新增后的数据
     *
     * @param pos PO 模型
     * @return 新增后的数据
     */
    public List<PO> saveBatch(List<PO> pos) {
        genericCurdDao.saveBatch(pos);
        return pos;
    }

    /**
     * 根据主键修改数据，返回修改后的数据
     *
     * @param po PO 模型
     * @return 修改后的数据
     */
    public PO updateByPrimaryKey(PO po) {
        return genericCurdDao.updateByPrimaryKey(po);
    }

    /**
     * 根据主键批量修改数据，返回修改后的数据
     *
     * @param pos PO 模型
     * @return 修改后的数据
     */
    public List<PO> updateBatchByPrimaryKey(List<PO> pos) {
        genericCurdDao.updateBatchByPrimaryKey(pos);
        return pos;
    }

    /**
     * 根据主键查询数据
     *
     * @param primaryKey 主键
     * @return PO 模型
     */
    public PO getByPrimary(Serializable primaryKey) {
        return genericCurdDao.getByPrimary(primaryKey);
    }

    /**
     * 根据主键查询数据
     * <pre>
     *  适用于 binary 类型的主键，使用该方法进行查询时会将主键解码为十六进制字符串，示例：
     *      SELECT * FROM table_name WHERE primary_key_name = UNHEX('11ECFC44878866EEB0E40242AC120002');
     * </pre>
     *
     * @param poClass    表映射的实体类
     * @param primaryKey 主键
     * @return PO 模型
     */
    public PO getByPrimary(Class<PO> poClass, Serializable primaryKey) {
        return genericCurdDao.selectOne(MpQueryWrappers.apply(SQL.getDecodeHexPrimaryKey(poClass, primaryKey)));
    }

    /**
     * 根据主键查询列表数据（不分页）
     *
     * @param primaryKeys 主键
     * @return PO 模型
     */
    public List<PO> listByPrimaryKeys(Collection<? extends Serializable> primaryKeys) {
        return genericCurdDao.listByPrimaryKeys(primaryKeys);
    }

    /**
     * 根据主键删除数据，返回删除是否成功
     *
     * @param primaryKey 主键
     * @return 是否成功
     */
    public boolean removeByPrimaryKey(Long primaryKey) {
        return genericCurdDao.removeByPrimaryKey(primaryKey);
    }

}
