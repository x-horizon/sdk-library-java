package cn.srd.itcp.sugar.mybatis.plus.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
     * 1、在 join 的相关函数里，主表默认使用别名 t；
     * 2、使用示例：
     *    MpWrappers.&#060;PO&#062;of()
     *            .select("column1 AS column_name1", "column2 AS column_name2")
     *            .innerJoin("table2 ON table2.id = t.id")
     *            .gt("table2.number", 0)
     *            .eq("t.name", "name")
     * </pre>
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return 使用硬编码查询的 Wrapper
     */
    public static <T> MPJQueryWrapper<T> with() {
        return new MPJQueryWrapper<>();
    }

    /**
     * 获取一个使用 lambda 方式查询的 Wrapper
     * <pre>
     * 相关说明：
     * 1、在 join 的相关函数里，主表默认使用别名 t；
     * 2、使用示例：
     *    MpWrappers.&#060;PO&#062;ofLambda()
     *            .selectAs(PO::getName, DTO::getSpecialName)
     *            .innerJoin(PO2.class, PO2::getId, PO::getId)
     *            .gt(PO2::getAge, 20)
     *            .eq(PO::getSex, "sex");
     * </pre>
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return 使用 lambda 方式查询的 Wrapper
     */
    public static <T> MPJLambdaWrapper<T> withLambda() {
        return new MPJLambdaWrapper<>();
    }

    /**
     * 获取一个空的 {@link QueryWrapper}
     *
     * @param <T> 在单表查询中表示该表对应的 PO 模型类型，在连表查询中表示主表对应的 PO 模型类型
     * @return 空的 {@link QueryWrapper}
     */
    public static <T> QueryWrapper<T> emptyWrapper() {
        return Wrappers.emptyWrapper();
    }

}
