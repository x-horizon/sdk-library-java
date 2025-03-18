package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaNullableListEntityTypeHandler;
import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;

/**
 * <p>the mysql jdbc json data type and Java list nullable entity mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>mysql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *     id           BIGINT                        NOT NULL PRIMARY KEY,
 *     detail_infos JSON   DEFAULT (JSON_ARRAY()) NOT NULL -- value format: [{"name":"myName1","age":18},{"name":"myName2","age":18}]
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
 *          typeHandler = JdbcJsonMappingJavaNullableListEntityTypeHandler.class
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
 *     typeHandler = JdbcJsonMappingJavaNullableListEntityTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>null handling rules:</strong></p>
 * <ul>
 *  <li>when {@code detailPOs} is {@code null} or empty list ➔ mysql stores {@code '[]'}</li>
 *  <li>when list contains elements where {@link NullableObject#isNull()} returns {@code true} ➔ filtered before storage</li>
 *  <li>non-null elements are serialized as JSON objects in the array</li>
 * </ul>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaNullableListEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonMappingJavaNullableListEntityTypeHandler<T, String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}