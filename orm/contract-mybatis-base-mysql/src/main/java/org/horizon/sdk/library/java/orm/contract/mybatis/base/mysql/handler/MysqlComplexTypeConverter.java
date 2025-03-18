package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import lombok.SneakyThrows;

/**
 * the mysql complex type converter.
 *
 * @author wjm
 * @since 2025-03-19 10:15
 */
public interface MysqlComplexTypeConverter {

    @SneakyThrows
    default String toJdbcObjectByStringContent(String javaObjectContent) {
        return javaObjectContent;
    }

}