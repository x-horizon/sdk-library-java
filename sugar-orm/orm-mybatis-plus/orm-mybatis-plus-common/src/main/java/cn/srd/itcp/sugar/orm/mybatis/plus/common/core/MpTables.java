package cn.srd.itcp.sugar.orm.mybatis.plus.common.core;

import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

import java.io.Serializable;

/**
 * 实体类与表的映射信息辅助工具
 *
 * @author wjm
 * @since 2022-07-22 00:43:21
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
                .toList()
        );
    }

    /**
     * <pre>
     * example:
     * 传入：1550095650131333122, "org_ancestor_ids"，则生成的 SQL 语句为：
     * '' || 1550095650131333122 = ANY (STRING_TO_ARRAY(org_ancestor_ids, ','))
     * </pre>
     *
     * @param needToInValue          被搜索的值
     * @param stringToArrayFieldName 搜索集合的字段名
     * @return 结果集
     */
    public static String getPostgresqlInStringToArray(Serializable needToInValue, String stringToArrayFieldName) {
        // example: '' || 1550095650131333122 = ANY (STRING_TO_ARRAY(org_ancestor_ids, ','))
        return StringsUtil.format(" '' || {} = ANY (string_to_array({},',')) ", needToInValue, stringToArrayFieldName);
    }

    /**
     * @param needToInValue          被搜索的值
     * @param stringToArrayFieldName 搜索集合的字段名
     * @return 结果集
     * @see #getPostgresqlInStringToArray(Serializable, String)
     */
    @SneakyThrows
    public static Expression getPostgresqlInStringToArrayExpression(Serializable needToInValue, String stringToArrayFieldName) {
        return CCJSqlParserUtil.parseExpression(getPostgresqlInStringToArray(needToInValue, stringToArrayFieldName));
    }

}
