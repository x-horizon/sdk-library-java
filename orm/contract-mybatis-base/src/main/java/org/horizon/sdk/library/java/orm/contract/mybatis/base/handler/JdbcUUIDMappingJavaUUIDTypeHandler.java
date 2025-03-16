package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.UUID;

/**
 * <p>the postgresql jdbc uuid and Java {@link UUID} mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
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
 *          typeHandler = JdbcUUIDMappingJavaUUIDTypeHandler.class
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
 *     typeHandler = JdbcUUIDMappingJavaUUIDTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>data conversion features:</strong></p>
 * <ul>
 *  <li>native UUID type conversion without string serialization</li>
 *  <li>automatic null safety handling</li>
 * </ul>
 *
 * @author wjm
 * @since 2023-11-11 14:55
 */
public class JdbcUUIDMappingJavaUUIDTypeHandler extends AbstractJdbcComplexTypeHandler<UUID> {

    @Override
    protected Object toJdbcObject(UUID javaObject) {
        return javaObject;
    }

    @SneakyThrows
    @Override
    protected UUID toJavaObject(String columnName, String columnValue) {
        return Nil.isBlank(columnValue) ? null : UUID.fromString(columnValue);
    }

}