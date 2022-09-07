package cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (pg_description) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@Accessors(chain = true)
@TableName(value = "pg_description", autoResultMap = true)
public class PostgresqlDescriptionPO {

    @TableField(value = "objoid")
    private Long objoid;

    @TableField(value = "objsubid")
    private Long objsubid;

    @TableField(value = "description")
    private String description;

}
