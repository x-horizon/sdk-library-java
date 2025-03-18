package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaStringTypeHandler;

/**
 * <p>the mysql jdbc json data type and Java string mapping relation type handler.</p>
 *
 * <p>typical usage scenarios:</p>
 * <ol>
 *  <li><p>mysql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *     id          BIGINT                        NOT NULL PRIMARY KEY,
 *     detail_info JSON   DEFAULT (JSON_OBJECT()) NOT NULL -- value example: {"name": "myName", "age": 18}
 *  );
 *  }</pre>
 *  <p>or:</p>
 *  <pre>{@code
 *  detail_info JSON DEFAULT (JSON_ARRAY()) NOT NULL -- value example: [{"name":"myName1"},{"name":"myName2"}]
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
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaStringTypeHandler extends AbstractJdbcJsonMappingJavaStringTypeHandler<String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}