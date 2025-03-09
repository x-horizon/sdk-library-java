package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.UUID;

/**
 * <pre>
 * the postgresql jdbc uuid and java uuid relation type handler.
 *
 * 1. the postgresql sql as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id        BIGINT NOT NULL,
 *         family_id UUID, -- the value like "b9d8e022-dd38-42df-90b0-05055447910c"
 *         PRIMARY KEY (id)
 *     );
 * }
 *
 * 2. the java object as following:
 * {@code
 *     @Data
 *     // need to replace this annotation from the specified orm framework
 *     @OrmFrameworkTableMarkedDemo(tableName = "example")
 *     public class ExamplePO implements Serializable {
 *
 *         @Serial private static final long serialVersionUID = -7680901283684311918L;
 *
 *         // need to replace this annotation from the specified orm framework
 *         @OrmFrameworkIdMarkedDemo
 *         @OrmFrameworkColumnMarkedDemo(columnName = "id")
 *         private Long id;
 *
 *         // need to replace this annotation from the specified orm framework
 *         // add the type handler
 *         @OrmFrameworkColumnMarkedDemo(columnName = "family_id", typeHandler = JdbcUUIDMappingJavaUUIDTypeHandler.class)
 *         private UUID familyId;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note: the core of the postgresql jdbc uuid and java uuid mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "family_id", typeHandler = JdbcUUIDMappingJavaUUIDTypeHandler.class)</em></strong>
 * <p>
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