package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

/**
 * <p>provide a standard specification to explain the mapping relationship between jdbc json type and java list enum integer type.</p>
 * <p>you can refer to the implementations of this class.</p>
 *
 * @param <E> the java object data type
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2023-05-09 10:35
 */
public abstract class AbstractJdbcJsonMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>, J> extends AbstractJdbcJsonMappingJavaListEnumTypeHandler<E, J> {

    @Override
    protected Class<?> selectEnumFieldType() {
        return Integer.class;
    }

}