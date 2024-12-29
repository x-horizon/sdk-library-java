package cn.srd.library.java.orm.contract.mybatis.postgresql.handler;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java list enum integer value mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like array [] as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id    BIGINT                     NOT NULL,
 *         types JSONB  DEFAULT '[]'::JSONB NOT NULL, -- the value like [1, 2, 3]
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
 *         @OrmFrameworkColumnMarkedDemo(columnName = "types", typeHandler = JdbcJsonbMappingJavaListEnumIntegerTypeHandler.class)
 *         private List<TypeEnum> types;
 *
 *     }
 * }
 *
 * 3. the enum mapping postgresql jdbc jsonb as following:
 * {@code
 *     @Getter
 *     @AllArgsConstructor
 *     public class TypeEnum {
 *
 *         A(1),
 *         B(2),
 *         C(3),
 *
 *         ;
 *
 *         @EnumValue
 *         private final int code;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note: the core of the postgresql jdbc jsonb data type and java list enum integer value mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "types", typeHandler = JdbcJsonbMappingJavaListEnumIntegerTypeHandler.class)</em></strong>
 * <p>
 *
 * @param <E> the enum data type
 * @author xiongjing
 * @since 2023-05-09 10:35
 */
public class JdbcJsonbMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonbMappingJavaListEnumTypeHandler<E> {

    @Override
    protected Class<?> selectEnumFieldType() {
        return Integer.class;
    }

}