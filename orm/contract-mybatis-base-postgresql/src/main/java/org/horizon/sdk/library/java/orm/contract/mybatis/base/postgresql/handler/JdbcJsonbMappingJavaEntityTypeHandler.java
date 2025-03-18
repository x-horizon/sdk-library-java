package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

/**
 * <p>postgresql jdbc jsonb data type and java entity mapping relation type handler.</p>
 *
 * <p>implementation workflow:</p>
 * <ol>
 *     <li><b>database schema:</b>
 *         <pre>{@code
 * CREATE TABLE example (
 *     id         BIGINT              NOT NULL,
 *     detail_info JSONB DEFAULT '{}' NOT NULL,  -- Example: {"name": "myName", "age": 18}
 *     PRIMARY KEY (id)
 * );
 *         }</pre>
 *     </li>
 *
 *     <li><b>java entity mapping:</b>
 *         <pre>{@code
 * @Data
 * @OrmFrameworkTableMarkedDemo(tableName = "example")
 * public class ExamplePO implements Serializable {
 *     @Serial
 *     private static final long serialVersionUID = -7680901283684311918L;
 *
 *     @OrmFrameworkIdMarkedDemo
 *     @OrmFrameworkColumnMarkedDemo(columnName = "id")
 *     private Long id;
 *
 *     @OrmFrameworkColumnMarkedDemo(
 *         columnName = "detail_info",
 *         typeHandler = JdbcJsonbMappingJavaEntityTypeHandler.class
 *     )
 *     private DetailPO detailPO;
 * }
 *         }</pre>
 *     </li>
 *
 *     <li><b>jsonb pojo structure:</b>
 *         <pre>{@code
 * @Data
 * public class DetailPO implements Serializable {
 *     @Serial
 *     private static final long serialVersionUID = -88531220073385451L;
 *
 *     private String name;
 *     private Short age;
 * }
 *         }</pre>
 *     </li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @OrmFrameworkColumnMarkedDemo(
 *     columnName = "detail_info",
 *     typeHandler = JdbcJsonbMappingJavaEntityTypeHandler.class
 * )
 * }</pre>
 *
 * @param <T> mapped java object type
 * @author wjm
 * @since 2023-11-08 16:51
 */
public class JdbcJsonbMappingJavaEntityTypeHandler<T> extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<T> {

}