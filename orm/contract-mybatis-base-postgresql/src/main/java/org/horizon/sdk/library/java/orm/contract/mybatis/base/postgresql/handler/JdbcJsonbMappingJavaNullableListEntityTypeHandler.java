package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaNullableListEntityTypeHandler;
import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;
import org.postgresql.util.PGobject;

/**
 * <p>the postgresql jdbc jsonb data type and Java list nullable entity mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id           BIGINT              NOT NULL,
 *      detail_infos JSONB  DEFAULT '[]' NOT NULL,  -- value format: [{"name":"myName1","age":18},{"name":"myName2","age":18}]
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
 * @YourOrmColumn(
 *     columnName = "detail_infos",
 *     typeHandler = JdbcJsonbMappingJavaNullableListEntityTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>null handling rules:</strong></p>
 * <ul>
 *  <li>when {@code detailPOs} is {@code null} or empty list ➔ PostgreSQL stores {@code '[]'}</li>
 *  <li>when list contains elements where {@link NullableObject#isNull()} returns {@code true} ➔ filtered before storage</li>
 *  <li>non-null elements are serialized as JSONB objects in the array</li>
 * </ul>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonbMappingJavaNullableListEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonMappingJavaNullableListEntityTypeHandler<T, PGobject> implements PostgresqlObjectJsonbConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PostgresqlObjectJsonbConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}