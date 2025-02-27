package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;

import java.util.Map;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java nullable entity mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like map {} as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id          BIGINT                     NOT NULL,
 *         detail_info JSONB  DEFAULT '{}'::JSONB NOT NULL, -- the value like {"name": "myName", "age": 18}
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
 *         @OrmFrameworkColumnMarkedDemo(columnName = "detail_info", typeHandler = JdbcJsonbMappingJavaNullableEntityTypeHandler.class)
 *         private DetailPO detailPO;
 *
 *     }
 * }
 *
 * 3. the java object mapping postgresql jdbc jsonb as following:
 * {@code
 *     @Data
 *     public class DetailPO implements Serializable {
 *
 *         @Serial private static final long serialVersionUID = -88531220073385451L;
 *
 *         private String name;
 *
 *         private Short age;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note1: the core of the postgresql jdbc jsonb data type and java nullable entity mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "detail_info", typeHandler = JdbcJsonbMappingJavaNullableEntityTypeHandler.class)</em></strong>
 *
 * <p><h2>note2: about the usage of implement class {@link NullableObject}:</h2>
 * <strong><em>
 * when storing this class into postgresql,<br/>
 * it provides an opportunity to represent the condition that the field in postgresql is empty,<br/>
 * when the field detailPO value in the class ExamplePO is null,<br/>
 * or {@link NullableObject#isNull()} return true,<br/>
 * it will set "{}" into postgresql.<br/>
 * </em></strong>
 * <p>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonbMappingJavaNullableEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<T> {

    @Override
    protected Object doConvertToJdbcObject(T javaObject) {
        return javaObject.isNull() ? Map.of() : javaObject;
    }

}