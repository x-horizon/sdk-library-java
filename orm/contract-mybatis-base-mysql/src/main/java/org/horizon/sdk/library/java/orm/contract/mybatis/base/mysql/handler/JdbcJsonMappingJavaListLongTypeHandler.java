package org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaListLongTypeHandler;

/**
 * <p>the mysql jdbc json data type and Java list long mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>mysql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *     id         BIGINT                        NOT NULL PRIMARY KEY,
 *     family_ids JSON   DEFAULT (JSON_ARRAY()) NOT NULL -- value format: [100, 101, 102]
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
 *          columnName = "family_ids",
 *          typeHandler = JdbcJsonMappingJavaListLongTypeHandler.class
 *      )
 *      private List<Long> familyIds;
 *  }
 *  }</pre></li>
 * </ol>
 *
 * <p><strong>core configuration:</strong></p>
 * <pre>{@code
 * @YourOrmColumn(
 *     columnName = "family_ids",
 *     typeHandler = JdbcJsonMappingJavaListLongTypeHandler.class
 * )
 * }</pre>
 *
 * @author wjm
 * @since 2025-03-19 10:15
 */
public class JdbcJsonMappingJavaListLongTypeHandler extends AbstractJdbcJsonMappingJavaListLongTypeHandler<String> implements MysqlComplexTypeConverter {

    @Override
    public String toJdbcObjectByStringContent(String javaObjectContent) {
        return MysqlComplexTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}