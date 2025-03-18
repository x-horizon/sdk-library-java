package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * the postgresql jdbc jsonb data type and java list enum mapping relation abstract type handler
 *
 * @param <E> the enum data type
 * @author wjm
 * @since 2023-11-09 18:45
 */
public abstract class AbstractJdbcJsonbMappingJavaListEnumTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<E> {

    /**
     * <p>select the enum field type to persist.</p>
     *
     * <p>example enum structure:</p>
     * <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN(1, "man", "Man"),
     *     WOMAN(2, "woman", "Woman"),
     *     UNKNOWN(3, "unknown", "Unknown");
     *
     *     private final int code;
     *     private final String description1;
     *     private final String description2;
     * }
     * }</pre>
     *
     * <p>persistence behavior:</p>
     * <ul>
     *     <li>when returning {@code Integer.class} -> JDBC JSONB stores [1, 2, 3]</li>
     *     <li>when returning {@code String.class} -> JDBC JSONB stores ["man", "woman", "unknown"]</li>
     * </ul>
     *
     * @return the field type used for enum persistence
     */
    protected abstract Class<?> selectEnumFieldType();

    @Override
    protected Object doConvertToJdbcObject(List<E> javaObjects) {
        return javaObjects.stream().map(javaObject -> Enums.getFieldValue(javaObject, selectEnumFieldType())).toList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<E> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.toEnumsByString(Strings.removeHeadTailBracket(columnValue), javaType);
    }

}