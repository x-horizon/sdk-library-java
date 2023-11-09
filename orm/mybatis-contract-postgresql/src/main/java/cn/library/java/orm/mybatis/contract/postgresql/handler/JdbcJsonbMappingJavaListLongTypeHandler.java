// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java list long mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like array [] as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id         BIGINT                     NOT NULL,
 *         family_ids JSONB  DEFAULT '[]'::JSONB NOT NULL, -- the value like [441769244028997, 441769244028998, 441769244028999]
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
 *         @OrmFrameworkColumnMarkedDemo(columnName = "family_ids", typeHandler = JdbcJsonbMappingJavaListLongTypeHandler.class)
 *         private List<Long> familyIds;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note: the core of the postgresql jdbc jsonb data type and java list long mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "family_ids", typeHandler = JdbcJsonbMappingJavaListLongTypeHandler.class)</em></strong>
 * <p/>
 *
 * @author wjm
 * @since 2023-06-14 09:20
 */
public class JdbcJsonbMappingJavaListLongTypeHandler extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<Long> {

}
