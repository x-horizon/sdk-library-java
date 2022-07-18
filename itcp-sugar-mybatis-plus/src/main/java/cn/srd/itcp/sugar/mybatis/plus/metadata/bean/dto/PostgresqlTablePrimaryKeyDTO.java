package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * postgresql table primary key 相关信息 数据传输模型
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
@Data
@Accessors(chain = true)
public class PostgresqlTablePrimaryKeyDTO {

    /**
     * 主键名称
     */
    private String primaryKeyName;

    /**
     * 主键对应的字段名称
     */
    private String primaryKeyColumnName;

}
