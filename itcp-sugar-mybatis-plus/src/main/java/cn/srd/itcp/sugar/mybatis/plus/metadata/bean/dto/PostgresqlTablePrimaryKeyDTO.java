package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
