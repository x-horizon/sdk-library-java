package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * postgresql table 相关信息 数据传输模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PostgresqlTableDTO {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

}
