package org.horizon.sdk.library.java.orm.contract.mybatis.base.postgresql.handler;

import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcJsonMappingJavaListLongTypeHandler;
import org.postgresql.util.PGobject;

/**
 * <p>the postgresql jdbc jsonb data type and Java list long mapping relation type handler.</p>
 *
 * <p>typical usage scenario:</p>
 * <ol>
 *  <li><p>postgresql table definition:</p>
 *  <pre>{@code
 *  CREATE TABLE example (
 *     id         BIGINT               NOT NULL,
 *     family_ids JSONB  DEFAULT '[]'  NOT NULL, -- value format: [100, 101, 102]
 *     PRIMARY KEY (id)
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
 *          typeHandler = JdbcJsonbMappingJavaListLongTypeHandler.class
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
 *     typeHandler = JdbcJsonbMappingJavaListLongTypeHandler.class
 * )
 * }</pre>
 *
 * @author wjm
 * @since 2023-06-14 09:20
 */
public class JdbcJsonbMappingJavaListLongTypeHandler extends AbstractJdbcJsonMappingJavaListLongTypeHandler<PGobject> implements PostgresqlJsonbTypeConverter {

    @Override
    public PGobject toJdbcObjectByStringContent(String javaObjectContent) {
        return PostgresqlJsonbTypeConverter.super.toJdbcObjectByStringContent(javaObjectContent);
    }

}