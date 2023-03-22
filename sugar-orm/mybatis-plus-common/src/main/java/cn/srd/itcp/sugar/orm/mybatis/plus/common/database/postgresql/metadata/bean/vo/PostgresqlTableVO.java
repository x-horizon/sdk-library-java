package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * postgresql table 相关信息 视图模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PostgresqlTableVO {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

}
