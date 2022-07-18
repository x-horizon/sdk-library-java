package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "pg_class", autoResultMap = true)
public class PostgresqlClassPO {

    @TableField(value = "oid")
    private Long oid;

    @TableField(value = "relam")
    private Long relam;

    @TableField(value = "relname")
    private String relname;

}
