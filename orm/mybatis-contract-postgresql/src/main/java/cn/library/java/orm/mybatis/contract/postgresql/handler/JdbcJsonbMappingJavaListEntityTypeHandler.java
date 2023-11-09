// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java list entity mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like array [] as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id           BIGINT                     NOT NULL,
 *         detail_infos JSONB  DEFAULT '[]'::JSONB NOT NULL, -- the value like [{"name": "myName1", "age": 18}, {"name": "myName2", "age": 18}]
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
 *         @OrmFrameworkColumnMarkedDemo(columnName = "detail_infos", typeHandler = JdbcJsonbMappingJavaListEntityTypeHandler.class)
 *         private List<DetailPO> detailPOs;
 *
 *     }
 * }
 *
 * 3. the java object mapping postgresql jdbc jsonb as following:
 * {@code
 *     @Data
 *     public class DetailPO implements Serializable {
 *
 *         @Serial private static final long serialVersionUID = -88531220073385451L;
 *
 *         private String name;
 *
 *         private Short age;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note: the core of the postgresql jdbc jsonb data type and java list entity mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "detail_infos", typeHandler = JdbcJsonbMappingJavaListEntityTypeHandler.class)</em></strong>
 * <p/>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-08 16:51
 */
public class JdbcJsonbMappingJavaListEntityTypeHandler<T> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<T> {

}
