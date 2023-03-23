package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * postgresql table primary key 相关信息 数据传输模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class PostgresqlTablePrimaryKeyDTO {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 主键名称
     */
    private String primaryKeyName;

    /**
     * 主键对应的字段名称
     */
    private String primaryKeyColumnName;

}
