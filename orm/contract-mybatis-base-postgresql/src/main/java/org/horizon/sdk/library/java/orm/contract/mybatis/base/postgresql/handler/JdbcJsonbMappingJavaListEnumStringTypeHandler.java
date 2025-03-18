package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

/**
 * <p>the postgresql jdbc jsonb data type and java list enum string value mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id    BIGINT              NOT NULL,
 *      types JSONB DEFAULT '[]'  NOT NULL,  -- value format: ["a", "b", "c"]
 *      PRIMARY KEY (id)
 *  );
 *  }</pre></li>
 *
 *  <li><p>java entity mapping:</p>
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
 *          columnName = "types",
 *          typeHandler = JdbcJsonbMappingJavaListEnumStringTypeHandler.class
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
 * @OrmFrameworkColumnMarkedDemo(
 *     columnName = "types",
 *     typeHandler = JdbcJsonbMappingJavaListEnumStringTypeHandler.class
 * )
 * }</pre>
 *
 * @param <E> the enum type that maps to jsonb array values
 * @author wjm
 * @since 2023-11-09 18:45
 */
public class JdbcJsonbMappingJavaListEnumStringTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonbMappingJavaListEnumTypeHandler<E> {

    @Override
    protected Class<?> selectEnumFieldType() {
        return String.class;
    }

}