package cn.library.java.orm.mybatis.contract.base.handler;

import cn.srd.library.java.tool.lang.object.Nil;
import lombok.SneakyThrows;

import java.util.UUID;

/**
 * @author wjm
 * @since 2020-12-25 15:36
 */
public class JdbcUUIDMappingJavaUuidTypeHandler extends AbstractJdbcComplexTypeHandler<String> {

    @Override
    protected Object toJdbcObject(String javaObject) {
        return Nil.isBlank(javaObject) ? null : UUID.fromString(javaObject);
    }

    @SneakyThrows
    @Override
    protected String toJavaObject(String content, String columnName) {
        return content;
    }

}
