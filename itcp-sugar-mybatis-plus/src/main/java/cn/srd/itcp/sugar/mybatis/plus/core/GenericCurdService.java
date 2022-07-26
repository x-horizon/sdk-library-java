package cn.srd.itcp.sugar.mybatis.plus.core;

import cn.srd.itcp.sugar.mybatis.plus.support.SQL;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.ReflectsUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.lang.Nullable;

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
        return getOne(MpWrappers.<PO>withLambdaJoinQuery().apply(SQL.getDecodeHexPrimaryKey(poClass, primaryKey)));
    }

    /**
     * 校验唯一性，该函数只适合与新增数据时不存在主键的唯一性校验，若存在主键的唯一性校验，使用 {@link #isUnique(Class, SFunction, String, Serializable)}
     *
     * @param poClass                  表映射的实体类
     * @param requireUniqueColumn      校验唯一性的字段
     * @param requireUniqueColumnValue 校验唯一性的字段值
     * @return true 代表唯一，false 代表不唯一
     */
    public boolean isUnique(Class<PO> poClass, SFunction<PO, ?> requireUniqueColumn, String requireUniqueColumnValue) {
        return isUnique(poClass, requireUniqueColumn, requireUniqueColumnValue, null);
    }

    /**
     * 校验唯一性
     *
     * @param poClass                  表映射的实体类
     * @param requireUniqueColumn      校验唯一性的字段
     * @param requireUniqueColumnValue 校验唯一性的字段值
     * @param id                       表主键值，新增数据时传 null，更新数据时传具体的表主键值
     * @return true 代表唯一，false 代表不唯一
     */
    public boolean isUnique(Class<PO> poClass, SFunction<PO, ?> requireUniqueColumn, String requireUniqueColumnValue, @Nullable Serializable id) {
        // 新增情况，id 为空，使用需要判断唯一值的字段查库，若存在数据，表示不唯一；
        if (Objects.isNull(id)) {
            return count(MpWrappers.<PO>withLambdaQuery().eq(requireUniqueColumn, requireUniqueColumnValue)) == 0L;
        }
        // 更新情况，id 不为空，使用需要判断唯一值的字段查库；
        PO po = getOne(MpWrappers.<PO>withLambdaQuery().eq(requireUniqueColumn, requireUniqueColumnValue));
        // 不存在数据，表示唯一；
        if (Objects.isNull(po)) {
            return true;
        }
        // 存在数据，若查出来数据的主键值等于形参中的主键值，表示为未修改该字段，此时是唯一的，否则不唯一；
        return Objects.equals(ReflectsUtil.getFieldValue(po, MpTables.getTableInfo(poClass).getKeyProperty()), id);
    }

}
