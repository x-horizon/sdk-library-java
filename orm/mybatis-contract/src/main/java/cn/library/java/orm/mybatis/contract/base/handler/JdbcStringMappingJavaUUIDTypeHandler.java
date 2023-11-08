package cn.library.java.orm.mybatis.contract.base.handler;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.object.Nil;
import lombok.SneakyThrows;

import java.util.UUID;

/**
 * @author wjm
 * @since 2022-07-12 18:42
 */
public class JdbcStringMappingJavaUUIDTypeHandler extends AbstractJdbcComplexTypeHandler<UUID> {

    @Override
    protected Object toJdbcObject(UUID javaObject) {
        return Nil.isNull(javaObject) ? SymbolConstant.EMPTY : javaObject.toString();
    }

    @SneakyThrows
    @Override
    protected UUID toJavaObject(String content, String columnName) {
        return Nil.isBlank(content) ? null : UUID.fromString(content);
    }

}
