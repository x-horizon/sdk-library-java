package cn.srd.itcp.sugar.mybatis.plus.metadata.handler;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdService;
import cn.srd.itcp.sugar.mybatis.plus.core.MpWrappers;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto.PostgresqlTableDTO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po.PostgresqlTablePO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.dao.PostgresqlTableDao;

/**
 * postgresql table 基本信息 handler
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
public class PostgresqlTableHandler extends GenericCurdService<PostgresqlTableDao, PostgresqlTablePO> {

    /**
     * 查询表信息
     * <pre>
     * SELECT pg_tables.tablename                                         AS table_name,
     *        CAST(OBJ_DESCRIPTION(t.relfilenode, 'pg_class') AS VARCHAR) AS table_comment
     * FROM pg_class t
     *          INNER JOIN pg_tables ON pg_tables.tablename = t.relname
     * WHERE pg_tables.tablename = t.relname
     *   AND pg_tables.schemaname = 'public'
     *   AND pg_tables.tablename = 'sys_post';
     * </pre>
     *
     * @param tableName 表名
     * @return 表信息
     */
    public PostgresqlTableDTO getByTableName(String tableName) {
        return selectJoinOne(
                PostgresqlTableDTO.class,
                MpWrappers.<PostgresqlTablePO>of()
                        .select("t.tablename AS table_name", "CAST(OBJ_DESCRIPTION(pg_class.relfilenode, 'pg_class') AS VARCHAR) AS table_comment")
                        .innerJoin("pg_class on pg_class.relname = t.tablename")
                        .eq("schemaname", "public")
                        .eq("tablename", tableName)
        );
    }

}
