package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;

import java.util.Map;

/**
 * <p>provide a standard specification to explain the mapping relationship between jdbc json type and java nullable entity type.</p>
 * <p>you can refer to the implementations of this class.</p>
 *
 * @param <T> the java object data type
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public abstract class AbstractJdbcJsonMappingJavaNullableEntityTypeHandler<T extends NullableObject, J> extends AbstractJdbcJsonMappingJavaObjectTypeHandler<T, J> {

    @Override
    protected Object doConvertToJdbcObject(T javaObject) {
        return javaObject.isNull() ? Map.of() : javaObject;
    }

}