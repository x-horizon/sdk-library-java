package cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (pg_constraint) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@Accessors(chain = true)
@TableName(value = "pg_constraint", autoResultMap = true)
public class PostgresqlConstraintPO {

    @TableField(value = "conrelid")
    private Long conrelid;

    @TableField(value = "conname")
    private String conname;

}
