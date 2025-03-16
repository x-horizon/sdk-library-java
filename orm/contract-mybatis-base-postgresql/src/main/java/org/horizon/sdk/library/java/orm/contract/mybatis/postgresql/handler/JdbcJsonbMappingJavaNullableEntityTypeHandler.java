package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;

import java.util.Map;

/**
 * <p>the postgresql jdbc jsonb data type and Java nullable entity mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id          BIGINT              NOT NULL,
 *      detail_info JSONB DEFAULT '{}'  NOT NULL,  -- value format: {"name": "myName", "age": 18}
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
 *          columnName = "detail_info",
 *          typeHandler = JdbcJsonbMappingJavaNullableEntityTypeHandler.class
 *      )
 *      private DetailPO detailPO;
 *  }
 *  }</pre></li>
 *
 *  <li><p>nested entity definition:</p>
 *  <pre>{@code
 *  @Data
 *  public class DetailPO implements Serializable {
 *      @Serial
 *      private static final long serialVersionUID = -88531220073385451L;
 *
 *      private String name;
 *      private Short age;
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @OrmFrameworkColumnMarkedDemo(
 *     columnName = "detail_info",
 *     typeHandler = JdbcJsonbMappingJavaNullableEntityTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>nullable handling mechanism:</strong></p>
 * <ul>
 *  <li>when {@code detailPO} field is {@code null}</li>
 *  <li>or {@link NullableObject#isNull()} returns {@code true}</li>
 *  <li>postgresql will store empty JSONB object {@code '{}'}</li>
 * </ul>
 *
 * @param <T> the type of Java object that maps to JSONB data
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonbMappingJavaNullableEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<T> {

    @Override
    protected Object doConvertToJdbcObject(T javaObject) {
        return javaObject.isNull() ? Map.of() : javaObject;
    }

}