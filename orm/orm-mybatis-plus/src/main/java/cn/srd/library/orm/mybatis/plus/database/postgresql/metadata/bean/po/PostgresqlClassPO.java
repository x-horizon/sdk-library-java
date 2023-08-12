package cn.srd.library.orm.mybatis.plus.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * (pg_class) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@TableName(value = "pg_class", autoResultMap = true)
public class PostgresqlClassPO {

    @TableField(value = "oid")
    private Long oid;

    @TableField(value = "relam")
    private Long relam;

    @TableField(value = "relname")
    private String relname;

}
