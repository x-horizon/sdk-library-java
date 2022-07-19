package cn.srd.itcp.sugar.mybatis.plus.core;

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
 * @date 2022-07-18 17:59:54
 */
public class MpWrappers {

    /**
     * 获取一个使用硬编码查询的 Wrapper
     * <pre>
     * 相关说明：
     * 1、在 join 的相关函数里，主表默认使用别名 t，因此只有使用以下方式查询时才能使用该方法，其余情况由于不会自动使用别名 t，使用了该方法会报错：
     *   {@link MPJBaseServiceImpl#selectJoinCount(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinList(Class, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinListPage(IPage, Class, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMap(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMaps(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMapsPage(IPage, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinOne(Class, MPJBaseJoin)}
     * 2、使用示例：
     *    MpWrappers.&#060;PO&#062;ofLambda()
     *            .selectAs(PO::getName, DTO::getSpecialName)
     *            .innerJoin(PO2.class, PO2::getId, PO::getId)
     *            .gt(PO2::getAge, 20)
     *            .eq(PO::getSex, "sex");
     * </pre>
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return {@link MPJQueryWrapper}&lt;T&gt;
     */
    public static <T> MPJQueryWrapper<T> withJoin() {
        return new MPJQueryWrapper<>();
    }

    /**
     * 获取一个使用 lambda 方式查询且支持连表查询的 Wrapper
     * <pre>
     * 相关说明：
     * 1、在 join 的相关函数里，主表默认使用别名 t，因此只有使用以下方式查询时才能使用该方法，其余情况由于不会自动使用别名 t，使用了该方法会报错：
     *   {@link MPJBaseServiceImpl#selectJoinCount(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinList(Class, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinListPage(IPage, Class, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMap(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMaps(MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinMapsPage(IPage, MPJBaseJoin)}
     *   {@link MPJBaseServiceImpl#selectJoinOne(Class, MPJBaseJoin)}
     * 2、使用示例：
     *    MpWrappers.&#060;PO&#062;ofLambda()
     *            .selectAs(PO::getName, DTO::getSpecialName)
     *            .innerJoin(PO2.class, PO2::getId, PO::getId)
     *            .gt(PO2::getAge, 20)
     *            .eq(PO::getSex, "sex");
     * </pre>
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return {@link MPJLambdaWrapper}&lt;T&gt;
     */
    public static <T> MPJLambdaWrapper<T> withJoinLambda() {
        return new MPJLambdaWrapper<>();
    }

    /**
     * 获取空的 {@link QueryWrapper}
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return 空的 {@link QueryWrapper}&lt;T&gt;
     */
    public static <T> QueryWrapper<T> emptyWrapper() {
        return Wrappers.emptyWrapper();
    }

    /**
     * 获取 {@link UpdateWrapper}
     *
     * @param <T> 实体类泛型
     * @return {@link UpdateWrapper}&lt;T&gt;
     */
    public static <T> UpdateWrapper<T> update() {
        return new UpdateWrapper<>();
    }

    /**
     * 获取 {@link QueryWrapper}
     *
     * @param <T> 实体类泛型
     * @return {@link QueryWrapper}&lt;T&gt;
     */
    public static <T> QueryWrapper<T> query() {
        return new QueryWrapper<>();
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param <T> 实体类泛型
     * @return {@link LambdaUpdateWrapper}&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> lambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param <T> 模型类型
     * @return {@link LambdaQueryWrapper}&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

}
