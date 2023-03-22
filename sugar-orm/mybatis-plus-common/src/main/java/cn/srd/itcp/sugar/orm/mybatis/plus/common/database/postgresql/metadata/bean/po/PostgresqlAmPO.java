package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * (pg_am) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "pg_am", autoResultMap = true)
public class PostgresqlAmPO {

    @TableField(value = "oid")
    private Long oid;

}
