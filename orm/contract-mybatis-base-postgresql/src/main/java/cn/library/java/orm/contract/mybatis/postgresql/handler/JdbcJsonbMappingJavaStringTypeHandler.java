package cn.library.java.orm.contract.mybatis.postgresql.handler;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java string mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like map {} as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id          BIGINT                     NOT NULL,
 *         detail_info JSONB  DEFAULT '{}'::JSONB NOT NULL, -- the value like {"name": "myName", "age": 18}, also using: detail_info JSONB  DEFAULT '[]'::JSONB NOT NULL, -- the value like [{"name": "myName1", "age": 18}, {"name": "myName2", "age": 18}]
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
 *         @OrmFrameworkColumnMarkedDemo(columnName = "detail_info", typeHandler = JdbcJsonbMappingJavaStringTypeHandler.class)
 *         private String detailInfo;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note: the core of the postgresql jdbc jsonb data type and java string mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "detail_info", typeHandler = JdbcJsonbMappingJavaStringTypeHandler.class)</em></strong>
 * <p>
 *
 * @author wjm
 * @since 2023-11-10 14:35
 */
public class JdbcJsonbMappingJavaStringTypeHandler extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<String> {

    @Override
    protected Object toJdbcObject(String javaObject) {
        return toPostgresqlObject(javaObject);
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected String doConvertToJavaObject(String columnValue, Class javaType) {
        return columnValue;
    }

}