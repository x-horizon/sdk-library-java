package cn.srd.itcp.sugar.mybatis.plus.metadata.handler;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdService;
import cn.srd.itcp.sugar.mybatis.plus.core.MpWrappers;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto.PostgresqlTableColumnDTO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto.PostgresqlTablePrimaryKeyDTO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po.*;
import cn.srd.itcp.sugar.mybatis.plus.metadata.dao.PostgresqlClassDao;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;

import java.util.Collection;
import java.util.List;

/**
 * postgresql table column 基本信息 handler
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
public class PostgresqlTableColumnHandler extends GenericCurdService<PostgresqlClassDao, PostgresqlClassPO> {

    /**
     * 查询表字段信息
     *
     * @param tableName 表名
     * @return 该表下的字段信息
     */
    public List<PostgresqlTableColumnDTO> listByTableName(String tableName) {
        return listByTableNames(CollectionsUtil.toList(tableName));
    }

    /**
     * 查询表字段信息
     * <pre>
     * SELECT pg_attribute.attname       AS column_name,
     *        pg_type.typname            AS column_type,
     *        pg_description.description AS column_comment
     * FROM pg_class
     *          INNER JOIN pg_attribute ON pg_class.oid = pg_attribute.attrelid
     *          INNER JOIN pg_type ON pg_attribute.atttypid = pg_type.oid
     *          INNER JOIN pg_description ON pg_description.objoid = pg_attribute.attrelid AND pg_description.objsubid = pg_attribute.attnum
     *          INNER JOIN pg_am ON pg_am.oid = pg_class.relam
     * WHERE pg_attribute.attnum > 0
     *   AND pg_class.relname = 'tableName';
     * </pre>
     *
     * @param tableNames 表名
     * @return 结果集
     */
    public List<PostgresqlTableColumnDTO> listByTableNames(Collection<String> tableNames) {
        List<PostgresqlTableColumnDTO> postgresqlTableColumnDTOs = selectJoinList(
                PostgresqlTableColumnDTO.class,
                MpWrappers.<PostgresqlClassPO>withLambdaJoinQuery()
                        .selectAs(PostgresqlAttributePO::getAttname, PostgresqlTableColumnDTO::getColumnName)
                        .selectAs(PostgresqlTypePO::getTypname, PostgresqlTableColumnDTO::getColumnType)
                        .selectAs(PostgresqlDescriptionPO::getDescription, PostgresqlTableColumnDTO::getColumnComment)
                        .selectAs(PostgresqlClassPO::getRelname, PostgresqlTableColumnDTO::getTableName)
                        .innerJoin(PostgresqlAttributePO.class, PostgresqlAttributePO::getAttrelid, PostgresqlClassPO::getOid)
                        .innerJoin(PostgresqlTypePO.class, PostgresqlTypePO::getOid, PostgresqlAttributePO::getAtttypid)
                        .innerJoin(PostgresqlDescriptionPO.class, on -> on.eq(PostgresqlDescriptionPO::getObjoid, PostgresqlAttributePO::getAttrelid).eq(PostgresqlDescriptionPO::getObjsubid, PostgresqlAttributePO::getAttnum))
                        .innerJoin(PostgresqlAmPO.class, PostgresqlAmPO::getOid, PostgresqlClassPO::getRelam)
                        .gt(PostgresqlAttributePO::getAttnum, 0)
                        .in(PostgresqlClassPO::getRelname, tableNames)
        );
        List<PostgresqlTablePrimaryKeyDTO> postgresqlTablePrimaryKeyDTOs = listPrimaryKeysByTableNames(tableNames);
        postgresqlTableColumnDTOs.forEach(postgresqlTableColumnDTO -> postgresqlTableColumnDTO.setPrimaryKeyIs(false));
        CollectionsUtil.filters(
                postgresqlTableColumnDTOs,
                // TODO wjm 这种做法仅支持单主键或无主键的表，后续需要优化
                postgresqlTableColumnDTO -> {
                    PostgresqlTablePrimaryKeyDTO postgresqlTablePrimaryKeyDTOHasSameTable = CollectionsUtil.getFirst(CollectionsUtil.filters(
                            postgresqlTablePrimaryKeyDTOs,
                            postgresqlTablePrimaryKeyDTO -> Objects.equals(postgresqlTablePrimaryKeyDTO.getTableName(), postgresqlTableColumnDTO.getTableName())
                    ));
                    return Objects.isNull(postgresqlTablePrimaryKeyDTOHasSameTable) ? Boolean.FALSE : Objects.equals(postgresqlTableColumnDTO.getColumnName(), postgresqlTablePrimaryKeyDTOHasSameTable.getPrimaryKeyColumnName());
                }
        ).forEach(postgresqlTableColumnDTO -> postgresqlTableColumnDTO.setPrimaryKeyIs(true));
        return postgresqlTableColumnDTOs;
    }

    /**
     * 查询表主键信息
     *
     * @param tableName 表名
     * @return 结果集
     */
    private PostgresqlTablePrimaryKeyDTO getPrimaryKeyByTableName(String tableName) {
        return CollectionsUtil.getFirst(listPrimaryKeysByTableNames(CollectionsUtil.toList(tableName)));
    }

    /**
     * 查询表主键信息
     * <pre>
     * SELECT pg_constraint.conname AS primary_key_name,
     *        pg_attribute.attname  AS primary_key_column_name
     * FROM pg_class
     *          INNER JOIN pg_constraint ON pg_constraint.conrelid = pg_class.oid
     *          INNER JOIN pg_attribute ON pg_attribute.attrelid = pg_class.oid AND pg_attribute.attnum = pg_constraint.conkey[1]
     * WHERE pg_attribute.attnum > 0
     *   AND pg_class.relname = 'tableName';
     * </pre>
     *
     * @param tableNames 表名
     * @return 结果集
     */
    private List<PostgresqlTablePrimaryKeyDTO> listPrimaryKeysByTableNames(Collection<String> tableNames) {
        return selectJoinList(
                PostgresqlTablePrimaryKeyDTO.class,
                MpWrappers.<PostgresqlClassPO>withJoinQuery()
                        .select("t.relname AS table_name", "pg_constraint.conname AS primary_key_name", "pg_attribute.attname AS primary_key_column_name")
                        .innerJoin("pg_constraint ON pg_constraint.conrelid = t.oid")
                        .innerJoin("pg_attribute ON pg_attribute.attrelid = t.oid AND pg_attribute.attnum = pg_constraint.conkey[1]")
                        .gt("pg_attribute.attnum", 0)
                        .in("t.relname", tableNames)
        );
    }

}
