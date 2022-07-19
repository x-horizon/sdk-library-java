package cn.srd.itcp.sugar.mybatis.plus.metadata.handler;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdService;
import cn.srd.itcp.sugar.mybatis.plus.core.MpWrappers;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto.PostgresqlTableDTO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po.PostgresqlTablePO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.vo.PostgresqlTableVO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.dao.PostgresqlTableDao;
import cn.srd.itcp.sugar.mybatis.plus.utils.Converts;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.page.PageParam;
import cn.srd.itcp.sugar.tools.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * postgresql table 基本信息 handler
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
public class PostgresqlTableHandler extends GenericCurdService<PostgresqlTableDao, PostgresqlTablePO> {

    /**
     * 查询表信息
     *
     * @param tableName 表名
     * @return 结果集
     */
    public PostgresqlTableDTO getByTableName(String tableName) {
        return CollectionsUtil.getFirst(listByTableNames(CollectionsUtil.toList(tableName)));
    }

    /**
     * 查询表信息
     * <pre>
     * SELECT pg_tables.tablename                                         AS table_name,
     *        CAST(OBJ_DESCRIPTION(t.relfilenode, 'pg_class') AS VARCHAR) AS table_comment
     * FROM pg_class t
     *          INNER JOIN pg_tables ON pg_tables.tablename = t.relname
     * WHERE pg_tables.tablename = t.relname
     *   AND pg_tables.schemaname = 'public'
     *   AND pg_tables.tablename IN ('tableName');
     * </pre>
     *
     * @param tableNames 表名
     * @return 结果集
     */
    public List<PostgresqlTableDTO> listByTableNames(Collection<String> tableNames) {
        return selectJoinList(
                PostgresqlTableDTO.class,
                MpWrappers.<PostgresqlTablePO>withJoinQuery()
                        .select("t.tablename AS table_name", "CAST(OBJ_DESCRIPTION(pg_class.relfilenode, 'pg_class') AS VARCHAR) AS table_comment")
                        .innerJoin("pg_class on pg_class.relname = t.tablename")
                        .eq("schemaname", "public")
                        .in("tablename", tableNames)
        );
    }

    /**
     * 查询所有表信息
     * <pre>
     * SELECT pg_tables.tablename                                         AS table_name,
     *        CAST(OBJ_DESCRIPTION(t.relfilenode, 'pg_class') AS VARCHAR) AS table_comment
     * FROM pg_class t
     *          INNER JOIN pg_tables ON pg_tables.tablename = t.relname
     * WHERE pg_tables.tablename = t.relname
     *   AND pg_tables.schemaname = 'public';
     * </pre>
     *
     * @return 结果集
     */
    public <T extends PageParam> PageResult<PostgresqlTableVO> listAll(T pageParam) {
        return Converts.withMybatisPlusMapstruct().toPageBean(
                selectJoinListPage(new Page<>(pageParam.getPageIndex(), pageParam.getPageSize()),
                        PostgresqlTableDTO.class,
                        MpWrappers.<PostgresqlTablePO>withJoinQuery()
                                .select("t.tablename AS table_name", "CAST(OBJ_DESCRIPTION(pg_class.relfilenode, 'pg_class') AS VARCHAR) AS table_comment")
                                .innerJoin("pg_class on pg_class.relname = t.tablename")
                                .eq("schemaname", "public")
                ),
                PostgresqlTableVO.class
        );
    }

}
