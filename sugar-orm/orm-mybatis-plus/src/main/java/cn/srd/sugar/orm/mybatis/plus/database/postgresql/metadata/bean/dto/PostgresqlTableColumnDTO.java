package cn.srd.sugar.orm.mybatis.plus.database.postgresql.metadata.bean.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * postgresql table column 相关信息 数据传输模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class PostgresqlTableColumnDTO {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 是否为主键
     */
    private Boolean primaryKeyIs;

}
