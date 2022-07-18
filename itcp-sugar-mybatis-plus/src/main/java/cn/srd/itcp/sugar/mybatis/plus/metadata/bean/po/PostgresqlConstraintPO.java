package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "pg_constraint", autoResultMap = true)
public class PostgresqlConstraintPO {

    @TableField(value = "conrelid")
    private Long conrelid;

    @TableField(value = "conname")
    private String conname;

}
