package cn.srd.library.java.orm.mybatis.plus.database.postgresql.metadata.bean.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * postgresql table 相关信息 数据传输模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
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
