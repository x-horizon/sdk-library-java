package cn.srd.itcp.sugar.orm.mybatis.plus.core;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.interfaces.MPJBaseJoin;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

/**
 * Mybatis Plus 使用入口
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
public class MpWrappers {

    /**
     * 获取用于查询的无条件 Wrapper
     *
     * @param <T> 模型类型
     * @return 用于查询的无条件 {@link QueryWrapper}&lt;T&gt;
     */
    public static <T> QueryWrapper<T> withEmptyQuery() {
        return Wrappers.emptyWrapper();
    }

    /**
     * 获取硬编码方式用于查询的 Wrapper
     *
     * @param <T> 模型类型
     * @return 用于查询的 {@link QueryWrapper}&lt;T&gt;
     */
    public static <T> QueryWrapper<T> withQuery() {
        return new QueryWrapper<>();
    }

    /**
     * 获取硬编码方式并支持连表查询的 Wrapper，参考 {@link #withLambdaQuery()}
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return 用于连表查询的 {@link MPJQueryWrapper}&lt;T&gt;
     */
    public static <T> MPJQueryWrapper<T> withJoinQuery() {
        return new MPJQueryWrapper<>();
    }

    /**
     * 获取 lambda 方式用于查询的 Wrapper
     *
     * @param <T> 模型类型
     * @return 用于查询的 {@link LambdaQueryWrapper}&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> withLambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * 获取 lambda 方式并支持连表查询的 Wrapper
     * <pre>
     * 相关说明：
     * 1、在 join 的相关函数里，主表默认使用别名 t，因此只有使用以下方式查询时才能使用该方法，其余情况由于不会自动使用别名 t，此时使用该方法会报 SQL 的错误；
     *   {@link MPJBaseServiceImpl#selectJoinCount(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinList(Class, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinListPage(IPage, Class, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMap(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMaps(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMapsPage(IPage, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinOne(Class, MPJBaseJoin)}
     * 2、使用该方式查询必须显式指定结果集，否则不会返回结果集；
     * 3、使用示例：
     *    MpWrappers.&#060;PO&#062;withLambdaJoinQuery()
     *            .selectAs(PO::getName, DTO::getSpecialName)
     *            .innerJoin(PO2.class, PO2::getId, PO::getId)
     *            .gt(PO2::getAge, 20)
     *            .eq(PO::getSex, "sex");
     * </pre>
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return 用于连表查询的 {@link MPJLambdaWrapper}&lt;T&gt;
     */
    public static <T> MPJLambdaWrapper<T> withLambdaJoinQuery() {
        return new MPJLambdaWrapper<>();
    }

    /**
     * 获取硬编码方式用于更新的 Wrapper
     *
     * @param <T> 模型类型
     * @return 用于更新的 {@link UpdateWrapper}&lt;T&gt;
     */
    public static <T> UpdateWrapper<T> withUpdate() {
        return new UpdateWrapper<>();
    }

    /**
     * 获取 lambda 方式用于更新的 Wrapper
     *
     * @param <T> 模型类型
     * @return 用于更新的 {@link LambdaUpdateWrapper}&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> withLambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

}
