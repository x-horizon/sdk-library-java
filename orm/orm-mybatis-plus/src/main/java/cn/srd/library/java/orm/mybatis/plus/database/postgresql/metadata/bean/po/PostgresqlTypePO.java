package cn.srd.library.java.orm.mybatis.plus.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * (pg_type) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@TableName(value = "pg_type", autoResultMap = true)
public class PostgresqlTypePO {

    @TableField(value = "oid")
    private Long oid;

    @TableField(value = "typname")
    private String typname;

}
