package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaListEntityTypeHandler;
import org.postgresql.util.PGobject;

/**
 * <p>postgresql jdbc jsonb array type and java list entity mapping handler.</p>
 *
 * <p>implementation workflow:</p>
 * <ol>
 *     <li><b>database schema:</b>
 *         <pre>{@code
 * CREATE TABLE example (
 *     id           BIGINT             NOT NULL,
 *     detail_infos JSONB DEFAULT '[]' NOT NULL,  -- Example: [{"name":"myName1","age":18},{"name":"myName2","age":18}]
 *     PRIMARY KEY (id)
 * );
 *         }</pre>
 *     </li>
 *
 *     <li><b>java entity mapping:</b>
 *         <pre>{@code
 * @Data
 * @YourOrmTable(tableName = "example")
 * public class ExamplePO implements Serializable {
 *     @Serial private static final long serialVersionUID = -7680901283684311918L;
 *
 *     @YourOrmColumnId
 *     @YourOrmColumn(columnName = "id")
 *     private Long id;
 *
 *     @YourOrmColumn(
 *         columnName = "detail_infos",
 *         typeHandler = JdbcJsonbMappingJavaListEntityTypeHandler.class
 *     )
 *     private List<DetailPO> detailPOs;
 * }
 *         }</pre>
 *     </li>
 *
 *     <li><b>jsonb element structure:</b>
 *         <pre>{@code
 * @Data
 * public class DetailPO implements Serializable {
 *     @Serial private static final long serialVersionUID = -88531220073385451L;
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
 *     columnName = "detail_infos",
 *     typeHandler = JdbcJsonbMappingJavaListEntityTypeHandler.class
 * )
 * }</pre>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-08 16:51
 */
public class JdbcJsonbMappingJavaListEntityTypeHandler<T> extends AbstractJdbcJsonMappingJavaListEntityTypeHandler<T, PGobject> implements PostgresqlObjectJsonbConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PostgresqlObjectJsonbConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}