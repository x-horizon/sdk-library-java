package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaListEnumIntegerTypeHandler;

/**
 * <p>mysql jdbc json array type and java enum list mapping handler.</p>
 *
 * <p>implementation workflow:</p>
 * <ol>
 *     <li><b>database schema:</b>
 *         <pre>{@code
 * CREATE TABLE example (
 *    id    BIGINT                        NOT NULL PRIMARY KEY,
 *    types JSON   DEFAULT (JSON_ARRAY()) NOT NULL -- Example: [1, 2, 3]
 * );
 *         }</pre>
 *     </li>
 *
 *     <li><b>java entity mapping:</b>
 *         <pre>{@code
 * @Data
 * @YourOrmTable(tableName = "example")
 * public class ExamplePO {
 *     @YourOrmColumnId
 *     @YourOrmColumn(columnName = "id")
 *     private Long id;
 *
 *     @YourOrmColumn(
 *         columnName = "types",
 *         typeHandler = JdbcJsonMappingJavaListEnumIntegerTypeHandler.class
 *     )
 *     private List<TypeEnum> types;
 * }
 *         }</pre>
 *     </li>
 *
 *     <li><b>enum structure:</b>
 *         <pre>{@code
 * @Getter
 * @AllArgsConstructor
 * public enum TypeEnum {
 *     A(1),
 *     B(2),
 *     C(3);
 *
 *     @EnumValue
 *     private final int code;
 * }
 *         }</pre>
 *     </li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @YourOrmColumn(
 *     columnName = "types",
 *     typeHandler = JdbcJsonMappingJavaListEnumIntegerTypeHandler.class
 * )
 * }</pre>
 *
 * @param <E> the java object data type
 * @author wjm
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonMappingJavaListEnumIntegerTypeHandler<E, String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}