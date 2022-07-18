package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostgresqlTableDTO {

    /**
     * 表名称
     */
    @TableField(value = "table_name")
    private String tableName;

    /**
     * 表注释
     */
    @TableField(value = "table_comment")
    private String tableComment;

}
