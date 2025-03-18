package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaNullableEntityTypeHandler;
import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;

/**
 * <p>the mysql jdbc json data type and Java nullable entity mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>mysql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *     id          BIGINT                         NOT NULL PRIMARY KEY,
 *     detail_info JSON   DEFAULT (JSON_OBJECT()) NOT NULL -- value format: {"name": "myName", "age": 18}
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
 *  <li>mysql will store empty JSON object {@code '{}'}</li>
 * </ul>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaNullableEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonMappingJavaNullableEntityTypeHandler<T, String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}