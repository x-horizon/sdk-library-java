package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaStringTypeHandler;
import org.postgresql.util.PGobject;

/**
 * <p>the postgresql jdbc json data type and Java string mapping relation type handler.</p>
 *
 * <p>typical usage scenarios:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *     id          BIGINT              NOT NULL,
 *     detail_info JSON   DEFAULT '{}' NOT NULL, -- value example: {"name": "myName", "age": 18}
 *     PRIMARY KEY (id)
 *  );
 *  }</pre>
 *  <p>or:</p>
 *  <pre>{@code
 *  detail_info JSON DEFAULT '[]' NOT NULL -- value example: [{"name":"myName1"},{"name":"myName2"}]
 *  }</pre></li>
 *
 *  <li><p>Java entity mapping:</p>
 *  <pre>{@code
 *  @Data
 *  @YourOrmTable(tableName = "example")
 *  public class ExamplePO implements Serializable {
 *      @Serial
 *      private static final long serialVersionUID = -7680901283684311918L;
 *
 *      @YourOrmColumnId
 *      @YourOrmColumn(columnName = "id")
 *      private Long id;
 *
 *      @YourOrmColumn(
 *          columnName = "detail_info",
 *          typeHandler = JdbcJsonMappingJavaStringTypeHandler.class
 *      )
 *      private String detailInfo;
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @YourOrmColumn(
 *     columnName = "detail_info",
 *     typeHandler = JdbcJsonMappingJavaStringTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>implementation requirements:</strong></p>
 * <ul>
 *  <li>Java string field must contain valid JSON format data</li>
 *  <li>Supports both JSON object ({@code {}}) and array ({@code []}) storage formats</li>
 *  <li>Automatic conversion between JSON and String types during persistence</li>
 * </ul>
 *
 * @author wjm
 * @since 2023-11-10 14:35
 */
public class JdbcJsonMappingJavaStringTypeHandler extends AbstractJdbcJsonMappingJavaStringTypeHandler<PGobject> implements PostgresqlJsonTypeConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PostgresqlJsonTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}