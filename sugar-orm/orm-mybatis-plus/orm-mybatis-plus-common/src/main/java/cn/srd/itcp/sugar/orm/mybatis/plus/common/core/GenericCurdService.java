package cn.srd.itcp.sugar.orm.mybatis.plus.common.core;

import cn.srd.itcp.sugar.orm.mybatis.plus.common.database.mysql.utils.SQL;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.base.MPJBaseServiceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通用的增删查改 service
 *
 * @param <Dao> 持久化模型对应的 dao
 * @param <PO>  持久化模型
 * @author wjm
 * @since 2022-07-05
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
     * 校验唯一性，该函数只适合与新增数据时不存在主键的唯一性校验，若存在主键的唯一性校验，使用 {@link #isUnique(Class, Serializable, SFunction, Object)}
     *
     * @param poClass                  表映射的实体类
     * @param requireUniqueColumn      校验唯一性的字段
     * @param requireUniqueColumnValue 校验唯一性的字段值
     * @param <T>                      校验唯一性的字段类型
     * @return true 代表唯一，false 代表不唯一
     */
    public <T> boolean isUnique(Class<PO> poClass, SFunction<PO, T> requireUniqueColumn, T requireUniqueColumnValue) {
        return isUnique(poClass, null, requireUniqueColumn, requireUniqueColumnValue);
    }

    /**
     * 校验唯一性
     *
     * @param poClass                  表映射的实体类
     * @param id                       表主键值，新增数据时传 null，更新数据时传具体的表主键值
     * @param requireUniqueColumn      校验唯一性的字段
     * @param requireUniqueColumnValue 校验唯一性的字段值
     * @param <T>                      校验唯一性的字段类型
     * @return true 代表唯一，false 代表不唯一
     */
    public <T> boolean isUnique(Class<PO> poClass, @Nullable Serializable id, SFunction<PO, T> requireUniqueColumn, T requireUniqueColumnValue) {
        // 若唯一值字段为空，表示唯一；
        if (Objects.isEmpty(requireUniqueColumnValue)) {
            return true;
        }
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

    /**
     * 唯一性结果查询
     *
     * @param requireUniqueColumn       校验唯一性的字段
     * @param requireUniqueColumnValues 校验唯一性的字段值
     * @param <T>                       校验唯一性的字段类型
     * @return 结果集
     */
    public <T> List<PO> isUnique(SFunction<PO, T> requireUniqueColumn, Collection<T> requireUniqueColumnValues) {
        // 若唯一值字段为空，表示唯一；
        if (Objects.isEmpty(requireUniqueColumnValues)) {
            return new ArrayList<>();
        }
        // 使用需要判断唯一值的字段查库，若存在数据，表示不唯一；
        return list(MpWrappers.<PO>withLambdaQuery().in(requireUniqueColumn, requireUniqueColumnValues));
    }

    /**
     * 校验唯一性，该函数只适合与新增数据时不存在主键的唯一性校验，若存在主键的唯一性校验，使用 {@link #isUnique(Class, Serializable, SFunction, Object, SFunction, Object)}
     *
     * @param poClass                   表映射的实体类
     * @param requireUniqueColumn       需要唯一性的字段，例如传入手机号码的字段名
     * @param requireUniqueColumnValue  需要唯一性的字段值，例如传入手机号码的字段值
     * @param conditionScopeColumn      限制在什么范围下校验唯一性的字段名，例如：传入表父级主键字段名，此时在同一个父级范围内手机号码不允许重复；
     * @param conditionScopeColumnValue 限制在什么范围下校验唯一性的字段值，例如：传入表父级主键字段值，此时在同一个父级范围内手机号码不允许重复；
     * @param <T>                       校验唯一性的字段类型
     * @param <K>                       范围字段类型
     * @return true 代表唯一，false 代表不唯一
     */
    public <T, K> boolean isUnique(Class<PO> poClass, SFunction<PO, T> requireUniqueColumn, T requireUniqueColumnValue, SFunction<PO, K> conditionScopeColumn, K conditionScopeColumnValue) {
        return isUnique(poClass, null, requireUniqueColumn, requireUniqueColumnValue, conditionScopeColumn, conditionScopeColumnValue);
    }

    /**
     * 校验唯一性
     *
     * @param poClass                   表映射的实体类
     * @param id                        表主键值，新增数据时传 null，更新数据时传具体的表主键值
     * @param requireUniqueColumn       需要唯一性的字段，例如传入手机号码的字段名
     * @param requireUniqueColumnValue  需要唯一性的字段值，例如传入手机号码的字段值
     * @param conditionScopeColumn      限制在什么范围下校验唯一性的字段名，例如：传入表父级主键字段名，此时在同一个父级范围内手机号码不允许重复；
     * @param conditionScopeColumnValue 限制在什么范围下校验唯一性的字段值，例如：传入表父级主键字段值，此时在同一个父级范围内手机号码不允许重复；
     * @param <T>                       校验唯一性的字段类型
     * @param <K>                       范围字段类型
     * @return true 代表唯一，false 代表不唯一
     */
    public <T, K> boolean isUnique(Class<PO> poClass, @Nullable Serializable id, SFunction<PO, T> requireUniqueColumn, T requireUniqueColumnValue, SFunction<PO, K> conditionScopeColumn, K conditionScopeColumnValue) {
        // 若唯一值字段为空，表示唯一；
        if (Objects.isEmpty(requireUniqueColumnValue)) {
            return true;
        }
        // 新增情况，id 为空，使用需要判断唯一值的字段查库，若存在数据，表示不唯一；
        if (Objects.isEmpty(id)) {
            return count(MpWrappers.<PO>withLambdaQuery()
                    .eq(requireUniqueColumn, requireUniqueColumnValue)
                    .eq(conditionScopeColumn, conditionScopeColumnValue)
            ) == 0L;
        }
        // 更新情况，id 不为空，使用需要判断唯一值的字段查库；
        PO po = getOne(MpWrappers.<PO>withLambdaQuery()
                .eq(requireUniqueColumn, requireUniqueColumnValue)
                .eq(conditionScopeColumn, conditionScopeColumnValue)
        );
        // 不存在数据，表示唯一；
        if (Objects.isNull(po)) {
            return true;
        }
        // 存在数据，若查出来数据的主键值等于形参中的主键值，表示为未修改该字段，此时是唯一的，否则不唯一；
        return Objects.equals(ReflectsUtil.getFieldValue(po, MpTables.getTableInfo(poClass).getKeyProperty()), id);
    }

}
