package cn.srd.sugar.tool.geometry.mybatis.plus.database.postgresql.utils;

import cn.srd.itcp.sugar.tool.core.StringsUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SQL 解析器
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SQLParser {

    /**
     * Function: PostgreSQL Geometry ==&gt; TEXT
     */
    private static final String GEOMETRY_TO_STRING_FUNCTION = "ST_ASTEXT";

    /**
     * 将查询语句中指定的字段名替换为应用了函数 ST_ASTEXT(columnName) 的 SQL 片段，功能为 PostgreSQL Geometry ==&gt; TEXT
     *
     * @param poClass    表映射的实体类
     * @param columnName 指定的字段名
     * @param <PO>       PO 模型
     * @return id, columnName ==&gt; id, ST_ASTEXT(columnName) AS columnName
     */
    public static <PO> String replaceSelectSQLToGeometryToString(Class<PO> poClass, String columnName) {
        return replaceSelectSQLToGeometryToString(TableInfoHelper.getTableInfo(poClass).getAllSqlSelect(), columnName);
    }

    /**
     * 将指定语句中指定的字段名替换为应用了函数 ST_ASTEXT(columnName) 的 SQL 片段，功能为 PostgreSQL Geometry ==&gt; TEXT
     *
     * @param sql        指定的语句
     * @param columnName 指定的字段名
     * @return id, columnName ==&gt; id, ST_ASTEXT(columnName) AS columnName
     */
    public static String replaceSelectSQLToGeometryToString(String sql, String columnName) {
        String columnAfterReplace = GEOMETRY_TO_STRING_FUNCTION + "(" + columnName + ") AS " + columnName;
        return StringsUtil.replace(sql, columnName, columnAfterReplace);
    }

}