package cn.srd.library.java.orm.mybatis.plus.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * (pg_constraint) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@TableName(value = "pg_constraint", autoResultMap = true)
public class PostgresqlConstraintPO {

    @TableField(value = "conrelid")
    private Long conrelid;

    @TableField(value = "conname")
    private String conname;

}
