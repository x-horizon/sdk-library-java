package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (pg_type) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@Accessors(chain = true)
@TableName(value = "pg_type", autoResultMap = true)
public class PostgresqlTypePO {

    @TableField(value = "oid")
    private Long oid;

    @TableField(value = "typname")
    private String typname;

}
