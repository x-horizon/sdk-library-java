package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "pg_attribute", autoResultMap = true)
public class PostgresqlAttributePO {

    @TableField(value = "attrelid")
    private Long attrelid;

    @TableField(value = "atttypid")
    private Long atttypid;

    @TableField(value = "attnum")
    private Long attnum;

    @TableField(value = "attname")
    private String attname;

}
