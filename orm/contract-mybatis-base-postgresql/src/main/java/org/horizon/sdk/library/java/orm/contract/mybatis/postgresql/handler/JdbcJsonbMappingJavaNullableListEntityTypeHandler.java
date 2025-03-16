package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;

import java.util.List;

/**
 * <p>the postgresql jdbc jsonb data type and Java list nullable entity mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id           BIGINT              NOT NULL,
 *      detail_infos JSONB DEFAULT '[]'  NOT NULL,  -- value format: [{"name":"myName1","age":18},{"name":"myName2","age":18}]
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
 *          columnName = "detail_infos",
 *          typeHandler = JdbcJsonbMappingJavaNullableListEntityTypeHandler.class
 *      )
 *      private List<DetailPO> detailPOs;
 *  }
 *  }</pre></li>
 *
 *  <li><p>nested entity definition:</p>
 *  <pre>{@code
 *  @Data
 *  public class DetailPO implements NullableObject, Serializable {
 *      @Serial
 *      private static final long serialVersionUID = -88531220073385451L;
 *
 *      @Override
 *      public boolean isNull() {
 *          return false;  // actual null determination logic
 *      }
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
 *     columnName = "detail_infos",
 *     typeHandler = JdbcJsonbMappingJavaNullableListEntityTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>null handling rules:</strong></p>
 * <ul>
 *  <li>when {@code detailPOs} is {@code null} or empty list ➔ PostgreSQL stores {@code '[]'}</li>
 *  <li>when list contains elements where {@link NullableObject#isNull()} returns {@code true} ➔ filtered before storage</li>
 *  <li>non-null elements are serialized as JSON objects in the array</li>
 * </ul>
 *
 * @param <T> the type of Java objects in the list that map to JSONB array elements
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonbMappingJavaNullableListEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<T> {

    @Override
    protected Object doConvertToJdbcObject(List<T> javaObjects) {
        return javaObjects.stream().filter(NullableObject::isNotNull).toList();
    }

}