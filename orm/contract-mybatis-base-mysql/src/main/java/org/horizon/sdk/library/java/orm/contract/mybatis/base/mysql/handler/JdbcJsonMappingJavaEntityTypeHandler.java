package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaEntityTypeHandler;

/**
 * <p>mysql jdbc json data type and java entity mapping relation type handler.</p>
 *
 * <p>implementation workflow:</p>
 * <ol>
 *     <li><b>database schema:</b>
 *         <pre>{@code
 * CREATE TABLE example (
 *    id          BIGINT                         NOT NULL PRIMARY KEY,
 *    detail_info JSON   DEFAULT (JSON_OBJECT()) NOT NULL -- Example: {"name": "myName", "age": 18}
 * );
 *         }</pre>
 *     </li>
 *
 *     <li><b>java entity mapping:</b>
 *         <pre>{@code
 * @Data
 * @YourOrmTable(tableName = "example")
 * public class ExamplePO implements Serializable {
 *     @Serial
 *     private static final long serialVersionUID = -7680901283684311918L;
 *
 *     @YourOrmColumnId
 *     @YourOrmColumn(columnName = "id")
 *     private Long id;
 *
 *     @YourOrmColumn(
 *         columnName = "detail_info",
 *         typeHandler = JdbcJsonMappingJavaEntityTypeHandler.class
 *     )
 *     private DetailPO detailPO;
 * }
 *         }</pre>
 *     </li>
 *
 *     <li><b>json pojo structure:</b>
 *         <pre>{@code
 * @Data
 * public class DetailPO implements Serializable {
 *     @Serial
 *     private static final long serialVersionUID = -88531220073385451L;
 *
 *     private String name;
 *     private Short age;
 * }
 *         }</pre>
 *     </li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @YourOrmColumn(
 *     columnName = "detail_info",
 *     typeHandler = JdbcJsonMappingJavaEntityTypeHandler.class
 * )
 * }</pre>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaEntityTypeHandler<T> extends AbstractJdbcJsonMappingJavaEntityTypeHandler<T, String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}