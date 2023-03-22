package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * (pg_tables) 持久化模型
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "pg_tables", autoResultMap = true)
public class PostgresqlTablePO {

}
