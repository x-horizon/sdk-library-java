package cn.srd.library.orm.mybatis.plus.database.mysql.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;

/**
 * 表主键生成器接口实现：使用自增 UUID 实现，仅支持 MySQL 8.0 及以上版本；
 * <pre>
 *    使用示例如下，此时新增数据时将会根据 {@link #executeSql(String)} 的返回值填充到主键中；
 *
 *    // 建表语句：
 *    // CREATE TABLE table_name
 *    //  (
 *    //      table_name_uuid    BINARY(16) NOT NULL COMMENT '主键',
 *    //      PRIMARY KEY (tenant_uuid)
 *    //  ) ENGINE = INNODB
 *    //    DEFAULT CHARSET = UTF8MB4
 *    //      COMMENT '示例表';
 *
 *    &#064;KeySequence("mysqlBinUuidPrimaryKeyGenerator")
 *    &#064;TableName(value = "table_name", autoResultMap = true)
 *    public class TableNamePO implements Serializable {
 *        &#064;Serial
 *        private static final long serialVersionUID = -1635772589307788624L;
 *        &#064;TableId(type = IdType.INPUT)
 *        private Byte[] tenantUuid;
 *    }
 *
 * </pre>
 *
 * @author wjm
 * @since 2022-07-05
 */
public class MysqlBinaryPrimaryKeyGenerator implements IKeyGenerator {

    @Override
    public String executeSql(String incrementerName) {
        return "SELECT UUID_TO_BIN(UUID(), true);";
    }

    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }

}
