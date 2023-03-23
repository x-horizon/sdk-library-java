package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * (pg_attribute) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
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
