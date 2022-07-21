package cn.srd.itcp.sugar.mybatis.plus.core;

import cn.srd.itcp.sugar.tools.constant.StringPool;
import cn.srd.itcp.sugar.tools.core.StringsUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;

import java.util.stream.Collectors;

/**
 * 实体类与表的映射信息辅助工具
 *
 * @author wjm
 * @date 2022-07-22 00:43:21
 */
public class MpTables extends TableInfoHelper {

    /**
     * 获取除主键外的所有字段的 select sql 片段
     * <pre>
     *    例如：name1,name2,name3
     * </pre>
     *
     * @param clazz 表模型的 Java 类型
     * @return 除主键外的所有字段的 select sql 片段
     */
    public static String getSqlSelectWithoutPrimaryKey(Class<?> clazz) {
        return StringsUtil.removeIfStartWith(
                TableInfoHelper.getTableInfo(clazz).getAllSqlSelect(),
                TableInfoHelper.getTableInfo(clazz).getKeyColumn() + StringPool.COMMA
        );
    }

    /**
     * 获取除主键外的所有字段的 select sql 片段
     * <pre>
     *    该函数适用于使用了 {@link com.github.yulichang.query.MPJQueryWrapper MPJQueryWrapper} 的连表查询语句，
     *    由于 {@link com.github.yulichang.query.MPJQueryWrapper MPJQueryWrapper} 会自动给主表拼接上别名 t，
     *    因此该函数会给除主键外所有字段的 select sql 片段前拼上 t.
     *    例如：t.name1,t.name2,t.name3
     * </pre>
     *
     * @param clazz 表模型的 Java 类型
     * @return 除主键外的所有字段的 select sql 片段
     */
    public static String getJoinSqlSelectWithoutPrimaryKey(Class<?> clazz) {
        // TODO wjm 待优化
        return StringsUtil.pretty(StringsUtil.split(getSqlSelectWithoutPrimaryKey(clazz), ",")
                .stream()
                .map(column -> "t." + column)
                .collect(Collectors.toList())
        );
    }

}
