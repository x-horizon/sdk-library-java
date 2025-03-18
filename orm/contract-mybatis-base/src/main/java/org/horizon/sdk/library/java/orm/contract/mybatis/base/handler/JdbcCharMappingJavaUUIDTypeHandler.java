package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.UUID;

/**
 * <p>the postgresql jdbc string and Java {@link UUID} mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id        BIGINT NOT NULL,
 *      family_id CHAR(36),  -- value format: "b9d8e022-dd38-42df-90b0-05055447910c"
 *      PRIMARY KEY (id)
 *  );
 *  }</pre></li>
 *
 *  <li><p>Java entity mapping:</p>
 *  <pre>{@code
 *  @Data
 *  @OrmFrameworkTableMarkedDemo(tableName = "example")
 *  public class ExamplePO implements Serializable {
 *      @Serial
 *      private static final long serialVersionUID = -7680901283684311918L;
 *
 *      @OrmFrameworkIdMarkedDemo
 *      @OrmFrameworkColumnMarkedDemo(columnName = "id")
 *      private Long id;
 *
 *      @OrmFrameworkColumnMarkedDemo(
 *          columnName = "family_id",
 *          typeHandler = JdbcCharMappingJavaUUIDTypeHandler.class
 *      )
 *      private UUID familyId;
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @OrmFrameworkColumnMarkedDemo(
 *     columnName = "family_id",
 *     typeHandler = JdbcCharMappingJavaUUIDTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>data conversion rules:</strong></p>
 * <ul>
 *  <li>UUID → 36-character string (with hyphens) for PostgreSQL CHAR(36)</li>
 *  <li>null values are handled according to JDBC specification</li>
 * </ul>
 *
 * @author wjm
 * @since 2022-07-12 18:42
 */
public class JdbcCharMappingJavaUUIDTypeHandler extends AbstractJdbcComplexTypeHandler<UUID, String> {

    @Override
    protected String toJdbcObject(UUID javaObject) {
        return javaObject.toString();
    }

    @SneakyThrows
    @Override
    protected UUID toJavaObject(String columnName, String columnValue) {
        return Nil.isBlank(columnValue) ? null : UUID.fromString(columnValue);
    }

}