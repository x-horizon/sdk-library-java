package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaNullableEntityTypeHandler;
import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;
import org.postgresql.util.PGobject;

/**
 * <p>the postgresql jdbc json data type and Java nullable entity mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id          BIGINT              NOT NULL,
 *      detail_info JSON   DEFAULT '{}' NOT NULL,  -- value format: {"name": "myName", "age": 18}
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
 *          columnName = "detail_info",
 *          typeHandler = JdbcJsonMappingJavaNullableEntityTypeHandler.class
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
 * @YourOrmColumn(
 *     columnName = "detail_info",
 *     typeHandler = JdbcJsonMappingJavaNullableEntityTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>nullable handling mechanism:</strong></p>
 * <ul>
 *  <li>when {@code detailPO} field is {@code null}</li>
 *  <li>or {@link NullableObject#isNull()} returns {@code true}</li>
 *  <li>postgresql will store empty JSON object {@code '{}'}</li>
 * </ul>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonMappingJavaNullableEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonMappingJavaNullableEntityTypeHandler<T, PGobject> implements PgObjectJsonConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PgObjectJsonConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}