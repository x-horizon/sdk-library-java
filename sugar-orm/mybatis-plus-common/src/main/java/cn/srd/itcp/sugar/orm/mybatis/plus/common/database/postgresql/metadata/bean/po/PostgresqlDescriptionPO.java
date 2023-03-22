package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * (pg_description) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "pg_description", autoResultMap = true)
public class PostgresqlDescriptionPO {

    @TableField(value = "objoid")
    private Long objoid;

    @TableField(value = "objsubid")
    private Long objsubid;

    @TableField(value = "description")
    private String description;

}
