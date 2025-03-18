package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;

/**
 * <p>the postgresql jdbc jsonb data type and Java string mapping relation type handler.</p>
 *
 * <p>typical usage scenarios:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *      id          BIGINT              NOT NULL,
 *      detail_info JSONB DEFAULT '{}'  NOT NULL,  -- value example: {"name": "myName", "age": 18}
 *      PRIMARY KEY (id)
 *  );
 *  }</pre>
 *  <p>or:</p>
 *  <pre>{@code
 *  detail_info JSONB DEFAULT '[]' NOT NULL  -- value example: [{"name":"myName1"},{"name":"myName2"}]
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
 *          columnName = "detail_info",
 *          typeHandler = JdbcJsonbMappingJavaStringTypeHandler.class
 *      )
 *      private String detailInfo;
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @OrmFrameworkColumnMarkedDemo(
 *     columnName = "detail_info",
 *     typeHandler = JdbcJsonbMappingJavaStringTypeHandler.class
 * )
 * }</pre>
 *
 * <p><strong>implementation requirements:</strong></p>
 * <ul>
 *  <li>Java string field must contain valid JSON format data</li>
 *  <li>Supports both JSON object ({@code {}}) and array ({@code []}) storage formats</li>
 *  <li>Automatic conversion between JSONB and String types during persistence</li>
 * </ul>
 *
 * @author wjm
 * @since 2023-11-10 14:35
 */
public class JdbcJsonbMappingJavaStringTypeHandler extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<String> {

    @Override
    protected String toJdbcObjectContent(String javaObject) {
        return javaObject;
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected String doConvertToJavaObject(String columnValue, Class javaType) {
        return columnValue;
    }

}