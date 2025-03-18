package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaListEnumStringTypeHandler;
import org.postgresql.util.PGobject;

/**
 * <p>the postgresql jdbc json data type and java list enum string value mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id    BIGINT              NOT NULL,
 *      types JSON   DEFAULT '[]' NOT NULL,  -- value format: ["a", "b", "c"]
 *      PRIMARY KEY (id)
 *  );
 *  }</pre></li>
 *
 *  <li><p>java entity mapping:</p>
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
 *          columnName = "types",
 *          typeHandler = JdbcJsonMappingJavaListEnumStringTypeHandler.class
 *      )
 *      private List<TypeEnum> types;
 *  }
 *  }</pre></li>
 *
 *  <li><p>enum definition:</p>
 *  <pre>{@code
 *  public enum TypeEnum {
 *      A("a"),
 *      B("b"),
 *      C("c");
 *
 *      @EnumValue
 *      private final String code;
 *
 *      TypeEnum(String code) {
 *          this.code = code;
 *      }
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @YourOrmColumn(
 *     columnName = "types",
 *     typeHandler = JdbcJsonMappingJavaListEnumStringTypeHandler.class
 * )
 * }</pre>
 *
 * @param <E> the java object data type
 * @author wjm
 * @since 2023-11-09 18:45
 */
public class JdbcJsonMappingJavaListEnumStringTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonMappingJavaListEnumStringTypeHandler<E, PGobject> implements PostgresqlObjectJsonConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PostgresqlObjectJsonConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}