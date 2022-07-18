package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "pg_tables", autoResultMap = true)
public class PostgresqlTablePO {

}
