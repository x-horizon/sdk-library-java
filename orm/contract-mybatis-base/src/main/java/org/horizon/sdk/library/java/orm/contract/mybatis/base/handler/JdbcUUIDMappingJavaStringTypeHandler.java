package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import lombok.SneakyThrows;

import java.util.UUID;

/**
 * <p>the jdbc uuid and Java string mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id        BIGINT NOT NULL,
 *      family_id UUID,  -- value format: "b9d8e022-dd38-42df-90b0-05055447910c"
 *      PRIMARY KEY (id)
 *  );
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
 *          columnName = "family_id",
 *          typeHandler = JdbcUUIDMappingJavaStringTypeHandler.class
 *      )
 *      private String familyId;
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @YourOrmColumn(
 *     columnName = "family_id",
 *     typeHandler = JdbcUUIDMappingJavaStringTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>data conversion rules:</strong></p>
 * <ul>
 *  <li>String → UUID conversion validates 36-character format with hyphens</li>
 *  <li>Automatic null handling for both database and Java fields</li>
 *  <li>Supports standard UUID string representations (RFC 4122)</li>
 * </ul>
 *
 * @author wjm
 * @since 2020-12-25 15:36
 */
public class JdbcUUIDMappingJavaStringTypeHandler extends AbstractJdbcComplexTypeHandler<String, UUID> {

    @Override
    protected UUID toJdbcObject(String javaObject) {
        return UUID.fromString(javaObject);
    }

    @SneakyThrows
    @Override
    protected String toJavaObject(String columnName, String columnValue) {
        return columnValue;
    }

}